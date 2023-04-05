package com.lovisgod.nfcpos.data.model

data class AIPDECODED (
    var SDA_SUPPORTED : Boolean = false,
    var DDA_SUPPORTED: Boolean = false,
    var CARD_HOLDER_VERIFICATION: Boolean = false,
    var TERMINAL_RISK_TO_BE_PERFORMED: Boolean = false,
    var ISSUER_AUTHENTICATION_TO_BE_PERFORMED: Boolean = false,
    var CDA_SUPPORTED: Boolean = false
) {
    override fun toString(): String {
        return "" +
                "SDA_SUPPORTED:::: ${this.SDA_SUPPORTED}\n" +
                "DDA_SUPPORTED:::: ${this.DDA_SUPPORTED}\n" +
                "CARD_HOLDER_VERIFICATION:::: ${this.CARD_HOLDER_VERIFICATION}\n" +
                "TERMINAL_RISK_TO_BE_PERFORMED:::: ${this.TERMINAL_RISK_TO_BE_PERFORMED}\n" +
                "ISSUER_AUTHENTICATION_TO_BE_PERFORMED:::: ${this.ISSUER_AUTHENTICATION_TO_BE_PERFORMED}\n" +
                "CDA_SUPPORTED:::: ${this.CDA_SUPPORTED}\n"
    }
}