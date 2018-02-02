package xyz.painapp.pocketdoc.entities

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

/**
 * Created by keyur on 2/1/18.
 * Package: xyz.painapp.pocketdoc.entities as part of PocketDoc
 */

class SpecificBodyRegion() : Parcelable {
    private var id: Int = 0
    private var name: String = ""
    private var symptomList: ArrayList<Symptom> = ArrayList()

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.id)
        dest.writeString(name)
        dest.writeTypedList(this.symptomList)
    }

    override fun describeContents(): Int {
        return 0
    }

    constructor(parcel: Parcel) : this() {
        this.id = parcel.readInt()
        this.name = parcel.readString()
        parcel.readTypedList(this.symptomList, Symptom.CREATOR)
    }

    /*
     * TODO Fix server code to look like this
     * { id: ,
     *   name: ,
     *   syndromeList: [ ]
     *  }
     */
    constructor(jsonObject: JSONObject) : this() {
        this.id = jsonObject.getInt("id")
        this.name = jsonObject.getString("name")
        val sList = jsonObject.getJSONArray("syndromeList")

        for (i: Int in 0..(sList.length() - 1)) {
            symptomList.add(Symptom(sList.getJSONObject(i)))
        }
    }

    companion object CREATOR : Parcelable.Creator<SpecificBodyRegion> {
        override fun createFromParcel(parcel: Parcel): SpecificBodyRegion {
            return SpecificBodyRegion(parcel)
        }

        override fun newArray(size: Int): Array<SpecificBodyRegion?> {
            return arrayOfNulls(size)
        }
    }

}