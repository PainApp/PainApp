package xyz.painapp.pocketdoc.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import xyz.painapp.pocketdoc.R

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var actionListView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionListView = findViewById<ListView>(R.id.main_action_list) as ListView
        actionListView.adapter = ArrayAdapter.createFromResource(this, R.array.main_action_list, R.layout.main_action_list_item)
        actionListView.onItemClickListener = this

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = actionListView.getItemAtPosition(position) as String

        // TODO add in handlers for other activities
        val intent : Intent? =
                if (item == "start") {
                    Intent(this, BodyActivity::class.java)
                } else {
                    null
                }

        if (intent != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "That action isn't supported yet!", Toast.LENGTH_SHORT).show()
        }
    }
}
