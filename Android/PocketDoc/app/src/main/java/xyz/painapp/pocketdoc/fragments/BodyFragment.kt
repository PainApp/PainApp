package xyz.painapp.pocketdoc.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import xyz.painapp.pocketdoc.activities.BodyActivity
import xyz.painapp.pocketdoc.R

/**
* Created by keyur on 10/16/17.
* Package: ${PACKAGE_NAME} as part of PocketDoc
*/

class BodyFragment : Fragment(), View.OnTouchListener, View.OnClickListener {
    private var flipped: Boolean = false
    private var bodyImageView : ImageView? = null
    private var flipButton : Button? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.body_view, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        bodyImageView = getView()!!.findViewById<ImageView>(R.id.body_image) as ImageView
        flipButton = getView()!!.findViewById<Button>(R.id.flip_body_button) as Button

        val resId: Int = if (!flipped) R.drawable.body_front else R.drawable.body_back

        bodyImageView!!.setImageResource(resId)

        bodyImageView!!.setOnTouchListener(this)
        flipButton!!.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        flipped = !flipped
        val resId: Int = if (!flipped) R.drawable.body_front else R.drawable.body_back

        bodyImageView!!.setImageResource(resId)

    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        Log.i("View", v.toString())
        when (v) {
            bodyImageView -> return imgButtonTouch(v, event)
            else -> v!!.performClick()
        }
        return true
    }

    private fun imgButtonTouch(v: View?, event: MotionEvent?): Boolean {
        val x : Int = event!!.x.toInt()
        val y : Int = event.y.toInt()
        val data = Bundle()

        val region = getBodyRegion(x, y)

        // TODO extract string constants
        data.putString("Region", region)

        (activity as BodyActivity).switchFragments(RegionFragment(), region, data)

        return true
    }

    // TODO figure out which region of the body was clicked
    private fun getBodyRegion(x: Int, y: Int): String {
        return "calf"
    }
}
