package xyz.painapp.pocketdoc;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BodyActivity extends AppCompatActivity {
    private String[] drawerValueList;
    private DrawerLayout sidebarDrawer;
    private ListView drawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        // Initialize sidebar drawer
        drawerValueList = getResources().getStringArray(R.array.drawer_list);
        sidebarDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerList = (ListView)findViewById(R.id.left_drawer);

        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, drawerValueList));


    }
}
