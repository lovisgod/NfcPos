package com.lovisgod.nfcpos.utils



object BerTlvLogger {
    fun log(
        aPadding: String?,
        aTlv: BerTlvs,
        aLogger: IBerTlvLogger?
    ) {
        for (tlv in aTlv.list) {
            if (aPadding != null) {
                if (aLogger != null) {
                    log(aPadding, tlv, aLogger)
                }
            }
        }
    }

    fun log(
        aPadding: String,
        aTlv: BerTlv?,
        aLogger: IBerTlvLogger
    ) {
        if (aTlv == null) {
            aLogger.debug("{} is null", aPadding)
            return
        }
        if (aTlv.isConstructed() == true) {
            aLogger.debug(
                "{} [{}]", aPadding, HexUtil.toHexString(
                    aTlv.getTag()!!.bytes
                )
            )
            for (child in aTlv.getValues()!!) {
                log("$aPadding    ", child, aLogger)
            }
        } else {
            aLogger.debug(
                "{} [{}] {}", aPadding, HexUtil.toHexString(
                    aTlv.getTag()!!.bytes
                ), aTlv.getHexValue()
            )
        }
    }
}