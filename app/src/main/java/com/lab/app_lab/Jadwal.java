package com.lab.app_lab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Jadwal extends Fragment {

    public Jadwal() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_jadwal, container, false);
        Button in1 = (Button) rootView.findViewById(R.id.inet1);
        Button in2 = (Button) rootView.findViewById(R.id.inet2);
        Button ap1 = (Button) rootView.findViewById(R.id.apli1);
        Button ap2 = (Button) rootView.findViewById(R.id.apli2);
        Button ap3 = (Button) rootView.findViewById(R.id.apli3);
        in1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lab_inet1 = new Intent(getActivity(), Jadwal_lab.class);
                String kata11 = "Lab Internet 1";
                String kata1 = "internet+1";
                Bundle bundle = new Bundle();
                bundle.putString("btn",kata1);
                bundle.putString("btn1",kata11);
                lab_inet1.putExtras(bundle);
                startActivity(lab_inet1);
            }
        });

        in2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lab_inet2 = new Intent(getActivity(), Jadwal_lab.class);
                String kata21 = "Lab Internet 2";
                String kata2 = "internet+2";
                Bundle bundle = new Bundle();
                bundle.putString("btn",kata2);
                bundle.putString("btn1",kata21);
                lab_inet2.putExtras(bundle);
                startActivity(lab_inet2);
            }
        });

        ap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lab_apli1 = new Intent(getActivity(), Jadwal_lab.class);
                String kata31 = "Lab Aplikasi 1";
                String kata3 = "aplikasi+1";
                Bundle bundle = new Bundle();
                bundle.putString("btn",kata3);
                bundle.putString("btn1",kata31);
                lab_apli1.putExtras(bundle);
                startActivity(lab_apli1);
            }
        });

        ap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lab_apli2 = new Intent(getActivity(), Jadwal_lab.class);
                String kata41 = "Lab Aplikasi 2";
                String kata4 = "aplikasi+2";
                Bundle bundle = new Bundle();
                bundle.putString("btn",kata4);
                bundle.putString("btn1",kata41);
                lab_apli2.putExtras(bundle);
                startActivity(lab_apli2);
            }
        });

        ap3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lab_apli3 = new Intent(getActivity(), Jadwal_lab.class);
                String kata51 = "Lab Aplikasi 3";
                String kata5 = "aplikasi+3";
                Bundle bundle = new Bundle();
                bundle.putString("btn",kata5);
                bundle.putString("btn1",kata51);
                lab_apli3.putExtras(bundle);
                startActivity(lab_apli3);
            }
        });
        return rootView;
    }

    public static Jadwal newInstance() {
        Jadwal fragment = new Jadwal();
        return fragment;
    }


}
