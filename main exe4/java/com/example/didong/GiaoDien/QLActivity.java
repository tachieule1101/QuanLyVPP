package com.example.didong.GiaoDien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.didong.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class QLActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btnIn, btnVPP, btnNV, btnPB;
    ImageButton btnCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        setConTrol();
        setEvent();
    }

    private void setEvent() {
        btnNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( QLActivity.this, NVActivity.class);
                startActivity( intent );
            }
        });
        btnVPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( QLActivity.this, VPPActivity.class);
                startActivity( intent );
            }
        });
        btnPB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( QLActivity.this, PBActivity.class);
                startActivity( intent );
            }
        });
        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( QLActivity.this, InActivity.class);
                startActivity( intent );
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( QLActivity.this, Contact.class);
                startActivity( intent );
            }
        });
    }

    private void setConTrol() {
        btnIn = (Button) findViewById(R.id.btnIN);
        btnNV = (Button) findViewById(R.id.btnNV);
        btnPB = (Button) findViewById(R.id.btnPB);
        btnVPP = (Button) findViewById(R.id.btnVPP);
        btnCall = (ImageButton) findViewById(R.id.btnCall);
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
        if (id == R.id.mnExit) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent( QLActivity.this, MainActivity.class);
            startActivity( intent );
        } else if (id == R.id.in) {
            Intent intent = new Intent( QLActivity.this, InActivity.class);
            startActivity( intent );
        }else if (id == R.id.vpp) {
            Intent intent = new Intent( QLActivity.this, VPPActivity.class);
            startActivity( intent );
        } else if (id == R.id.pb) {
            Intent intent = new Intent( QLActivity.this, PBActivity.class);
            startActivity( intent );
        } else if (id == R.id.nv) {
            Intent intent = new Intent( QLActivity.this, NVActivity.class);
            startActivity( intent );
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}