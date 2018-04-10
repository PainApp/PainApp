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
    var viewId: Int = 0
    var id: Int = 0
    var name: String = ""
    var specificRegionList: ArrayList<SpecificBodyRegion> = ArrayList()



    constructor(parcel: Parcel) : this() {
        viewId = parcel.readInt()
        id = parcel.readInt()
        name = parcel.readString()
        parcel.readTypedList(specificRegionList, SpecificBodyRegion.CREATOR)
    }


    constructor(jsonObject: JSONObject) : this() {
        try {
            this.id = jsonObject.getInt(ID_STR)
            this.name = jsonObject.getString(NAME_STR)
            this.viewId = REGION_ID_MAP[name]!!

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
        dest.writeInt(viewId)
        dest.writeInt(id)
        dest.writeString(name)
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
        //TODO Fix left/right issues
        val REGION_ID_MAP: HashMap<String, Int> = hashMapOf(
                "Front Hips" to R.id.front_hips_image_view,
                "Back Hips" to R.id.back_hips_image_view,
                "Front Elbow" to R.id.front_left_elbow_imageView,
                "Back Elbow" to R.id.back_left_elbow_imageView,
                "Front Feet" to R.id.front_feet_imageView,
                "Back Feet" to R.id.back_feet_imageView,
                "Front Hand" to R.id.front_left_hand_imageView,
                "Back Hand" to R.id.back_left_hand_imageView,
                "Front Upperarm" to R.id.front_left_upperarm_imageView,
                "Back Upperarm" to R.id.back_left_upperarm_imageView,
                "Front Forearm" to R.id.back_left_forearm_imageView,
                "Back Forearm" to R.id.back_left_upperarm_imageView,
                "Front Lowerlegs" to R.id.front_lowerlegs_imageView,
                "Back Lowerlegs" to R.id.back_calves_imageView,
                "Front Knees" to R.id.front_knees_imageView,
                "Back Knees" to R.id.back_knees_imageView,
                "Front Thighs" to R.id.front_thighs_imageView,
                "Back Thighs" to R.id.back_thighs_imageView,
                "Front Groin" to R.id.front_groin_imageView,
                "Back Groin" to R.id.back_butt_imageView,
                "Front Shoulder" to R.id.front_left_shoulder_imageView,
                "Back Shoulder" to R.id.back_left_shoulder_imageView,
                "Front Chest" to R.id.front_chest_imageView,
                "Back Chest" to R.id.back_back_imageView,
                "Front Head" to R.id.front_head_imageView)
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