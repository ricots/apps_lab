package com.lab.app_lab;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ACER on 2016-04-06.
 */
public class adapter_kritik extends BaseAdapter {
    private LayoutInflater inflater;
    private Activity activity;
    private List<Item> items;
    ImageLoader imageLoader = AppController.getInstance().getmImageLoader();

    public adapter_kritik(Activity activity, List<Item> items) {
        this.activity = activity;
        this.items = items;
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
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.detail_kritik, null);
        }
        if(imageLoader==null)
            imageLoader=AppController.getInstance().getmImageLoader();
        CircleImageView imageView = (CircleImageView) convertView.findViewById(R.id.tab_image_view);
        TextView detail_identitas = (TextView) convertView.findViewById(R.id.detail_kritik_npm);
        TextView detail_lokasi_kritik = (TextView) convertView.findViewById(R.id.detail_kritik_lokasi);
        TextView detail_keterangannya = (TextView) convertView.findViewById(R.id.detail_kritik_keterangan);
        //getting data for row
        Item item = items.get(position);
        Picasso.with(activity).load(item.getDetail_gambar_kritik()).resize(120, 60).into(imageView);
        detail_identitas.setText("dari npm : " + item.getDetail_kritik_npm());
        detail_lokasi_kritik.setText("kritik saran di " + item.getDetail_lokasinya());
        detail_keterangannya.setText("keterangan : " +item.getDetail_keteranganya());
        return convertView;
    }
}
