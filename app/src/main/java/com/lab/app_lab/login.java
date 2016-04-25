package com.lab.app_lab;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sp;
    SharedPreferences.Editor spe;
    //Defining views
    private EditText editTextnpm;
    private EditText editTextPass;
    private Button buttonLogin;
    ProgressDialog PD;
    private boolean loggedIn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextnpm = (EditText) findViewById(R.id.npm);
        editTextPass = (EditText) findViewById(R.id.pass);
        buttonLogin = (Button) findViewById(R.id.login);
        final TextView regis = (TextView) findViewById(R.id.akun);
        buttonLogin.setOnClickListener(this);
        PD = new ProgressDialog(this);
        PD.setMessage("silahkan tunggu.....");
        PD.setCancelable(false);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rg = new Intent(login.this, registrasi.class);
                startActivity(rg);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(config.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void login(){
        //Getting values from edit texts
        PD.show();
        final String npm = editTextnpm.getText().toString().trim();
        final String password = editTextPass.getText().toString().trim();
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, config.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        PD.dismiss();
                        //If we are getting success from server
                        // if(response.equalsIgnoreCase(config.LOGIN_SUCCESS)){
                        //Creating a shared preference
                        sp = login.this.getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                        //Creating editor to store values to shared preferences
                        spe = sp.edit();

                        //Adding values to editor
                        spe.putBoolean(config.LOGGEDIN_SHARED_PREF, true);
                        spe.putString(config.EMAIL_SHARED_PREF, npm);

                        //Saving values to editor
                        spe.commit();

                        //Starting profile activity
                        Intent intent = new Intent(login.this, MainActivity.class);
                        startActivity(intent);
                        /*Toast.makeText(getApplicationContext(),
                                "berhasil login",
                                Toast.LENGTH_SHORT).show();*/
                       /*}else{
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }*/
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        //PD.dismiss();
                        //Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put(config.KEY_USER,npm);
                params.put(config.KEY_PASSWORD,password);
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        //Calling the login function
        login();
    }

}
