package com.lovisgod.nfcpos

import android.nfc.tech.IsoDep
import com.lovisgod.nfcpos.data.model.FinalSelectApplicationData
import com.lovisgod.nfcpos.data.model.GpoResponseData
import com.lovisgod.nfcpos.utils.Conversions
import com.lovisgod.nfcpos.utils.HexUtil
import com.lovisgod.nfcpos.utils.console

object GetGpoHandler {

    fun getGPo(finalSelectApplicationData: FinalSelectApplicationData, isoDep: IsoDep): GpoResponseData {
        var pdolCommand = "80A80000028300"
        var dataneeded = ""
        if (finalSelectApplicationData.pdol == "null") {
            console.log("", "there is no pdol data")
            pdolCommand = "80A80000028300"
        }else {
            console.log("pdol", "there is pdol data:::: ${finalSelectApplicationData.pdol}")
            dataneeded = getPdolData(finalSelectApplicationData.pdol)
            pdolCommand = "80A80000${dataneeded}00"
        }
        console.log("pdol command", pdolCommand)
        var pdolByteArray = Conversions.HexStringToByteArray(pdolCommand)
        var result = isoDep.transceive(pdolByteArray)
        console.log("gpoIsoResult", HexUtil.toHexString(result))

        var bertlvs = Conversions.parseBERTLV(result)
        console.log("bertlv", bertlvs.toString())

        var aip = bertlvs?.let { Conversions.findTLVTAG(it, "82") }?.getHexValue().toString()
        var afl = bertlvs?.let { Conversions.findTLVTAG(it, "94") }?.getHexValue().toString()
        console.log("gpo result", "aip  ==> $aip \n   afl ==> $afl")
        var aipBinary = ""
        if (aip != "null") {
             aipBinary = Conversions.hexToBin(aip).let { it!!.substring(0, 8) }
            console.log("aip binary", aipBinary)
        }

        return GpoResponseData(aipBinary, afl)
    }

    private fun getPdolDataForTag(pdol: String): String{
        return when(pdol) {
             "9F1A" -> "0566" // TERMINAL COUNTRY CODE WILL GOTTEN FROM CONFIG
             "9F33" -> "E0F8C8" // TERMINAL CAPABILITIES WILL BE GOTTEN FROM CONFIG
             "5F2A" -> "0566" // TRANSACTION CURRENCY CODE WILL BE GOTTEN FROM CONFIG
             "9F66" -> "FFC0C880"	// Terminal Transaction Qualifiers (TTQ) Research this later
             "9F02" -> "000000001000"	//Amount, Authorised (Numeric)
             "9F03" ->	"000000000000" // Amount, Other (Numeric) // Get amount from transaction
             "95"   -> "0000000000"	// Terminal Verification Results (TVR)
             "9A"	-> "230405"	// Transaction Date (YYMMDD)
             "9C"	-> "00"	// Transaction Type
             "9F37" -> "35986756"
             "C7"   -> ""
             else -> { ""}
        }

    }

    private fun getPdolData(pdol: String): String {
        var pdolData = ""
        console.log("pdol list",pdol)
        val pdolEntries = getTagsFrom9F38(pdol)
        console.log("pdolentries", "${pdolEntries}")
        for (entry in pdolEntries) {
            val data = getPdolDataForTag(entry)
            pdolData += data
        }
        val lenghtPdolData = Integer.toHexString((pdolData.length)/2)
        val pdolDatawith80andLength = "83${lenghtPdolData}$pdolData"
        val legnthPdolewith80 = Integer.toHexString((pdolDatawith80andLength.length)/2)
        val pdolDataToreturn = "${legnthPdolewith80}${pdolDatawith80andLength}"
        console.log("pdol data", pdolDataToreturn)
        return pdolDataToreturn
    }


    fun getTagsFrom9F38(tag9F38: String): List<String> {
//        val hexString = "9F389F66049F02069F03069F1A0295055F2A029A039C019F3704"
        val pattern = "(9F38|9F66|9F02|9F03|9F1A|9F37|9A|9C|95|5F2A|C7)".toRegex()
        val tags = pattern.findAll(tag9F38).map { it.value }.toList()
        println(tags)
        return tags
    }

}