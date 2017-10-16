package xyz.painapp.pocketdoc;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class BodyActivity extends AppCompatActivity {
    private String[] drawerValueList;
    private DrawerLayout sidebarDrawerLayout;
    private ListView drawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        // Set toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        // Initialize sidebar drawer
        drawerValueList = getResources().getStringArray(R.array.drawer_list);
        sidebarDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        sidebarDrawerLayout.setStatusBarBackground(R.color.colorPrimary);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        // Set adapter for sidebar drawer and add actions
        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, drawerValueList));
        drawerList.setOnItemClickListener(new DrawerOnItemClickListener());
    }

    private void selectItem(int position) {
        String text = "You clicked " + drawerValueList[position];
        drawerList.setItemChecked(position, true);
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    private class DrawerOnItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }


}
