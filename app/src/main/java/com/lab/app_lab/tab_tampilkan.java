package com.lab.app_lab;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class tab_tampilkan extends Fragment {
    ArrayList<Item> array = new ArrayList<Item>();
    SwipeRefreshLayout mSwipeRefreshLayout;
    private SwipeRefreshLayout refreshLayout;
    private adapter_kritik adp_kritik;
    private ListView list_tamp;
    ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adp_kritik=new adapter_kritik(tab_tampilkan.this.getActivity(),array);




    }

    public void show_json(){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(config.DETAIL_KRITIK, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //hideDialog();
                array.clear();
                //parsing json
                for(int i=0;i<response.length();i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Item item = new Item();
                        item.setDetail_kritik_npm(obj.getString(config.KEY_NPM_KRITIK));
                        item.setDetail_lokasinya(obj.getString(config.KEY_LOKASI));
                        item.setDetail_keteranganya(obj.getString(config.KEY_KRITIK));
                        item.setDetail_gambar_kritik(obj.getString(config.KEY_detail_gambar_kritik));
                        array.add(item);
                        list_tamp.setVisibility(View.VISIBLE);
                    } catch (JSONException ex) {
                        ex.printStackTrace();

                    }
                }
                adp_kritik.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                3600, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //hideDialog();
        AppController.getInstance().addToRequesQueue(jsonArrayRequest);
    }

    public void hideDialog(){
        if(dialog !=null){
            dialog.dismiss();
            dialog=null;
        }
    }

    public static tab_tampilkan newInstance() {
        tab_tampilkan fragment = new tab_tampilkan();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_tab_tampilkan,container,false);
        list_tamp = (ListView) view.findViewById(R.id.list_kritik);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                show_json();
                refreshLayout.setRefreshing(false);
                list_tamp.setVisibility(View.GONE);
            }
        });
        //refreshLayout.setOnRefreshListener(onRefreshListener());
        //adapter=new Adapter(BeritaFragment.this.getActivity(),array);
        list_tamp.setAdapter(adp_kritik);
        show_json();
        return  view;
    }
}

