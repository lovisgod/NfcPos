package com.lovisgod.nfcpos.utils

import java.lang.IllegalArgumentException
import kotlin.experimental.and

class Conversions {

    companion object {

        /**
         * Utility method to convert a hexadecimal string to a byte string.
         *
         *
         * Behavior with input strings containing non-hexadecimal characters is undefined.
         *
         * @param s String containing hexadecimal characters to convert
         * @return Byte array generated from input
         * @throws java.lang.IllegalArgumentException if input length is incorrect
         */
        @Throws(IllegalArgumentException::class)
        fun HexStringToByteArray(s: String): ByteArray {
            val len = s.length
            require(len % 2 != 1) { "Hex string must have even number of characters" }
            val data = ByteArray(len / 2) // Allocate 1 byte per 2 hex characters
            var i = 0
            while (i < len) {

                // Convert each character into a integer (base-16), then bit-shift into place
                data[i / 2] = ((Character.digit(s[i], 16) shl 4)
                        + Character.digit(s[i + 1], 16)).toByte()
                i += 2
            }
            return data
        }

        /**
         * Utility method to convert a byte array to a hexadecimal string.
         *
         * @param bytes Bytes to convert
         * @return String, containing hexadecimal representation.
         */
        fun ByteArrayToHexString(bytes: ByteArray): String? {
            val hexArray = charArrayOf(
                '0',
                '1',
                '2',
                '3',
                '4',
                '5',
                '6',
                '7',
                '8',
                '9',
                'A',
                'B',
                'C',
                'D',
                'E',
                'F'
            )
            val hexChars = CharArray(bytes.size * 2) // Each byte has two hex characters (nibbles)
            var v: Int
            for (j in bytes.indices) {
                v = (bytes[j] and 0xFF.toByte()).toInt() // Cast bytes[j] to int, treating as unsigned value
                hexChars[j * 2] = hexArray[v ushr 4] // Select hex character from upper nibble
                hexChars[j * 2 + 1] = hexArray[v and 0x0F] // Select hex character from lower nibble
            }
            return String(hexChars)
        }
    }
}