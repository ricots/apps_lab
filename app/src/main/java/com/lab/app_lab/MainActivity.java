package com.lab.app_lab;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getSimpleName();
    TextView txtnpm,txtnama;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    Fragment fragment = null;
    SharedPreferences sp;
    SharedPreferences.Editor spe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtnpm = (TextView) findViewById(R.id.txtnpm);

        sp = getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String npm = sp.getString(config.EMAIL_SHARED_PREF, "Not Available");
        txtnpm.setText(npm);


        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().
                    add(R.id.frame,
                            new home(),
                            home.class.getSimpleName()).commit();
        }

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){


                    //Replacing the main content with ContentFragment Which is our Inbox View;

                    case R.id.home_lab:
                        home fragment_home = new home();
                        android.support.v4.app.FragmentTransaction fragmentTransaction_home = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction_home.replace(R.id.frame, fragment_home);
                        fragmentTransaction_home.commit();
                        String title = "Aplikasi Lab";
                        getSupportActionBar().setTitle(title);
                        return true;

                    // For rest of the options we just show a toast on click
                    case R.id.berita_lab:
                        ContentFragment fragment = new ContentFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        String title_berita = "Berita Lab";
                        getSupportActionBar().setTitle(title_berita);
                        return true;
                    case R.id.jadwal_lab:
                        Jadwal fragment_jadwal = new Jadwal();
                        android.support.v4.app.FragmentTransaction lab_jadwal = getSupportFragmentManager().beginTransaction();
                        lab_jadwal.replace(R.id.frame,fragment_jadwal);
                        lab_jadwal.commit();
                        String title_jadwal = "Jadwal Lab";
                        getSupportActionBar().setTitle(title_jadwal);
                        return true;
                    case R.id.kritik_saran:
                        /*kritik_saran fragment_kritik = new kritik_saran();
                        android.support.v4.app.FragmentTransaction kritik = getSupportFragmentManager().beginTransaction();
                        kritik.replace(R.id.frame,fragment_kritik);
                        kritik.commit();
                        String title_kritik = "Kritik & saran";
                        getSupportActionBar().setTitle(title_kritik);*/
                        kritik();
                        String title_tes= "Jadwal Lab";
                        getSupportActionBar().setTitle(title_tes);
                        return true;
                    default:
                        logout();
                        return true;

                }
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mn_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_search:
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("apakah anda yakin ingin keluar ?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {


                        SharedPreferences preferences =getSharedPreferences(config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.commit();
                        finish();

                        //Getting out sharedpreferences
                        /*SharedPreferences preferences = getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(config.EMAIL_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity*/
                        Intent intent = new Intent(MainActivity.this, login.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void kritik(){
        Intent intent = new Intent(MainActivity.this, kritik_saran.class);
        startActivity(intent);
    }
}
