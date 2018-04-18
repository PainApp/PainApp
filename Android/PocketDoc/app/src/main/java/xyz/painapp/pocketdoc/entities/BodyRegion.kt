package xyz.painapp.pocketdoc.entities

import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import org.json.JSONArray
import org.json.JSONObject
import xyz.painapp.pocketdoc.R

/**
 * Created by keyur on 2/1/18.
 * Package: xyz.painapp.pocketdoc.entities as part of PocketDoc
 */
class BodyRegion() : Parcelable {
    var tag: String = ""
    var id: Int = 0
    var name: String = ""
    var specificRegionList: ArrayList<SpecificBodyRegion> = ArrayList()

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString()
        tag = parcel.readString()
        parcel.readTypedList(specificRegionList, SpecificBodyRegion.CREATOR)
    }

    constructor(jsonObject: JSONObject) : this() {
        try {
            this.id = jsonObject.getInt(ID_STR)
            this.name = jsonObject.getString(NAME_STR)
            if (name in REGION_TAG_LIST) {
                this.tag = name
            } else {
                throw IllegalArgumentException("Invalid tag name")
            }

            if (jsonObject.has(SpecificBodyRegion.S_REGIONS_STR)) {
                val sRegionList = jsonObject.getJSONArray(SpecificBodyRegion.S_REGIONS_STR)

                for (i: Int in 0..(sRegionList.length() - 1)) {
                    specificRegionList.add(SpecificBodyRegion(sRegionList.getJSONObject(i), this.id, this.name))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeString(tag)
        dest.writeTypedList(specificRegionList)
    }

    fun toJSONObject(): JSONObject {
        val res = JSONObject()
        res.put(ID_STR, this.id)
        return res
    }

    override fun describeContents() = 0

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<BodyRegion> {
            override fun createFromParcel(parcel: Parcel): BodyRegion = BodyRegion(parcel)
            override fun newArray(size: Int): Array<BodyRegion?> = arrayOfNulls(size)
        }
        val REGION_TAG_LIST: Array<String> =
                arrayOf("Front Hips",
                "Back Hips",
                "Front Elbow",
                "Back Elbow",
                "Front Feet",
                "Back Feet",
                "Front Hand",
                "Back Hand",
                "Front Upperarm",
                "Back Upperarm",
                "Front Forearm",
                "Back Forearm",
                "Front Lowerlegs",
                "Back Lowerlegs",
                "Front Knees",
                "Back Knees",
                "Front Thighs",
                "Back Thighs",
                "Front Groin",
                "Back Groin",
                "Front Shoulder",
                "Back Shoulder",
                "Front Chest",
                "Back Chest",
                "Front Head",
                "Front Abdomen",
                "Back Abdomen")
        const val BODY_REGIONS_STR = "body_regions"
        const val BODY_REGION_STR = "body_region"
        const val NAME_STR = "name"
        const val ID_STR = "id"
        fun fromJSONArray(jsonArray: JSONArray) : ArrayList<BodyRegion> {
            val bodyRegions: ArrayList<BodyRegion> = ArrayList()

            (0 until jsonArray.length()).mapTo(bodyRegions) { BodyRegion(jsonArray.getJSONObject(it)) }
            return bodyRegions
        }
    }
}