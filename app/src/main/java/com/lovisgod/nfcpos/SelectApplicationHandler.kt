package com.lovisgod.nfcpos

import android.nfc.tech.IsoDep
import android.os.Build
import androidx.annotation.RequiresApi
import com.lovisgod.nfcpos.data.model.FinalSelectApplicationData
import com.lovisgod.nfcpos.data.model.SelectPPSEData
import com.lovisgod.nfcpos.utils.Conversions
import com.lovisgod.nfcpos.utils.HexUtil
import com.lovisgod.nfcpos.utils.TAGHelper
import com.lovisgod.nfcpos.utils.console

object SelectApplicationHandler {

    enum class SelectApplicationData(val tag: String, val tagName: String) {
        FILE_CONTROL_INFORMATION_TEMPLATE("6F", "FILE_CONTROL_INFORMATION_TEMPLATE"),
        DEDICATED_FILE_NAME("84", "DEDICATED_FILE_NAME"),
        APPLICATION_IDENTIFIER("4F", "APPLICATION_IDENTIFIER"),
        APPLICATION_IDENTIFIER84("84", "APPLICATION_IDENTIFIER"),
        APPLICATION_PRIORITY_INDICATOR("87", "APPLICATION_PRIORITY_INDICATOR"),
        PROCESSING_OPTION_DATA_OBJECT_LIST("9F38", "PDOL")
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun selectByname(isoDep: IsoDep): SelectPPSEData {
        val data = HexUtil.convertStringToHex("2PAY.SYS.DDF01")
        console.log("select application data", data)
        val command  = "00A404000E${data}00"
        console.log("select application", command)
        val result = isoDep.transceive(Conversions.HexStringToByteArray(command))
        console.log("select application isoResult", HexUtil.toHexString(result))
        var bertlvs = Conversions.parseBERTLV(result)

        val tlv = bertlvs?.let { Conversions.findTLVTAG(it, "84") }
        val aid = tlv?.getHexValue().toString()
        println("AID is ::::: ${aid}")

        val selectPPSEData = SelectPPSEData(
            APPLICATION_IDENTIFIER = TAGHelper.getTagFromTlv(bertlvs, SelectApplicationData.APPLICATION_IDENTIFIER.tag),
            APPLICATION_PRIORITY_INDICATOR = TAGHelper.getTagFromTlv(bertlvs, SelectApplicationData.APPLICATION_PRIORITY_INDICATOR.tag)
        )

        return selectPPSEData

    }


    fun finalSelectApplication(isoDep: IsoDep, aid: String): FinalSelectApplicationData {
        val command = "00A4040007${aid}00"
        console.log("final select application", command)
        val result = isoDep.transceive(Conversions.HexStringToByteArray(command))
        console.log("final select application isoResult", HexUtil.toHexString(result))
        var bertlvs = Conversions.parseBERTLV(result)
        console.log("bertlv", bertlvs.toString())
        val aidGotten =
            TAGHelper.getTagFromTlv(bertlvs, SelectApplicationData.APPLICATION_IDENTIFIER84.tag)
        val pdol = TAGHelper.getTagFromTlv(
            bertlvs,
            SelectApplicationData.PROCESSING_OPTION_DATA_OBJECT_LIST.tag
        )

        return FinalSelectApplicationData(
            aid = aidGotten,
            pdol = pdol
        )
    }


}