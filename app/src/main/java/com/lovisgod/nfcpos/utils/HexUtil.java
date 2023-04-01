package com.lovisgod.nfcpos.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.nio.charset.StandardCharsets;

public class HexUtil {

    private static final char[] CHARS_TABLES = "0123456789ABCDEF".toCharArray();
    private static final byte[] BYTES = new byte[128];

    static {
        for (int i = 0; i < 10; i++) {
            BYTES['0' + i] = (byte) i;
            BYTES['A' + i] = (byte) (10 + i);
            BYTES['a' + i] = (byte) (10 + i);
        }
    }

    public static String toHexString(byte[] aBytes) {
        return toHexString(aBytes, 0, aBytes.length);
    }

    public static String toFormattedHexString(byte[] aBytes) {
        return toFormattedHexString(aBytes, 0, aBytes.length);
    }

    public static String toHexString(byte[] aBytes, int aLength) {
        return toHexString(aBytes, 0, aLength);
    }

    public static byte[] parseHex(String aHexString) {
        char[] src = aHexString.replace("\n", "").replace(" ", "").toUpperCase().toCharArray();
        byte[] dst = new byte[src.length / 2];

        for (int si = 0, di = 0; di < dst.length; di++) {
            byte high = BYTES[src[si++] & 0x7f];
            byte low = BYTES[src[si++] & 0x7f];
            dst[di] = (byte) ((high << 4) + low);
        }
        return dst;
    }

    public static String toFormattedHexString(byte[] aBytes, int aOffset, int aLength) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(aLength);
        sb.append("] :");
        for (int si = aOffset, di = 0; si < aOffset + aLength; si++, di++) {
            byte b = aBytes[si];
            if (di % 4 == 0) {
                sb.append("  ");
            } else {
                sb.append(' ');
            }
            sb.append(CHARS_TABLES[(b & 0xf0) >>> 4]);
            sb.append(CHARS_TABLES[(b & 0x0f)]);
        }
        return sb.toString();
    }

    public static String toHexString(byte[] aBytes, int aOffset, int aLength) {
        char[] dst = new char[aLength * 2];

        for (int si = aOffset, di = 0; si < aOffset + aLength; si++) {
            byte b = aBytes[si];
            dst[di++] = CHARS_TABLES[(b & 0xf0) >>> 4];
            dst[di++] = CHARS_TABLES[(b & 0x0f)];
        }
        return new String(dst);
    }

    public static String fillString(String formatString, int length, char fillChar, boolean leftFillFlag) {
        if (null == formatString) {
            formatString = "";
        }
        int strLen = formatString.length();
        if (strLen >= length) {
            if (true == leftFillFlag) {
                return formatString.substring(strLen - length, strLen);
            } else {
                return formatString.substring(0, length);
            }
        } else {
            StringBuffer sbuf = new StringBuffer();
            int fillLen = length - formatString.length();
            for (int i = 0; i < fillLen; i++) {
                sbuf.append(fillChar);
            }

            if (true == leftFillFlag) {
                sbuf.append(formatString);
            } else {
                sbuf.insert(0, formatString);
            }
            String returnString = sbuf.toString();
            sbuf = null;
            return returnString;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String convertStringToHex(String str) {
        String chars = toHexString(str.getBytes(StandardCharsets.UTF_8));
        return chars;
    }
}
