package xyz.painapp.pocketdoc.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import xyz.painapp.pocketdoc.entities.DrawerItem
import xyz.painapp.pocketdoc.R

/**
* Created by keyur on 11/8/17.
* Package: ${PACKAGE_NAME} as part of PocketDoc
*/

class DrawerListAdapter(context: Context, resource: Int, objects: Array<DrawerItem>) : ArrayAdapter<DrawerItem>(context, resource, objects) {
    var resource: Int
    var list: Array<DrawerItem>
    var inflater: LayoutInflater

    init {
        this.resource = resource
        this.list = objects
        this.inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    private class ViewHolder(var textView: TextView, var imageView: ImageView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val holder: ViewHolder
        val rowItem: DrawerItem = getItem(position)
        val v: View
        val tv: TextView
        val iv: ImageView

        if (convertView == null) {
            v = this.inflater.inflate(R.layout.drawer_list_item, null)
            tv = v.findViewById(R.id.drawer_text)
            iv = v.findViewById(R.id.drawer_icon)
            holder = ViewHolder(tv, iv)

            v.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            v = convertView
        }

        holder.textView.text = rowItem.text
        holder.imageView.setImageResource(rowItem.image_id)


        return v
    }
}