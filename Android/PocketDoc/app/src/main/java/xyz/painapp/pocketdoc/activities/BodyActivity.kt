package xyz.painapp.pocketdoc.activities

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import xyz.painapp.pocketdoc.fragments.BodyFragment
import xyz.painapp.pocketdoc.R

class BodyActivity : AppCompatActivity() {
    private var fragmentManager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body)

        fragmentManager = supportFragmentManager

        if (findViewById<View>(R.id.fragment_parent) != null && savedInstanceState == null) {
            val firstFragment = BodyFragment()


            firstFragment.arguments = intent.extras
            fragmentManager!!.beginTransaction().add(R.id.fragment_parent, firstFragment).commit()
        }

        // Set toolbar
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar) as Toolbar
        setSupportActionBar(toolbar)
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

    fun switchFragments(fragment: Fragment, tag: String, args: Bundle) {

        val transaction = fragmentManager!!.beginTransaction()

        fragment.arguments = args
        transaction.replace(R.id.fragment_parent, fragment, tag)


        if (fragment is BodyFragment) {
            fragmentManager!!.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        } else {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    override fun onBackPressed() {
        if (fragmentManager!!.backStackEntryCount > 0) {
            fragmentManager!!.popBackStack()
        } else {
            super.onBackPressed()
        }

    }


}
