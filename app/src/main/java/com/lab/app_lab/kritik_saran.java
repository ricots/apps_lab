package com.lab.app_lab;

import android.graphics.Color;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class kritik_saran extends AppCompatActivity {

    ViewPager pager;
    PagerTabStrip tab_strp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kritik_saran);
        ma_pager_adapter mapager=new ma_pager_adapter(getSupportFragmentManager());
        pager=(ViewPager)findViewById(R.id.pager);


        pager.setAdapter(mapager);
        tab_strp=(PagerTabStrip)findViewById(R.id.tab_strip);
        tab_strp.setTextColor(Color.WHITE);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // moveTaskToBack(true);;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
