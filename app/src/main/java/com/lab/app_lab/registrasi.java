package com.lab.app_lab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class registrasi extends AppCompatActivity {

    EditText e_npm, e_nama,e_pas;
    Spinner c_prodi;
    Button btnregis;

    ProgressDialog PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Registarsi");

        String list[]={"pilih prodi","sistem informasi","tehnik informatika","bahasa inggris","bahasa jepang",
                "pendidikan bahasa dan sastra indonesia","pendidikan bahasa inggris","akuntasi","manajemen",
                "pendidikan ekonomi","ilmu hukum","bimbingan dan konseling","pendidikan geografi"};
        Spinner spinner = (Spinner) findViewById(R.id.prodi);
        ArrayAdapter<String> AdapterList = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,list);
        spinner.setAdapter(AdapterList);

        e_npm = (EditText) findViewById(R.id.input_npm);
        e_nama = (EditText) findViewById(R.id.input_nama);
        c_prodi = (Spinner) findViewById(R.id.prodi);
        e_pas = (EditText) findViewById(R.id.input_password);
        btnregis = (Button) findViewById(R.id.regis);

        PD = new ProgressDialog(this);
        PD.setMessage("silahkan tunggu.....");
        PD.setCancelable(false);
    }

    public void rgs(View v) {
        PD.show();
        final String npm_mhs = e_npm.getText().toString();
        final String nama = e_nama.getText().toString();
        final String prodi = c_prodi.getSelectedItem().toString();
        final String pass = e_pas.getText().toString();



        StringRequest postRequest = new StringRequest(Request.Method.POST, config.REGIS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        PD.dismiss();
                        Toast.makeText(getApplicationContext(),
                                "berhasil registrasi",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(registrasi.this, login.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                PD.dismiss();
                Toast.makeText(getApplicationContext(),
                        "gagal registrasi", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(config.KEY_USER,npm_mhs);
                params.put(config.KEY_PASSWORD,pass);
                params.put(config.KEY_PRODI,prodi);
                params.put(config.KEY_NAMA,nama);
                return params;
            }
        };

        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(postRequest);
        //MyApplication.getInstance().addToReqQueue(postRequest);
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
