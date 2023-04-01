package com.lovisgod.nfcpos.data.model

import android.os.Parcel
import android.os.Parcelable

import org.simpleframework.xml.Element

class SelectPPSEData(
        var FILE_CONTROL_INFORMATION_TEMPLATE: String = "",
        var DEDICATED_FILE_NAME: String = "000000000000",
        var APPLICATION_IDENTIFIER: String = "",
        var APPLICATION_PRIORITY_INDICATOR: String = "",
): Parcelable {

        constructor(parcel: Parcel) : this(
                parcel.readString().toString(),
                parcel.readString().toString(),
                parcel.readString().toString(),
                parcel.readString().toString()
        ) {

        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(FILE_CONTROL_INFORMATION_TEMPLATE)
                parcel.writeString(DEDICATED_FILE_NAME)
                parcel.writeString(APPLICATION_IDENTIFIER)
                parcel.writeString(APPLICATION_PRIORITY_INDICATOR)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<SelectPPSEData> {
                override fun createFromParcel(parcel: Parcel): SelectPPSEData {
                        return SelectPPSEData(parcel)
                }

                override fun newArray(size: Int): Array<SelectPPSEData?> {
                        return arrayOfNulls(size)
                }
        }

}

