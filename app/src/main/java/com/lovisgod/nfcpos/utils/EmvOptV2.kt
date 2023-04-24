package com.lovisgod.nfcpos.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lovisgod.nfcpos.data.model.AID
import com.lovisgod.nfcpos.data.model.CapkV2
import com.pixplicity.easyprefs.library.Prefs
import java.lang.reflect.Type

class EmvOptV2 {

    fun addAid(aid: AID) = ConfigInfoHelper.saveAID(aid)
    fun addCapk(capkV2: CapkV2) = ConfigInfoHelper.saveCapk(capkV2)

    private fun getAllAid() = ConfigInfoHelper.readAID()
    private fun getAllCapk() = ConfigInfoHelper.readCAPK()

     fun checkAidAvailability(aid: ByteArray): AID? {
        val aids = getAllAid()
        val aid = aids.find { aidx ->
            aidx.aid.contentEquals(aid)
        }

        return aid

    }

    fun checkCapkAvailability(rid: ByteArray, index: Byte) : CapkV2? {
        val capks = getAllCapk()
        val capk  = capks.find {
//            console.log("got here", "got here ::::: ${it.index == index}")
            it.rid.contentEquals(rid) && it.index == index
        }

        if (capk != null) {
             console.log("capk capk", ByteUtil.bytes2HexStr(capk?.modul))
        }

        return capk
    }

    object ConfigInfoHelper {
        val CAPK_LIST = "CAPK_LIST"
        val AID_LIST = "AID_LIST"
        fun readCAPK(): ArrayList<CapkV2> {
            val dataString = Prefs.getString(CAPK_LIST)
            val type = object : TypeToken<ArrayList<CapkV2>>() {}.type
            return Gson().fromJson(dataString, type) ?: arrayListOf()
        }

        fun saveCapk(data: CapkV2) {
            var cache = readCAPK()
            if (cache != null) {
                cache.add(data)
                val gson = Gson()
                val type: Type = object : TypeToken<ArrayList<CapkV2?>?>() {}.type
                val dataToSave = gson.toJson(cache, type)
                println("capk to cache:::::: $dataToSave")
                Prefs.putString(CAPK_LIST, dataToSave)
            }
        }

        fun readAID(): ArrayList<AID> {
            val dataString = Prefs.getString(AID_LIST)
            val type = object : TypeToken<ArrayList<AID>>() {}.type
            return Gson().fromJson(dataString, type) ?: arrayListOf()
        }

        fun saveAID(data: AID) {
            var cache = readAID()
            if (cache != null) {
                cache.add(data)
                val gson = Gson()
                val type: Type = object : TypeToken<ArrayList<AID?>?>() {}.type
                val dataToSave = gson.toJson(cache, type)
                println("AID to cache:::::: $dataToSave")
                Prefs.putString("AID_LIST", dataToSave)
            }
        }
    }


}