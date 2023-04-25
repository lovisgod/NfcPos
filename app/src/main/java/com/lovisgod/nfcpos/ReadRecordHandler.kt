package com.lovisgod.nfcpos

import android.nfc.tech.IsoDep
import com.lovisgod.nfcpos.utils.Conversions
import com.lovisgod.nfcpos.utils.Conversions.Companion.hexToBin
import com.lovisgod.nfcpos.utils.HexUtil
import com.lovisgod.nfcpos.utils.TAGHelper
import com.lovisgod.nfcpos.utils.console
import com.lovisgod.nfcpos.utils.security.ODAAuthenticationHelper
import parse90

object ReadRecordHandler {

    fun initiate(afl: String, isoDep: IsoDep): String {
        var result = ""
        var byrress = byteArrayOf()
        val aflEntries =  getAFLEntries(afl)
        val commandList  = ArrayList<String> ()
        for (entry in aflEntries) {
            commandList.addAll(getReadRecordComand(entry))
        }

        console.log("read record commands", "${commandList}")

        for(readCommand in commandList) {
            val byteResult = isoDep.transceive(Conversions.HexStringToByteArray(readCommand))
            byrress += byteResult
            parse90(HexUtil.toHexString(byteResult))
            result += HexUtil.toHexString(byteResult)
        }


        console.log("read apdu response", result)

        return result
    }

    fun getReadRecordComand(AFLEntry: String): ArrayList<String> {
        val aflEntry = AFLEntry.chunked(2).map {
            it.toInt(16).toByte()
        }.toByteArray()
        val sfiValue = aflEntry[0] / 8 // Divide first byte by 8 to get SFI value
        println("SFI value: $sfiValue")

        val recordsToRead = getRecordCountFromAflEntry(AFLEntry)

        val realSfiP2Value = getSfiForP2(AFLEntry.chunked(2)[0].toString())

        val commandlist = ArrayList<String>()

        for (record in recordsToRead) {
            val command  = "00B2${record}${realSfiP2Value}00"
            console.log("command", command)
            commandlist.add(command)
        }

        return commandlist
    }

    fun getRecordCountFromAflEntry(aflEntry: String): ArrayList<String> {
        val aflChunked = aflEntry.chunked(2).map {
            it.toInt()
        }

        if (aflChunked[1] == aflChunked[2]) {
            return arrayListOf("0${aflChunked[1]}")
        } else {
            var recordToReadArray = ArrayList<String>()
            for (i in aflChunked[1]..aflChunked[2]) {
                recordToReadArray.add("0${i}")
            }
            return recordToReadArray
        }
    }

    fun getSfiForP2(sfi:String):String {
        // convert sfi to binary
        val sfiBinary = hexToBin(sfi)
        println(sfiBinary)
        // Get the first 5 bits of the binary value by shifting right 3 positions
        val sfiFirstFiveBits = sfiBinary?.substring(0, 5)

//        println(sfiFirstFiveBits)

// Shift the result 3 bits to the left to get the real SFI value
        val sfiValue = Integer.parseInt(sfiFirstFiveBits, 2).shl(3) + 4

        val sfiValueInHex = String.format("%02X", sfiValue)

// Print the real SFI value
//        println("Real SFI value: $sfiValueInHex")
        return sfiValueInHex
    }

    fun getAFLEntries(AFL: String): List<String> {
        return AFL.chunked(8)
    }
}