package com.lab.app_lab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class detail_jadwal extends AppCompatActivity {
    private adapter_detail adapter_dtl;
    private List<Item> array = new ArrayList<Item>();
    SharedPreferences sp;
    SharedPreferences.Editor spe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jadwal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("detail jadwal");

        TextView dtl_tmp = (TextView) findViewById(R.id.detail_temp);
        sp = this.getSharedPreferences("kirim", 0);
        spe = sp.edit();
        String b = sp.getString("kirim","");
        dtl_tmp.setText(b);

        TextView dtl_hari = (TextView) findViewById(R.id.detail_hari);
        TextView dtl_jam = (TextView) findViewById(R.id.detail_jam);
        TextView dtl_kode = (TextView) findViewById(R.id.detail_kode);

        TextView detail_jam_selesai = (TextView) findViewById(R.id.detail_jam_selesai);
        TextView detail_kegiatan_lab = (TextView) findViewById(R.id.detail_kegiatan_lab);
        TextView detail_prodi = (TextView) findViewById(R.id.detail_prodi);
        Intent in = getIntent();
        String hri = in.getStringExtra(config.KEY_HARI);
        String jm = in.getStringExtra(config.KEY_JAM);
        String kd_jadwl = in.getStringExtra(config.KEY_KODE_JADWAL);

        String slsai = in.getStringExtra(config.KEY_JAM_SELESAI);
        String kegiatan = in.getStringExtra(config.KEY_KEGIATAN_LAB);
        String prodi = in.getStringExtra(config.KEY_PRODI_LAB);

        dtl_hari.setText(hri);
        dtl_jam.setText(jm);
        dtl_kode.setText(kd_jadwl);

        detail_jam_selesai.setText(slsai);
        detail_kegiatan_lab.setText(kegiatan);
        detail_prodi.setText(prodi);



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