package com.example.rui.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.rui.app.R.layout.about_us;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SwipeRefreshLayout swipe_refresh;
    private RecyclerView ry_inofo;
    private List<OverSpeedCar_info> overSpeedCar_infoList = new ArrayList<>();
    public static List<Activity>activityList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initOverSpeedCars();
        ry_inofo = (RecyclerView) findViewById(R.id.ry_info);
        DividerItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this,R.drawable.custom_divider));
        ry_inofo.addItemDecoration(divider);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        swipe_refresh = findViewById(R.id.swipe_refresh);
        swipe_refresh.setColorSchemeColors(Color.GRAY,Color.RED,Color.BLUE);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
                ry_inofo.setLayoutManager(layoutManager);
                OverSpeedCarAdapter adapter = new OverSpeedCarAdapter(overSpeedCar_infoList);
                ry_inofo.setAdapter(adapter);
                swipe_refresh.setRefreshing(false);
                Toast.makeText(MainActivity.this, "刷新成功！.", Toast.LENGTH_SHORT).show();

            }
        });
}

    private void initOverSpeedCars() {
        OverSpeedCar_info car_1 = new OverSpeedCar_info(R.drawable.img1,"冀B.W9999","120km/h","2018/6/10 10:20:10");
        overSpeedCar_infoList.add(car_1);
        OverSpeedCar_info car_2 = new OverSpeedCar_info(R.drawable.car2,"吉J.5T110","120km/h","2018/6/10 10:20:10");
        overSpeedCar_infoList.add(car_2);
        OverSpeedCar_info car_3 = new OverSpeedCar_info(R.drawable.car3,"皖A.A4411","120km/h","2018/6/10 10:20:10");
        overSpeedCar_infoList.add(car_3);
        OverSpeedCar_info car_4 = new OverSpeedCar_info(R.drawable.car4,"辽A.SB250","120km/h","2018/6/10 10:20:10");
        overSpeedCar_infoList.add(car_4);
        OverSpeedCar_info car_5 = new OverSpeedCar_info(R.drawable.car5,"苏B.99999","120km/h","2018/6/10 10:20:10");
        overSpeedCar_infoList.add(car_5);
        OverSpeedCar_info car_6 = new OverSpeedCar_info(R.drawable.img1,"冀B.M8841","120km/h","2018/6/10 10:20:10");
        overSpeedCar_infoList.add(car_1);
        OverSpeedCar_info car_7 = new OverSpeedCar_info(R.drawable.car2,"吉J.5T110","120km/h","2018/6/10 10:20:10");
        overSpeedCar_infoList.add(car_2);
        OverSpeedCar_info car_8 = new OverSpeedCar_info(R.drawable.car3,"皖A.A4411","120km/h","2018/6/10 10:20:10");
        overSpeedCar_infoList.add(car_3);
        OverSpeedCar_info car_9 = new OverSpeedCar_info(R.drawable.car4,"辽A.SB250","120km/h","2018/6/10 10:20:10");
        overSpeedCar_infoList.add(car_4);
        OverSpeedCar_info car_10 = new OverSpeedCar_info(R.drawable.car5,"苏B.99999","120km/h","2018/6/10 10:20:10");
        overSpeedCar_infoList.add(car_5);
        OverSpeedCar_info car_11 = new OverSpeedCar_info(R.drawable.car2,"吉J.5T110","120km/h","2018/6/10 10:20:10");
        overSpeedCar_infoList.add(car_2);
        OverSpeedCar_info car_12 = new OverSpeedCar_info(R.drawable.car3,"皖A.A4411","120km/h","2018/6/10 10:20:10");
        overSpeedCar_infoList.add(car_3);
        OverSpeedCar_info car_13 = new OverSpeedCar_info(R.drawable.car4,"辽A.SB250","120km/h","2018/6/10 10:20:10");
        overSpeedCar_infoList.add(car_4);
        OverSpeedCar_info car_14 = new OverSpeedCar_info(R.drawable.car5,"苏B.99999","120km/h","2018/6/10 10:20:10");
        overSpeedCar_infoList.add(car_5);
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

    public void exit(){
        for(Activity act:activityList){
            act.finish();
        }      //没有说明act是啥，初始值也没有，运行时是否存在毛病？
        System.exit(0);

    }

//右上角的设置选项    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//右上角 setting对应的动作    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setting) {
            startActivity(new Intent(this,setting.class));
        } else if (id == R.id.nav_speedSetting) {
            startActivity(new Intent(this,carspeed.class));
        } else if (id == R.id.nav_fileManager) {

        } else if (id == R.id.nav_monitor) {

        }
//        else if (id == R.id.nav_aboutUs) {
//
//        }
        else if (id == R.id.nav_exit) {
            exit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        swipe_refresh.setRefreshing(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        ry_inofo.setLayoutManager(layoutManager);
        OverSpeedCarAdapter adapter = new OverSpeedCarAdapter(overSpeedCar_infoList);
        ry_inofo.setAdapter(adapter);
        swipe_refresh.setRefreshing(false);
        Toast.makeText(MainActivity.this, "刷新成功！.", Toast.LENGTH_SHORT).show();

    }
}
