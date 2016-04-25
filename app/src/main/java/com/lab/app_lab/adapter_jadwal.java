package com.lab.app_lab;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by ACER on 2016-03-29.
 */
public class adapter_jadwal extends BaseAdapter{
    private LayoutInflater inflater;
    private Activity activity;
    private List<Item> items;
    ImageLoader imageLoader=AppController.getInstance().getmImageLoader();

    public adapter_jadwal(Activity activity,List<Item> items){
        this.activity=activity;
        this.items=items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null){
            inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView ==null){
            convertView=inflater.inflate(R.layout.list_jadwal,null);
        }
        //if(imageLoader==null)
        //    imageLoader=AppController.getmInstance().getmImageLoader();
        //    NetworkImageView imageView= (NetworkImageView) convertView.findViewById(R.id.image_view);
        TextView jadwal_hari_lab= (TextView) convertView.findViewById(R.id.hari);
            TextView jam_jadwal= (TextView) convertView.findViewById(R.id.jam);
        TextView kode_jadwal= (TextView) convertView.findViewById(R.id.kd_jadwal);

        TextView jam_slsai= (TextView) convertView.findViewById(R.id.jam_sls);
        TextView jam_kgt= (TextView) convertView.findViewById(R.id.jam_kgt);
        TextView jam_prodi= (TextView) convertView.findViewById(R.id.jam_prodi);


        //getting data for row
        Item item=items.get(position);
        jadwal_hari_lab.setText("hari : " +item.getHari());
        jam_jadwal.setText("jam : " +item.getJam());
        kode_jadwal.setText(item.getKode_jadwal());

        jam_slsai.setText("jam berahir : " + item.getSelesai_lab());
        jam_kgt.setText("kegiatan : " + item.getKegiatan_lab());
        jam_prodi.setText("prodi : " + item.getProdi_lab());
        return convertView;
    }
}
