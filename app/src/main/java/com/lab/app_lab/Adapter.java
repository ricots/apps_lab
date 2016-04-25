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

import java.text.SimpleDateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ACER on 2016-01-31.
 */
public class Adapter extends BaseAdapter{
    private LayoutInflater inflater;
    private Activity activity;
    private List<Item> items;
    ImageLoader imageLoader=AppController.getInstance().getmImageLoader();
    public Adapter(Activity activity,List<Item> items){
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
            convertView=inflater.inflate(R.layout.list_berita,null);
            //convertView=inflater.inflate(R.layout.list_jadwal,null);
        }
        if(imageLoader==null);
        imageLoader=AppController.getInstance().getmImageLoader();
        CircleImageView imageView = (CircleImageView) convertView.findViewById(R.id.image_view);
        TextView title= (TextView) convertView.findViewById(R.id.judul);
        TextView id= (TextView) convertView.findViewById(R.id.id_kgt);
        TextView desk= (TextView) convertView.findViewById(R.id.kegiatan);
        TextView tgl= (TextView) convertView.findViewById(R.id.tanggal);
        TextView kgt_mlai= (TextView) convertView.findViewById(R.id.jam);
        TextView kgt_slsai= (TextView) convertView.findViewById(R.id.slsai);

        Item item=items.get(position);
        Picasso.with(activity).load(item.getGambar()).resize(120, 60).into(imageView);
        //title
        title.setText(item.getJdl());
        id.setText("kegiatan yang dilaksanakan : " +item.getId_kegiatan());
        desk.setText("kegiatan yang dilaksanakan : " + item.getKegiatan());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        tgl.setText("tanggal pelaksanaan : " +simpleDateFormat.format(item.getTgl()));
        kgt_mlai.setText("jam mulai : " +item.getJam());

        Item it=items.get(position);

        return convertView;
    }
}