package com.lovisgod.nfcpos.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class CapkV2 implements Parcelable, Serializable {
    private static final long serialVersionUID = -1L;
    public byte[] rid = new byte[5];
    public byte index;
    public byte hashInd;
    public byte arithInd;
    public byte[] modul;
    public byte[] exponent;
    public byte[] expDate = new byte[3];
    public byte[] checkSum = new byte[20];
    public static final Creator<CapkV2> CREATOR = new Creator<CapkV2>() {
        public CapkV2 createFromParcel(Parcel source) {
            return new CapkV2(source);
        }

        public CapkV2[] newArray(int size) {
            return new CapkV2[size];
        }
    };

    public CapkV2() {
    }

    protected CapkV2(Parcel in) {
        this.readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.rid = in.createByteArray();
        this.index = in.readByte();
        this.hashInd = in.readByte();
        this.arithInd = in.readByte();
        this.modul = in.createByteArray();
        this.exponent = in.createByteArray();
        this.expDate = in.createByteArray();
        this.checkSum = in.createByteArray();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(this.rid);
        dest.writeByte(this.index);
        dest.writeByte(this.hashInd);
        dest.writeByte(this.arithInd);
        dest.writeByteArray(this.modul);
        dest.writeByteArray(this.exponent);
        dest.writeByteArray(this.expDate);
        dest.writeByteArray(this.checkSum);
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "CapkV2{rid=" + this.bytes2HexString(this.rid) + ", index=" + this.index + ", hashInd=" + this.hashInd + ", arithInd=" + this.arithInd + ", modul=" + this.bytes2HexString(this.modul) + ", exponent=" + this.bytes2HexString(this.exponent) + ", expDate=" + this.bytes2HexString(this.expDate) + ", checkSum=" + this.bytes2HexString(this.checkSum) + '}';
    }

    private String bytes2HexString(byte... src) {
        if (src != null && src.length > 0) {
            StringBuilder sb = new StringBuilder();
            byte[] var3 = src;
            int var4 = src.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                byte b = var3[var5];
                String hex = Integer.toHexString(b & 255);
                if (hex.length() < 2) {
                    sb.append(0);
                }

                sb.append(hex);
            }

            return sb.toString().toUpperCase();
        } else {
            return "";
        }
    }
}

