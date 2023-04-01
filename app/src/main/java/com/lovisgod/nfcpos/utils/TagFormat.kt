package com.lovisgod.nfcpos.utils

data class TagFormat (
   val tag: String,
   val name: String
)


object TAGHelper {

   fun getTagFromTlv(tlvs: BerTlvs?, tag: String): String {
     val tlv = tlvs.let { it?.let { it1 -> Conversions.findTLVTAG(it1, tag) } }
      return tlv?.getHexValue().toString()
   }
}