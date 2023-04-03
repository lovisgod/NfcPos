package com.lovisgod.nfcpos

interface EmvListener {
    fun onApplicationSelected(aid: ByteArray)
}