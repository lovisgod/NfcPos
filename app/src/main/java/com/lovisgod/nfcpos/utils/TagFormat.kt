package com.lovisgod.nfcpos.utils

data class TagFormat (
   val tag: String,
   val name: String
)


object TAGHelper {

   fun getTagFromTlv(tlvs: BerTlvs?, tag: String): String {
     val tlv = tlvs.let { it?.let { it1 -> Conversions.findTLVTAG(it1, tag) } }
       console.log("tlv", tlv?.getHexValue().toString())
      return tlv?.getHexValue().toString()
   }

    fun getTagFromTlvByte(tlvs: BerTlvs?, tag: ByteArray): String {
        val tlv = tlvs.let { it?.let { it1 -> Conversions.findTLVTAGByte(it1, tag) } }
        console.log("tlv", tlv?.getHexValue().toString())
        return tlv?.getHexValue().toString()
    }
}