import com.lovisgod.nfcpos.POSApplication
import com.payneteasy.tlv.*


fun parse90(xx: String) {
    val bytes: ByteArray =
        HexUtil.parseHex(xx)

    val parser = BerTlvParser()
    val tlvs: BerTlvs = parser.parse(bytes, 0, bytes.size)
    var tag90 = tlvs.find(BerTag(0X90)).hexValue
    println("tag 90 is ${tlvs.find(BerTag(0X90)).hexValue}")

    if (tag90 != null) {
        if (tag90.isNotEmpty()) {
            POSApplication.TAG_90 = tag90
        }
    }

}