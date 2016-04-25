package com.lab.app_lab;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class detail extends AppCompatActivity{
    RequestQueue getBeritaDetail;
    Item item;
    Toolbar mToolbar;
    CollapsingToolbarLayout collapsingToolbar;
    private static final String TAG = detail.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("detail berita");

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("detail berita");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView dtl_judul = (TextView) findViewById(R.id.detail_judul);
        ImageView dtl_gambar = (ImageView) findViewById(R.id.detail_image_view);
        TextView dtl_kegiatan = (TextView) findViewById(R.id.detail_kegiatan);
        TextView dtl_tgl = (TextView) findViewById(R.id.detail_tanggal);
        TextView dtl_start = (TextView) findViewById(R.id.jam_mulai);
        getBeritaDetail = Volley.newRequestQueue(getApplicationContext());

        Intent in = getIntent();
        String myInt = in.getStringExtra(config.KEY_JUDUL);
        String id = in.getStringExtra(config.KEY_ID);
        String kgt = in.getStringExtra(config.KEY_DESKIRIPSI);
        String d_tnggl = in.getStringExtra(config.KEY_TGL);
        String gambr = in.getStringExtra(config.KEY_GAMBAR);
        String mlai = in.getStringExtra(config.KEY_MULAI);
        //String gambr = in.putExtra(config.KEY_GAMBAR,dtl_gambar);

        dtl_judul.setText(myInt);
        dtl_kegiatan.setText(id);
        dtl_kegiatan.setText(kgt);
        dtl_tgl.setText(d_tnggl);
        dtl_start.setText(mlai);
        Picasso.with(getApplicationContext()).load(gambr).into(dtl_gambar);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}