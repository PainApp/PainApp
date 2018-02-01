package xyz.painapp.pocketdoc.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import xyz.painapp.pocketdoc.R

class BodyActivity : AppCompatActivity(), View.OnClickListener {

    private var flipped: Boolean = false
    private var bodyConstraintLayout : ConstraintLayout? = null
    private var flipButton : Button? = null
    private val regionMap: HashMap<Int, Int> = HashMap()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body)

        // Set toolbar
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        regionMap[R.id.front_hips_image_view] = 0
        regionMap[R.id.front_left_forearm_imageView] = 1
        regionMap[R.id.front_right_forearm_imageView] = 1
        regionMap[R.id.front_feet_imageView] = 2

        bodyConstraintLayout = findViewById(R.id.body_constraintLayout)


        flipButton!!.setOnClickListener(this)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.body_activity_options_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.search_btn -> Toast.makeText(this, "You clicked search", Toast.LENGTH_SHORT).show() // TODO add search handler
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {

        var intent = Intent(this, RegionActivity::class.java)

        when (v!!.id) {
            R.id.flip_body_button -> return
            else -> {
                if (regionMap.keys.contains(v.id)) {
                    intent.putExtra("Region", regionMap[v.id])
                    startActivity(intent)
                }
            }
        }
    }

    // TODO figure out which region of the body was clicked
    private fun getBodyRegion(x: Int, y: Int): String {
        return "calf"
    }


}
