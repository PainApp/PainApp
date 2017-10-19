package xyz.painapp.pocketdoc;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BodyActivity extends AppCompatActivity {
    private String[] drawerValueList;
    private DrawerLayout sidebarDrawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_parent) != null) {

            if (savedInstanceState == null) {
                BodyFragment firstFragment = new BodyFragment();

                firstFragment.setArguments(getIntent().getExtras());
                fragmentManager.beginTransaction().add(R.id.fragment_parent, firstFragment).commit();
            }
        }

        // Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        mDrawerTitle = mTitle = getTitle();

        // Initialize sidebar drawer
        drawerValueList = getResources().getStringArray(R.array.drawer_list);
        sidebarDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        sidebarDrawerLayout.setStatusBarBackground(R.color.colorPrimary);
        mDrawerToggle = new ActionBarDrawerToggle(this, sidebarDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        sidebarDrawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        // Set adapter for sidebar drawer and add actions
        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, drawerValueList));
        drawerList.setOnItemClickListener(new DrawerOnItemClickListener());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = sidebarDrawerLayout.isDrawerOpen(drawerList);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    private void clickItem(int position) {

        //getSupportActionBar().setTitle(drawerValueList[position]);
        if (drawerValueList[position].equals("Help") && !drawerList.isItemChecked(position)) {
            switchFragments(new HelpFragment());
        } else if (drawerValueList[position] == "Home"){
            switchFragments(new BodyFragment());
        }
        drawerList.setItemChecked(position, true);
        sidebarDrawerLayout.closeDrawer(Gravity.LEFT, true);
    }

    private void switchFragments(Fragment fragment) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.fragment_parent, fragment);

        if (fragment instanceof HelpFragment) {
            transaction.addToBackStack(null);
        } else if (fragment instanceof BodyFragment) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        transaction.commit();
    }

    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }

    }
    private class DrawerOnItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            clickItem(position);
        }
    }


}
