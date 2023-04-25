package com.lovisgod.nfcpos.utils.security


import com.lovisgod.nfcpos.data.model.CapkV2
import com.lovisgod.nfcpos.utils.*
import java.security.*
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.math.BigInteger
import java.util.*


object ODAAuthenticationHelper {
 val CAPK_INDEX = "8F"
 val ISSUER_PK_CERT = "90"


 data class DecryptedIssuerPkCert(val decryptedKey: String, val failed: Boolean)

 fun fetchCapk(aid: String, index: String): CapkV2? {
     console.log("rid", aid.take(10))
     console.log("index", index)
     val rid  = Conversions.HexStringToByteArray(aid.take(10))
     val indexByte = ByteUtil.hexStr2Byte(index)

     return EmvOptV2().checkCapkAvailability(rid, indexByte)
 }

 fun DecryptIssuerPKCert(issuerCertIn: String, capk: String): ByteArray? {
     try {
         // Issuer Public Key Certificate in bytes
         val issuerCert = Conversions.HexStringToByteArray(issuerCertIn)

// Certification Authority Public Key in bytes
         val caPubKey = ByteUtil.hexStr2Bytes(capk)

// Algorithm name
         val algorithm = "RSA/ECB/PKCS1Padding"

// Create a PublicKey object from the Certification Authority Public Key bytes
         val keyFactory = KeyFactory.getInstance("RSA")
         val caPublicKeySpec = X509EncodedKeySpec(caPubKey)
         val caPublicKey = keyFactory.generatePublic(caPublicKeySpec)

// Apply the recovery function to the Issuer Public Key Certificate using the Certification Authority Public Key
         val cipher = Cipher.getInstance(algorithm)
         cipher.init(Cipher.DECRYPT_MODE, caPublicKey)
         val recoveredData = cipher.doFinal(issuerCert)
         return recoveredData
     } catch (e: Exception) {
         e.printStackTrace()
         console.log("DECRYPT ISSUER KEY ERROR", e.message.toString())
         return null
     }
 }

    fun performCda(decryptedKey: ByteArray, issuerCertIn: String, capk: String): DecryptedIssuerPkCert {

        if (!issuerCertIn.length.equals(capk.length)) {
            return DecryptedIssuerPkCert("", true)
        }
        // Check if the Recovered Data Trailer is equal to 'BC'
        val recoveredDataHeader = decryptedKey.take(2).toString()
        val certificateFormat = decryptedKey.take(4).toString().substring(2, 4)
        val recoveredDataTrailer = decryptedKey.takeLast(2).toString()
        console.log("header", recoveredDataHeader)
        console.log("trailer", recoveredDataTrailer)
        console.log("format", certificateFormat)

        if (certificateFormat != "02") {
            // offline dynamic data authentication has failed
            return DecryptedIssuerPkCert(HexUtil.toHexString(decryptedKey), true)
        }
        if (recoveredDataHeader != "6A") {
            // offline dynamic data authentication has failed
            return DecryptedIssuerPkCert(HexUtil.toHexString(decryptedKey), true)
        }
        if (recoveredDataTrailer != "BC") {
            // Offline dynamic data authentication has failed
            return DecryptedIssuerPkCert(HexUtil.toHexString(decryptedKey), true)
        } else {
            return DecryptedIssuerPkCert(HexUtil.toHexString(decryptedKey), false)
        }
    }


    fun buildAndEncryptDataForCDA(decryptedIpcert: String, Aid: String,
                                  issuerPublicKeyRemainder: String,
                                  issuerPublicKeyExponent: String ) {
        val certificateFormat = "02"
        val data3 = decryptedIpcert.substring(4, 12)
        val data4 = decryptedIpcert.substring(12, 16)
        val data5 = decryptedIpcert.substring(16, 22)
        val data6 = decryptedIpcert.substring(22, 24)
        val data7 = decryptedIpcert.substring(24, 26)
        val data8 = decryptedIpcert.substring(26, 28)
        val data9 = decryptedIpcert.substring(28, 30)
        val data10issuerPublicKeyModulus = decryptedIpcert.substring(30, decryptedIpcert.length - 42)
        val iccPublicKeyCert = decryptedIpcert.substring(decryptedIpcert.length - 42, decryptedIpcert.length - 2)

        val concatenatedDataElements = certificateFormat + data3 + data4 +
                data5 + data6 + data7 + data8 +
                data9 + data10issuerPublicKeyModulus + issuerPublicKeyRemainder + issuerPublicKeyExponent

        val concatenatedDataElementsBytes = Conversions.HexStringToByteArray(concatenatedDataElements)
        val caPublicKeyBytes = Conversions.HexStringToByteArray(Aid)
        val sha1Digest = MessageDigest.getInstance("SHA-1")
        val messageDigest = sha1Digest.digest(concatenatedDataElementsBytes)
        val rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        val caPublicKey = KeyFactory.getInstance("RSA").generatePublic(X509EncodedKeySpec(caPublicKeyBytes))
        rsaCipher.init(Cipher.ENCRYPT_MODE, caPublicKey)
        val encryptedDigest = rsaCipher.doFinal(messageDigest)
        val result = HexUtil.toHexString(encryptedDigest).toUpperCase(Locale.ROOT)

        println("Recovered Data: $result")
    }
}