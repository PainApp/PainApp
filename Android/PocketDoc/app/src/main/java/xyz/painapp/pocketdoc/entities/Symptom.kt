package xyz.painapp.pocketdoc.entities

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

/**
 * Created by keyur on 2/1/18.
 * Package: xyz.painapp.pocketdoc.entities as part of PocketDoc
 */

class Symptom() : Parcelable {
    private var id: Int = 0
    private var name: String = ""

    constructor(parcel: Parcel) : this() {
        this.id = parcel.readInt()
        this.name = parcel.readString()
    }
    /*
     * TODO Fix server code to look like this
     * { id: ,
     *   name: ,
     *  }
     */
    constructor(jsonObject: JSONObject) : this() {
        this.id = jsonObject.getInt("id")
        this.name = jsonObject.getString("name")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(this.id)
        parcel.writeString(this.name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Symptom> {
        override fun createFromParcel(parcel: Parcel): Symptom {
            return Symptom(parcel)
        }

        override fun newArray(size: Int): Array<Symptom?> {
            return arrayOfNulls(size)
        }
    }

}