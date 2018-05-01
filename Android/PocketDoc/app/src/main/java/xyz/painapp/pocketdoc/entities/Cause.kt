package xyz.painapp.pocketdoc.entities

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONArray
import org.json.JSONObject
import xyz.painapp.pocketdoc.R

/**
 * Created by keyur on 2/1/18.
 * Package: xyz.painapp.pocketdoc.entities as part of PocketDoc
 */

class Cause() : Parcelable {
    private var id: Int = 0
    var name: String = ""
    private var classification: String = DEFAULT_VAL
    set(value) {
        if (value in CLASSIFICATION_LIST.keys) {
            field = value
        } else {
            throw IllegalArgumentException("Classification value must be in classification list")
        }
    }

    constructor(parcel: Parcel) : this() {
        this.id = parcel.readInt()
        this.name = parcel.readString()
        this.classification = parcel.readString()
    }

    constructor(jsonObject: JSONObject) : this() {
        this.id = jsonObject.getInt("id")
        this.name = jsonObject.getString("name")
        this.classification = jsonObject.getString("classification")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(this.id)
        parcel.writeString(this.name)
        parcel.writeString(this.classification)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun getColor(): Int {
        return if (classification in CLASSIFICATION_LIST) {
            CLASSIFICATION_LIST[classification]!!
        } else {
            R.color.gray
        }
    }

    companion object {
        private const val EXTREME_STR: String = "extreme" //red
        private const val CHILDREN_STR: String = "children" //green
        private const val FEMALE_STR: String = "female" // pink
        private const val OLD_STR: String = "old" // purple
        private const val DEFAULT_VAL: String = "default"
        const val CAUSES_STR: String = "causes"

        val CLASSIFICATION_LIST: HashMap<String, Int> = hashMapOf(EXTREME_STR to R.color.red, CHILDREN_STR to R.color.green, FEMALE_STR to R.color.pink, OLD_STR to R.color.purple, DEFAULT_VAL to R.color.white)

        fun fromJSONArray(jsonArray: JSONArray) : ArrayList<Cause> {
            val causes: ArrayList<Cause> = ArrayList()

            (0 until jsonArray.length()).mapTo(causes) { Cause(jsonArray.getJSONObject(it)) }
            return causes
        }

        @JvmField val CREATOR = object: Parcelable.Creator<Cause> {
            override fun createFromParcel(parcel: Parcel): Cause {
                return Cause(parcel)
            }

            override fun newArray(size: Int): Array<Cause?> {
                return arrayOfNulls(size)
            }
        }
    }

}