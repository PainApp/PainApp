package xyz.painapp.pocketdoc.entities

/**
* Created by keyur on 11/8/17.
* Package: ${PACKAGE_NAME} as part of PocketDoc
*/
class DrawerItem(var text: String, var image_id: Int) {


    companion object {
        fun createFromArray(textArray: Array<String>, imageArray: Array<Int>) : Array<DrawerItem> {
            if (textArray.size != imageArray.size) throw IllegalArgumentException("Arrays must be the same length")
            return Array(textArray.size, { i -> DrawerItem(textArray[i], imageArray[i]) })

        }
    }


}