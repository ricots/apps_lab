package com.lab.app_lab;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 04-06-2015.
 */
public class ContentFragment extends Fragment {
    private ProgressDialog dialog;
    //private List<Item> array = new ArrayList<Item>();
    List<Item> array = new ArrayList<Item>();
    private ListView listView;
    private Adapter adapter;
    CircleImageView gambar_berita;
    Context context;

    private static final String TAG = ContentFragment.class.getSimpleName();
    private int offSet = 0;
    int no;
    SwipeRefreshLayout swipe;
    Handler handler;
    Runnable runnable;

    public ContentFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void hideDialog(){
        if(dialog !=null){
            dialog.dismiss();
            dialog=null;
        }
    }

    public static ContentFragment newInstance() {
        ContentFragment fragment = new ContentFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_fragment,container,false);
        listView = (ListView) v.findViewById(R.id.list);
        swipe = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        array.clear();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ContentFragment.this.getActivity(), detail.class);
                intent.putExtra(config.KEY_ID, array.get(position).getId_kegiatan());
                startActivity(intent);
            }
        });
        adapter = new Adapter(ContentFragment.this.getActivity(), array);
        listView.setAdapter(adapter);

        swipe.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                array.clear();
                adapter.notifyDataSetChanged();
                callNews(0);
            }
        });

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           array.clear();
                           adapter.notifyDataSetChanged();
                           callNews(0);
                       }
                   }
        );

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            private int currentVisibleItemCount;
            private int currentScrollState;
            private int currentFirstVisibleItem;
            private int totalItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.currentScrollState = scrollState;
                this.isScrollCompleted();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
                this.totalItem = totalItemCount;
            }

            private void isScrollCompleted() {
                if (totalItem - currentFirstVisibleItem == currentVisibleItemCount
                        && this.currentScrollState == SCROLL_STATE_IDLE) {

                    swipe.setRefreshing(true);
                    handler = new Handler();

                    runnable = new Runnable() {
                        public void run() {
                            callNews(offSet);
                        }
                    };

                    handler.postDelayed(runnable, 3000);
                }
            }

        });
        return v;
    }

    private void callNews(int page){

        swipe.setRefreshing(true);

        // Creating volley request obj
        JsonArrayRequest arrReq = new JsonArrayRequest(config.BERITA_URL + page,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        if (response.length() > 0) {
                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    Item item = new Item();

                                    no = obj.getInt(config.TAG_NO);
                                    item.setId_kegiatan(obj.getString(config.KEY_ID));
                                    item.setJdl(obj.getString(config.KEY_JUDUL));
                                    item.setKegiatan(obj.getString(config.KEY_DESKIRIPSI));
                                    String tes = obj.getString(config.KEY_TGL);
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    Date tgl_kgt = simpleDateFormat.parse(tes);
                                    item.setTgl(tgl_kgt);
                                    item.setJam(obj.getString(config.KEY_MULAI));


                                    if (obj.getString(config.KEY_GAMBAR) != "") {
                                        item.setGambar(obj.getString(config.KEY_GAMBAR));
                                    }
                                    array.add(item);

                                    if (no > offSet)
                                        offSet = no;

                                    Log.d(TAG, "offSet " + offSet);


                                } catch (JSONException e) {
                                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                // notifying list adapter about data changes
                                // so that it renders the list view with updated data
                                adapter.notifyDataSetChanged();
                            }
                        }
                        swipe.setRefreshing(false);
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequesQueue(arrReq);
    }
}