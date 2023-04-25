package com.lovisgod.nfcpos

import android.content.Context
import android.content.ContextWrapper
import com.lovisgod.nfcpos.utils.security.ODAAuthenticationHelper
import com.pixplicity.easyprefs.library.Prefs

object POSApplication {

    fun initialize(context: Context) {
        Prefs.Builder()
            .setContext(context)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName("com.lovisgod.nfcpos")
            .setUseDefaultSharedPreference(true)
            .build()
    }

    var AID_SELECTED = ""
    var TAG_90 = ""
    var ODA_RESULT = ODAAuthenticationHelper.DecryptedIssuerPkCert("", false)
}