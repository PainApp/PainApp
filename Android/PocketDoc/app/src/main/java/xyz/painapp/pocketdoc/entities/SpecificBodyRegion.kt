package xyz.painapp.pocketdoc.entities

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import org.json.JSONObject

/**
 * Created by keyur on 2/1/18.
 * Package: xyz.painapp.pocketdoc.entities as part of PocketDoc
 */

class SpecificBodyRegion() : Parcelable {
    var bodyRegionName: String = ""
    var bodyRegionId: Int = 0
    var id: Int = 0
    var name: String = ""
    var causeList: ArrayList<Cause> = ArrayList()


    constructor(parcel: Parcel) : this() {
        this.id = parcel.readInt()
        this.bodyRegionId = parcel.readInt()
        this.name = parcel.readString()
        this.bodyRegionName = parcel.readString()
        parcel.readTypedList(this.causeList, Cause.CREATOR)
    }

    constructor(jsonObject: JSONObject, bodyRegionId: Int, bodyRegionName: String) : this() {
        this.id = jsonObject.getInt("id")
        this.bodyRegionId = bodyRegionId
        this.bodyRegionName = bodyRegionName
        this.name = jsonObject.getString("name")
        if (jsonObject.has("causes")) {
            val sList = jsonObject.getJSONArray("causes")

            for (i: Int in 0 until sList.length()) {
                causeList.add(Cause(sList.getJSONObject(i)))
            }
        }
    }

    fun getFullName(): String {
        return this.name + " " + this.bodyRegionName
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.id)
        dest.writeInt(this.bodyRegionId)
        dest.writeString(name)
        dest.writeString(bodyRegionName)
        dest.writeTypedList(this.causeList)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun toJSONObject(): JSONObject {
        val res = JSONObject()
      //  res.put(BODY_REGION_STR, this.bodyRegionId)
        res.put(ID_STR, this.id)
        return res
    }



    companion object {
        const val S_REGION_STR = "specific_region"
        const val S_REGIONS_STR = "specific_regions"
        const val BODY_REGION_STR = "body_region_id"
        const val ID_STR = "id"
        @JvmField val CREATOR = object : Parcelable.Creator<SpecificBodyRegion> {
            override fun createFromParcel(parcel: Parcel): SpecificBodyRegion {
                return SpecificBodyRegion(parcel)
            }

            override fun newArray(size: Int): Array<SpecificBodyRegion?> {
                return arrayOfNulls(size)
            }
        }
    }

}