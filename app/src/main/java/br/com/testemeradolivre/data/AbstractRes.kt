package br.com.testemeradolivre.data

import android.os.Parcel
import android.os.Parcelable

open class AbstractRes : Parcelable {

    var statusCode: Int = 0
    var mensagem: String? = null

    constructor() {}


    protected constructor(`in`: Parcel) {
        mensagem = `in`.readString()
        statusCode = `in`.readInt()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(mensagem)
        dest.writeInt(statusCode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AbstractRes> {
        override fun createFromParcel(parcel: Parcel): AbstractRes {
            return AbstractRes(parcel)
        }

        override fun newArray(size: Int): Array<AbstractRes?> {
            return arrayOfNulls(size)
        }
    }
}
