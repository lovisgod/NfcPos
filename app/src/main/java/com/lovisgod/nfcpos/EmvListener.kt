package com.lovisgod.nfcpos

import com.lovisgod.nfcpos.data.model.FinalSelectApplicationData
import com.lovisgod.nfcpos.data.model.GpoResponseData

interface EmvListener {
    fun onApplicationSelected(aid: ByteArray)
}

interface CoreEmvListner {
    fun onFinalSelection(finalSelectApplicationData: FinalSelectApplicationData)
    fun sendReadRecords(gpoResponseData: GpoResponseData)
}