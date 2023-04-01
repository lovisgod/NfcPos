package com.lovisgod.nfcpos.data.model;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class AID implements Parcelable, Serializable {
    private static final long serialVersionUID = -1L;
    public byte[] aid;
    public byte[] cvmLmt = new byte[6];
    public byte[] termClssLmt = new byte[6];
    public byte[] termClssOfflineFloorLmt = new byte[6];
    public byte[] termOfflineFloorLmt = new byte[6];
    public byte selFlag;
    public byte targetPer;
    public byte maxTargetPer;
    public byte[] floorLimit;
    public byte randTransSel;
    public byte velocityCheck;
    public byte[] threshold = new byte[4];
    public byte[] TACDenial = new byte[5];
    public byte[] TACOnline = new byte[5];
    public byte[] TACDefault = new byte[5];
    public byte[] AcquierId = new byte[6];
    public byte[] dDOL;
    public byte[] tDOL;
    public byte[] version = new byte[2];
    public byte rMDLen;
    public byte[] riskManData = new byte[8];
    public byte[] merchName = new byte[128];
    public byte[] merchCateCode = new byte[2];
    public byte[] merchId = new byte[16];
    public byte[] termId = new byte[8];
    public byte[] referCurrCode = new byte[]{1, 86};
    public byte referCurrExp;
    public byte[] referCurrCon = new byte[4];
    public byte clsStatusCheck;
    public byte zeroCheck;
    public byte kernelType;
    public byte paramType;
    public byte[] ttq = new byte[4];
    public byte[] kernelID;
    public byte extSelectSupFlg;
    public static final Creator<AID> CREATOR = new Creator<AID>() {
        public AID createFromParcel(Parcel source) {
            return new AID(source);
        }

        public AID[] newArray(int size) {
            return new AID[size];
        }
    };

    public AID() {
    }

    protected AID(Parcel in) {
        this.readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.aid = in.createByteArray();
        this.cvmLmt = in.createByteArray();
        this.termClssLmt = in.createByteArray();
        this.termClssOfflineFloorLmt = in.createByteArray();
        this.termOfflineFloorLmt = in.createByteArray();
        this.selFlag = in.readByte();
        this.targetPer = in.readByte();
        this.maxTargetPer = in.readByte();
        this.floorLimit = in.createByteArray();
        this.randTransSel = in.readByte();
        this.velocityCheck = in.readByte();
        this.threshold = in.createByteArray();
        this.TACDenial = in.createByteArray();
        this.TACOnline = in.createByteArray();
        this.TACDefault = in.createByteArray();
        this.AcquierId = in.createByteArray();
        this.dDOL = in.createByteArray();
        this.tDOL = in.createByteArray();
        this.version = in.createByteArray();
        this.rMDLen = in.readByte();
        this.riskManData = in.createByteArray();
        this.merchName = in.createByteArray();
        this.merchCateCode = in.createByteArray();
        this.merchId = in.createByteArray();
        this.termId = in.createByteArray();
        this.referCurrCode = in.createByteArray();
        this.referCurrExp = in.readByte();
        this.referCurrCon = in.createByteArray();
        this.clsStatusCheck = in.readByte();
        this.zeroCheck = in.readByte();
        this.kernelType = in.readByte();
        this.paramType = in.readByte();
        this.ttq = in.createByteArray();
        this.kernelID = in.createByteArray();
        this.extSelectSupFlg = in.readByte();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(this.aid);
        dest.writeByteArray(this.cvmLmt);
        dest.writeByteArray(this.termClssLmt);
        dest.writeByteArray(this.termClssOfflineFloorLmt);
        dest.writeByteArray(this.termOfflineFloorLmt);
        dest.writeByte(this.selFlag);
        dest.writeByte(this.targetPer);
        dest.writeByte(this.maxTargetPer);
        dest.writeByteArray(this.floorLimit);
        dest.writeByte(this.randTransSel);
        dest.writeByte(this.velocityCheck);
        dest.writeByteArray(this.threshold);
        dest.writeByteArray(this.TACDenial);
        dest.writeByteArray(this.TACOnline);
        dest.writeByteArray(this.TACDefault);
        dest.writeByteArray(this.AcquierId);
        dest.writeByteArray(this.dDOL);
        dest.writeByteArray(this.tDOL);
        dest.writeByteArray(this.version);
        dest.writeByte(this.rMDLen);
        dest.writeByteArray(this.riskManData);
        dest.writeByteArray(this.merchName);
        dest.writeByteArray(this.merchCateCode);
        dest.writeByteArray(this.merchId);
        dest.writeByteArray(this.termId);
        dest.writeByteArray(this.referCurrCode);
        dest.writeByte(this.referCurrExp);
        dest.writeByteArray(this.referCurrCon);
        dest.writeByte(this.clsStatusCheck);
        dest.writeByte(this.zeroCheck);
        dest.writeByte(this.kernelType);
        dest.writeByte(this.paramType);
        dest.writeByteArray(this.ttq);
        dest.writeByteArray(this.kernelID);
        dest.writeByte(this.extSelectSupFlg);
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "AidV2{aid=" + this.bytes2HexString(this.aid) + ", cvmLmt=" + this.bytes2HexString(this.cvmLmt) + ", termClssLmt=" + this.bytes2HexString(this.termClssLmt) + ", termClssOfflineFloorLmt=" + this.bytes2HexString(this.termClssOfflineFloorLmt) + ", termOfflineFloorLmt=" + this.bytes2HexString(this.termOfflineFloorLmt) + ", selFlag=" + this.selFlag + ", targetPer=" + this.targetPer + ", maxTargetPer=" + this.maxTargetPer + ", floorLimit=" + this.bytes2HexString(this.floorLimit) + ", randTransSel=" + this.randTransSel + ", velocityCheck=" + this.velocityCheck + ", threshold=" + this.bytes2HexString(this.threshold) + ", TACDenial=" + this.bytes2HexString(this.TACDenial) + ", TACOnline=" + this.bytes2HexString(this.TACOnline) + ", TACDefault=" + this.bytes2HexString(this.TACDefault) + ", AcquierId=" + this.bytes2HexString(this.AcquierId) + ", dDOL=" + this.bytes2HexString(this.dDOL) + ", tDOL=" + this.bytes2HexString(this.tDOL) + ", version=" + this.bytes2HexString(this.version) + ", rMDLen=" + this.rMDLen + ", riskManData=" + this.bytes2HexString(this.riskManData) + ", merchName=" + this.bytes2HexString(this.merchName) + ", merchCateCode=" + this.bytes2HexString(this.merchCateCode) + ", merchId=" + this.bytes2HexString(this.merchId) + ", termId=" + this.bytes2HexString(this.termId) + ", referCurrCode=" + this.bytes2HexString(this.referCurrCode) + ", referCurrExp=" + this.referCurrExp + ", referCurrCon=" + this.bytes2HexString(this.referCurrCon) + ", clsStatusCheck=" + this.clsStatusCheck + ", zeroCheck=" + this.zeroCheck + ", kernelType=" + this.kernelType + ", paramType=" + this.paramType + ", ttq=" + this.bytes2HexString(this.ttq) + ", kernelID=" + this.bytes2HexString(this.kernelID) + ", extSelectSupFlg=" + this.extSelectSupFlg + '}';
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
