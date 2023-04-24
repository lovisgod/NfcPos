package com.lovisgod.nfcpos

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.lovisgod.nfcpos.data.model.NFCPOSEXCEPTION
import com.lovisgod.nfcpos.utils.*
import java.nio.charset.Charset

class MainActivity : AppCompatActivity(), EmvListener {

    private lateinit var mNfcAdapter: NfcAdapter
    private lateinit var intentFiltersArray: Array<IntentFilter>
    private lateinit var techListsArray: Array<Array<String>>
    private lateinit var pendingIntent: PendingIntent

    val emvOptV2 = EmvOptV2()
    lateinit var emvHandler: EmvHandler

    val AID = "A0000000041010"
    var FCI = ""

    var SelectAID = Conversions.HexStringToByteArray(AID)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, javaClass).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
         pendingIntent = PendingIntent.getActivity(this,
            0, intent,
            PendingIntent.FLAG_MUTABLE)

//        val ndef = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
        val techDiscovered = IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED)

        intentFiltersArray = arrayOf(techDiscovered)

        techListsArray = arrayOf(arrayOf(IsoDep::class.java.name))

        // Checking if the NFC is enabled
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (!mNfcAdapter.isEnabled())
            Toast.makeText(this.getBaseContext(),
                "NFC not enabled for this device", Toast.LENGTH_LONG).show()

        AIDmapper().getAllAid(emvOptV2)
        AIDmapper().getAllCapk(emvOptV2)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            emvHandler = EmvHandler(this)
        }

    }


    public override fun onPause() {
        super.onPause()
        mNfcAdapter.disableForegroundDispatch(this)
    }

    public override fun onResume() {
        super.onResume()
        mNfcAdapter.enableForegroundDispatch(
            this,
            pendingIntent,
            intentFiltersArray,
            techListsArray
        )
    }

    public override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val tagFromIntent: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)

        if (tagFromIntent !=null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                onTagDiscovered(tagFromIntent)
            }
        } else {
            console.log("nfc tag", "Tag is empty")
        }
        //do something with tagFromIntent
    }

    private fun selectApdu(aid: ByteArray): ByteArray? {
        val commandApdu = ByteArray(6 + aid.size)
        commandApdu[0] = 0x00.toByte() // CLA
        commandApdu[1] = 0xA4.toByte() // INS
        commandApdu[2] = 0x04.toByte() // P1
        commandApdu[3] = 0x00.toByte() // P2
        commandApdu[4] = (aid.size and 0x0FF).toByte() // Lc
        System.arraycopy(aid, 0, commandApdu, 5, aid.size)
        commandApdu[commandApdu.size - 1] = 0x00.toByte() // Le
        return commandApdu
    }


    private fun getProcessingOptionApdu(data: String): ByteArray? {
        return Conversions.HexStringToByteArray(data)
    }

    private fun readRecordApdu(data: String): ByteArray? {
        return Conversions.HexStringToByteArray(data)
    }

     @RequiresApi(Build.VERSION_CODES.KITKAT)
     fun onTagDiscovered(tag: Tag) {
        val isoDep = IsoDep.get(tag);
        if (isoDep != null) {
            try {
                isoDep.connect()
                emvHandler.start(isoDep)
                // select application 1
                // makeApplication final Selection(isoDep)
                // READ APPLICATION DATA
                // EMV RISK MANAGEMENT [ OFFLINE DATA AUTH,
                         // PROCESSING RESTRICTIONS,
                         //CARDHOLDER VERIFICATION,TERMINAL RISK MANAGEMENT
                        // ]
                // TERMINAL ACTION ANALYSIS
                // CHIP ACTION ANALYSIS
                // ONLINE PROCESSING
                // ISSUER SCRIPTING
                // COMPLETION
            } catch (e: Exception) {
                println(e.printStackTrace())
                isoDep.close()
                console.log("isodeperror", e.localizedMessage.toString())
            }
        }
    }

    private fun makeApplicationSelection(isoDep: IsoDep) {
        var result = isoDep.transceive(selectApdu(SelectAID))
        console.log("isoResult", HexUtil.toHexString(result))

        // check if AID field is not null
        // check if AID returned is equal to the one we sent

        var bertlvs = Conversions.parseBERTLV(result)
        console.log("bertlv", bertlvs.toString())
        var aid = "" // 84
        var api = "" // application priority indicator (87)
        var fci = "" // file control information (6F or A5) // this has the data returned
        var lp = "" // language preference (5F2D)
        var al = "" // application label (determines type of card) (50)
        var apiF = "" // application program identifier for visa ps  (9F5A)
        var pdol = "" // processing option data list (9F38)
        val tlv = bertlvs?.let { Conversions.findTLVTAG(it, "84") }
        aid = tlv?.getHexValue().toString()
//        fci = bertlvs?.let { Conversions.findTLVTAG(it, "6F") }?.getHexValue().toString()
//        FCI = fci
        pdol = bertlvs?.let { Conversions.findTLVTAG(it, "9FC8") }?.getHexValue().toString()
        console.log("pdol", pdol)
        console.log("aid", aid)
        if (aid == AID) {
            console.log("application selected", aid)
            Toast.makeText(this, "Application selected :: ${aid}", Toast.LENGTH_LONG).show()
        }

        if (pdol  != "null") {
            console.log("", "got here")
            makeGPO(isoDep, pdol)
        } else {
            console.log("", "got here here")
            makeGPO(isoDep)
        }
    }

    private fun makeGPO(isoDep: IsoDep, pdol : String? = "") {
        console.log("",pdol.toString())
        var pdolData = "80A80000028300"
        var dataneeded = "0566" // this will be gotten from whatever pdol is returned from the select app
        // I have to work on this later on how to get the data to send back
        if (pdol?.isEmpty() == true) {
            console.log("", "got here here here")
            pdolData = "80A80000028300"
        }else {
            console.log("", "got here, here, here, here")
            pdolData = "80A800000283${pdol?.substring(pdol.length - 2, pdol.length - 1)}$dataneeded"
        }
        console.log("pdoldata", pdolData)
        var pdolByteArray = getProcessingOptionApdu(pdolData)
        var result = isoDep.transceive(pdolByteArray)
        console.log("gpoIsoResult", HexUtil.toHexString(result))

        var bertlvs = Conversions.parseBERTLV(result)
        console.log("bertlv", bertlvs.toString())

        var aip = bertlvs?.let { Conversions.findTLVTAG(it, "82") }?.getHexValue().toString()
        var afl = bertlvs?.let { Conversions.findTLVTAG(it, "94") }?.getHexValue().toString()
        var aipBinary = ""
        console.log("gpo result", "aip  ==> $aip \n   afl ==> $afl")

        if (aip != "null") {
            aipBinary = Conversions.hexToBin(aip).let { it!!.substring(0, 8) }
            console.log("aip binary", aipBinary)
        }

        if (afl != "null") {
            makeReadRecord(isoDep)
        }
    }

    private fun makeReadRecord(isoDep: IsoDep) {
        var rrcArray = arrayOf("00B20114", "00B20214", "00B2011C", "00B2021C", "00B20124", "00B20224")
        var rrctlv = ""
        for (item in rrcArray) {
            var readRecordsByteArray = readRecordApdu(item)
            var result = isoDep.transceive(readRecordsByteArray)
            console.log("readRecordIsoResult", HexUtil.toHexString(result))
            if (result.size > 4) {
                rrctlv += HexUtil.toHexString(result)
//                var bertlvs = Conversions.parseBERTLV(result)
//                console.log("bertlv", bertlvs.toString())
            }
        }

        console.log("tlvrrc", rrctlv)

    }


// NOTE
// CDOL1 is only used in contactless application
// CDOL1 are mandatory data that the terminal sends to the card for GEN AC 1
    // if any of the CDOL1 data is missing the card terminates the transaction with error
// CDOL2 is used for GEN AC 2 which is mostly important for contact trans


    override fun onApplicationSelected(aid: ByteArray) {
        println("this is called")
       console.log("onAppSelectedCalled", HexUtil.toHexString(aid))
    }

}




//Card Data Authentication (CDA)
//Terminal Action Analysis (TAA)
//Cardholder Verification (e.g. PIN entry)
//Generate Application Cryptogram (AC)
//Transaction Certificate (TC) if applicable
//Terminal Risk Management (TRM) if applicable
//Issuer Authentication if applicable
//Issuer Script Processing if applicable