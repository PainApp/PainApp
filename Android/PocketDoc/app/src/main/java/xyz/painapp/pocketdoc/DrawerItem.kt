package xyz.painapp.pocketdoc

/**
 * Created by keyur on 11/8/17.
 */
class DrawerItem(text: String, image_id: Int) {
    var text: String
    var image_id: Int

    init {
        this.text = text
        this.image_id = image_id
    }


    companion object {
        fun createFromArray(textArray: Array<String>, imageArray: Array<Int>) : Array<DrawerItem> {
            if (textArray.size != imageArray.size) throw IllegalArgumentException("Arrays must be the same length")
            return Array(textArray.size, { i -> DrawerItem (textArray[i], imageArray[i])})

        }
    }


}