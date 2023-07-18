package com.ferrysaptawan.crudapps;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ImageSlider imageSlider;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageSlider = findViewById(R.id.imageSlider);

        List<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.image, "Obat 1", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.image2, "Obat 2", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.image3, "Obat 3", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.image4, "Obat 4", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.image5, "Obat 5", ScaleTypes.FIT));

        imageSlider.setImageList(imageList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Tambah) {
            Intent intent = new Intent(DashboardActivity.this, TambahActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.Tampil) {
            Intent intent = new Intent(DashboardActivity.this, TampilActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.Analisis) {
            Intent intent = new Intent(DashboardActivity.this, AnalisisActivity.class);
            startActivity(intent);
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}