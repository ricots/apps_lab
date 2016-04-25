package com.lab.app_lab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Jadwal_lab extends AppCompatActivity {
    ProgressDialog PD;
    TextView hari,jam,lokasi,ruang_lab, jam_slsai, jam_kgt, jam_prodi;
    ListView listview_jadwal;
    private ProgressDialog dialog;
    private adapter_jadwal adp;
    private List<Item> array = new ArrayList<Item>();
    SharedPreferences sp;
    SharedPreferences.Editor spe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_lab);

        sp = this.getSharedPreferences("kirim", 0);
        spe = sp.edit();

        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("detail jadwal");

        hari = (TextView) findViewById(R.id.hari);
        jam = (TextView) findViewById(R.id.jam);
        jam_slsai = (TextView) findViewById(R.id.jam_sls);
        jam_kgt = (TextView) findViewById(R.id.jam_kgt);
        jam_prodi = (TextView) findViewById(R.id.jam_prodi);

        Bundle bundle = getIntent().getExtras();
        String tempat = bundle.getString("btn");
        String ruang = bundle.getString("btn1");
        lokasi = (TextView) findViewById(R.id.textView);
        ruang_lab = (TextView) findViewById(R.id.textView1);
        lokasi.setText(tempat);
        ruang_lab.setText(ruang);
        listview_jadwal = (ListView) findViewById(R.id.list_jadwal);
        adp=new adapter_jadwal(this,array);
        listview_jadwal.setAdapter(adp);

        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        String URL_CARI = config.JADWAL_ap1 + lokasi.getText().toString();
        //Creat volley request obj
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL_CARI, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                hideDialog();
                String str1 = "";
                //parsing json
                for(int i=0;i<response.length();i++){
                    try{
                        JSONObject obj=response.getJSONObject(i);
                        Item item=new Item();
                        item.setHari(obj.getString(config.KEY_HARI));
                        item.setJam(obj.getString(config.KEY_JAM));
                        item.setSelesai_lab(obj.getString(config.KEY_JAM_SELESAI));
                        item.setKegiatan_lab(obj.getString(config.KEY_KEGIATAN_LAB));
                        item.setProdi_lab(obj.getString(config.KEY_PRODI_LAB));
                        item.setKode_jadwal(obj.getString(config.KEY_KODE_JADWAL));
                        array.add(item);
                    }catch(JSONException ex){
                        ex.printStackTrace();
                    }
                }
                adp.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.append("COBA BACA =  "+ error.getMessage());
            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                3600, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        hideDialog();
        AppController.getInstance().addToRequesQueue(jsonArrayRequest);
    }

    public void hideDialog(){
        if(dialog !=null){
            dialog.dismiss();
            dialog=null;
        }

        listview_jadwal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String hari = ((TextView) view.findViewById(R.id.hari)).getText().toString();
                String jam = ((TextView) view.findViewById(R.id.jam)).getText().toString();
                String kd_jdwl= ((TextView) view.findViewById(R.id.kd_jadwal)).getText().toString();

                String jm_slsai = ((TextView) view.findViewById(R.id.jam_sls)).getText().toString();
                String jm_kgt = ((TextView) view.findViewById(R.id.jam_kgt)).getText().toString();
                String jm_prodi= ((TextView) view.findViewById(R.id.jam_prodi)).getText().toString();
                Intent in = new Intent(Jadwal_lab.this,detail_jadwal.class);
                in.putExtra(config.KEY_HARI, hari);
                in.putExtra(config.KEY_JAM, jam);
                in.putExtra(config.KEY_KODE_JADWAL, kd_jdwl);

                in.putExtra(config.KEY_JAM_SELESAI, jm_slsai);
                in.putExtra(config.KEY_KEGIATAN_LAB, jm_kgt);
                in.putExtra(config.KEY_PRODI_LAB, jm_prodi);
                spe.putString("kirim", kd_jdwl);
                spe.commit();
                startActivity(in);
            }
        });
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
