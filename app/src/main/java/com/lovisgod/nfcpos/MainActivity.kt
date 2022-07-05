package com.lovisgod.nfcpos

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lovisgod.nfcpos.utils.Conversions
import com.lovisgod.nfcpos.utils.console
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private lateinit var mNfcAdapter: NfcAdapter
    private lateinit var intentFiltersArray: Array<IntentFilter>
    private lateinit var techListsArray: Array<Array<String>>
    private lateinit var pendingIntent: PendingIntent

    val AID = "A0000000043060"

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
            onTagDiscovered(tagFromIntent)
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

     fun onTagDiscovered(tag: Tag) {
        val isoDep = IsoDep.get(tag);
        if (isoDep != null) {
            try {
                isoDep.connect()
                var result = isoDep.transceive(selectApdu(SelectAID))

                // after this step we move to other EMV steps and stages
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
                isoDep.close()
                console.log("isodeperror", e.localizedMessage.toString())
            }
        }
    }
}