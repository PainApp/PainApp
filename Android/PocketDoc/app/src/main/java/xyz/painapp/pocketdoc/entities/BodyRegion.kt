package xyz.painapp.pocketdoc.entities

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

/**
 * Created by keyur on 2/1/18.
 * Package: xyz.painapp.pocketdoc.entities as part of PocketDoc
 */
class BodyRegion() : Parcelable {
    private var id: Int = 0
    private var name: String = ""
    private var specificRegionList: ArrayList<SpecificBodyRegion> = ArrayList()


    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString()
        parcel.readTypedList(specificRegionList, SpecificBodyRegion.CREATOR)
    }

    /*
     * TODO Fix server code to make it look like this:
     * { id: ,
     *   name: ,
     *   sBodyRegionList: [ { id: , name: } ] }
     */
    constructor(jsonObject: JSONObject) : this() {
        this.id = jsonObject.getInt("id")
        this.name = jsonObject.getString("name")
        val sRegionList = jsonObject.getJSONArray("sBodyRegionList")

        for (i: Int in 0..(sRegionList.length() - 1)) {
            specificRegionList.add(SpecificBodyRegion(sRegionList.getJSONObject(i)))
        }
    }



    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeTypedList(specificRegionList)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<BodyRegion> {
        override fun createFromParcel(parcel: Parcel): BodyRegion {
            return BodyRegion(parcel)
        }

        override fun newArray(size: Int): Array<BodyRegion?> {
            return arrayOfNulls(size)
        }
    }
}