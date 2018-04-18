package xyz.painapp.pocketdoc.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.fragments.AboutFragment
import xyz.painapp.pocketdoc.fragments.HelpFragment
import xyz.painapp.pocketdoc.fragments.LegalFragment

class AboutActivity : AppCompatActivity(), AboutFragment.OnAboutFragmentInteractionListener {
    override fun onListItemClicked(item: String) {
        when (item) {
            "Legal" -> fragmentManager.beginTransaction().replace(R.id.about_fragment_container, LegalFragment()).addToBackStack(null).commit()
            "Help" -> fragmentManager.beginTransaction().replace(R.id.about_fragment_container, HelpFragment()).addToBackStack(null).commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // Set toolbar
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = getString(R.string.about_title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        fragmentManager.beginTransaction().replace(R.id.about_fragment_container, AboutFragment()).commit()
    }
}
