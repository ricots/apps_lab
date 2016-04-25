package com.lab.app_lab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Locale;

/**
 * Created by Dr.h3cker on 14/03/2015.
 */
public class ma_pager_adapter extends FragmentPagerAdapter {
    public ma_pager_adapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                tab_kritik t1 = new tab_kritik();
                return t1;
            case 1:
                tab_tampilkan t2 = new tab_tampilkan();
                return t2;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }//set the number of tabs

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return "Kritk & saran";
            case 1:

                return "List Kritk & saran";
        }
        return null;
    }



}

