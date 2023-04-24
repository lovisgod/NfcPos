package com.lovisgod.nfcpos

import android.nfc.tech.IsoDep
import android.os.Build
import androidx.annotation.RequiresApi
import com.lovisgod.nfcpos.data.model.AIPDECODED
import com.lovisgod.nfcpos.data.model.FinalSelectApplicationData
import com.lovisgod.nfcpos.data.model.GpoResponseData
import com.lovisgod.nfcpos.data.model.NFCPOSEXCEPTION
import com.lovisgod.nfcpos.utils.*
import com.lovisgod.nfcpos.utils.security.ODAAuthenticationHelper

@RequiresApi(Build.VERSION_CODES.KITKAT)
class EmvHandler(val emvListener: EmvListener) {
    val emvOptV2 = EmvOptV2()

    lateinit var  coreEmvListner : CoreEmvHanlder


    fun start(isoDep: IsoDep) {
        coreEmvListner = CoreEmvHanlder(emvListener, isoDep)
        selectApplication(isoDep)
    }

    private fun selectApplication(isoDep: IsoDep) {
        try {
            val selectPPSEData = SelectApplicationHandler.selectByname(isoDep)
            println("AID ======= ${selectPPSEData.APPLICATION_IDENTIFIER}, " +
                    "======API ==== ${selectPPSEData.APPLICATION_PRIORITY_INDICATOR}")

            val mutuallySupportedAid = emvOptV2.checkAidAvailability(
                ByteUtil.hexStr2Bytes(selectPPSEData.APPLICATION_IDENTIFIER)
            )

            if (mutuallySupportedAid !== null) {
                val aidSelected = ByteUtil.bytes2HexStr(mutuallySupportedAid.aid)
                println("selected aid ::::: $aidSelected")
                selectFinalSelection(isoDep, aidSelected)
            } else {
                throw NFCPOSEXCEPTION("Aid not found")
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }


    private fun selectFinalSelection(isoDep: IsoDep, aid: String) {
        try {
            val finalSelectApplicationData = SelectApplicationHandler.finalSelectApplication(isoDep, aid)
            console.log("final select data",
                "aid::::: ${finalSelectApplicationData.aid}::::: pdol::::::${finalSelectApplicationData.pdol}")
            if (finalSelectApplicationData.aid.isNotEmpty()) {
                emvListener.onApplicationSelected(ByteUtil.hexStr2Bytes(finalSelectApplicationData.aid))
                coreEmvListner.onFinalSelection(finalSelectApplicationData)
            }else {
                throw  NFCPOSEXCEPTION("AID Not found")
            }
        }catch (e: Exception) {
            println(e.message)
        }
    }


     inner class CoreEmvHanlder(val emvListener: EmvListener, val isoDep: IsoDep): CoreEmvListner {

        var aipdecoded = AIPDECODED()
        override fun onFinalSelection(finalSelectApplicationData: FinalSelectApplicationData) {
           try {
               POSApplication.AID_SELECTED = finalSelectApplicationData.aid
               val gpoResponseData = GetGpoHandler.getGPo(finalSelectApplicationData, isoDep)
               if (gpoResponseData !=null) {
                   aipdecoded = gpoResponseData.AIP?.let { decodeAip(it) }!!
                   console.log("aip_decoded", aipdecoded.toString())
                   gpoResponseData.AFL?.let { sendReadRecords(gpoResponseData) }

               } else {
                   throw NFCPOSEXCEPTION("GPO ERROR while getting GPO")
               }
           } catch (e:Exception) {
               console.log("GPO ERROR", e.message.toString())
           }
        }

         override fun sendReadRecords(gpoResponseData: GpoResponseData) {
             gpoResponseData.AFL?.let {
                 val result = ReadRecordHandler.initiate(it, isoDep)
                 val resultBytes = ByteUtil.hexStr2Bytes(result)
                 if (resultBytes != null) { startODA(resultBytes)}
             }
         }

         override fun startODA(readRecordResult: ByteArray) {
             var bertlvs = Conversions.parseBERTLV(readRecordResult)
             val capkIndex =
                 bertlvs?.let { it1 -> TAGHelper.getTagFromTlv(bertlvs, ODAAuthenticationHelper.CAPK_INDEX)
                 }
             console.log("capk index", capkIndex.toString())
             if (capkIndex != null) {
                 val capkChecked = ODAAuthenticationHelper.fetchCapk(POSApplication.AID_SELECTED, capkIndex)
                 console.log("capk checked",ByteUtil.bytes2HexStr( capkChecked?.modul))
             }
         }


         private fun decodeAip(aipBinary: String): AIPDECODED {
            val splittedAip = aipBinary.split("")
            val aipDecoded = AIPDECODED()
            aipDecoded.apply {
                SDA_SUPPORTED = splittedAip.get(2) == "1"
                DDA_SUPPORTED = splittedAip.get(3) == "1"
                CARD_HOLDER_VERIFICATION = splittedAip.get(4) == "1"
                TERMINAL_RISK_TO_BE_PERFORMED = splittedAip.get(5) == "1"
                ISSUER_AUTHENTICATION_TO_BE_PERFORMED = splittedAip.get(6) == "1"
                CDA_SUPPORTED = splittedAip.get(8) == "1"
            }
            return  aipDecoded
        }


    }

}