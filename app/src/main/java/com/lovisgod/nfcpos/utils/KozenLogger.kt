package com.lovisgod.nfcpos.utils

import android.util.Log

class KozenLogger private constructor(private val tag: String) {

    fun log(msg: String) = logInfo(msg)

    fun logInfo(info: String) = Log.d(tag, info)

    fun logErr(err: String) = Log.e(tag, err)

    fun logDebug(msg: String) = Log.d(tag, msg)

    fun logWarning(warning: String) = Log.w(tag, warning)

    companion object {

        fun with(tag: String) = KozenLogger(tag)

    }
}

