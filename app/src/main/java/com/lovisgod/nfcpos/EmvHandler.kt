package com.lovisgod.nfcpos

import android.nfc.tech.IsoDep
import android.os.Build
import androidx.annotation.RequiresApi
import com.lovisgod.nfcpos.data.model.NFCPOSEXCEPTION
import com.lovisgod.nfcpos.utils.ByteUtil
import com.lovisgod.nfcpos.utils.EmvOptV2
import com.lovisgod.nfcpos.utils.console

@RequiresApi(Build.VERSION_CODES.KITKAT)
class EmvHandler(val emvListener: EmvListener) {
    val emvOptV2 = EmvOptV2()

    fun start(isoDep: IsoDep) {
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
            }else {
                throw  NFCPOSEXCEPTION("AID Not found")
            }
        }catch (e: Exception) {
            println(e.message)
        }
    }
}