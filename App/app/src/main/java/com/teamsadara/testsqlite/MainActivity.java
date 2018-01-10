package com.teamsadara.testsqlite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

//import android.app.Fragment;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;

import android.widget.Toast;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.teamsadara.testsqlite.views.pearson.ListPearsonFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /*Section Declarations*/
    private FragmentManager _fragmentManager;
    private FragmentTransaction _fragmentTransaction;

    private ListPearsonFragment _pearsonView;
    /*End Section Declarations*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.InitialFragmentManager();
        this.showFragmentPearsons();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navShare) {

            // Share action
            this.openLink("https://github.com/mtraatabladaa94/TestSQLite/");

        } else if (id == R.id.navFb) {

            // Abrir Facebook
            this.openLink("https://www.facebook.com/mtraatabladaa94");

        } else if (id == R.id.navGh) {

            // Abrir Github
            this.openLink("https://github.com/mtraatabladaa94/");

        } else if (id == R.id.navLk) {

            // Abrir Linkedin
            this.openLink("https://www.linkedin.com/in/mtraatabladaa94/");

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    private void openLink(String link) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);

    }

    /*Private Custom Action*/
    private void InitialFragmentManager() {

        this._fragmentManager = this.getSupportFragmentManager();
        this._fragmentTransaction = this._fragmentManager.beginTransaction();

    }


    private void showFragments(Fragment fragment) {

        this._fragmentTransaction.add(R.id.contentMain, (Fragment)fragment);
        this._fragmentTransaction.commit();

    }

    private void showFragmentPearsons() {

        try {
            if(this._pearsonView == null) {
                this._pearsonView = new ListPearsonFragment();
            }
            this.showFragments(this._pearsonView);
        }
        catch (Exception ex) {
            Toast.makeText(this, "Error al cargar la agenda", Toast.LENGTH_LONG).show();
        }

    }
    /*End Custom Action*/
}
