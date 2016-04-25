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
 * Created by ACER on 2016-04-05.
 */
public class adapter_detail extends BaseAdapter {
    private LayoutInflater inflater;
    private Activity activity;
    private List<Item> items;
    ImageLoader imageLoader=AppController.getInstance().getmImageLoader();

    public adapter_detail(Activity activity,List<Item> items){
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
            convertView=inflater.inflate(R.layout.activity_detail_jadwal,null);
        }
        //if(imageLoader==null)
        //    imageLoader=AppController.getmInstance().getmImageLoader();
        //    NetworkImageView imageView= (NetworkImageView) convertView.findViewById(R.id.image_view);
        TextView kode_jadwal= (TextView) convertView.findViewById(R.id.kd_jadwal);
        TextView dtl_hari= (TextView) convertView.findViewById(R.id.detail_hari);
        TextView start= (TextView) convertView.findViewById(R.id.detail_jam);
        TextView finis= (TextView) convertView.findViewById(R.id.detail_jam_selesai);
        TextView kgt= (TextView) convertView.findViewById(R.id.detail_kegiatan_lab);
        TextView pro= (TextView) convertView.findViewById(R.id.detail_prodi);

        //getting data for row
        Item item=items.get(position);
        kode_jadwal.setText(item.getKode_jadwal());
        dtl_hari.setText("hari : " + item.getHari());
        start.setText("jam : " +item.getJam());
        finis.setText("jam berahir : " +item.getSelesai_lab());
        kgt.setText("kegiatan : " +item.getKegiatan_lab());
        pro.setText("prodi : " +item.getProdi());

        return convertView;
    }
}

