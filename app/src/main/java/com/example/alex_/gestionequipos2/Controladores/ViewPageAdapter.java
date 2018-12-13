package com.example.alex_.gestionequipos2.Controladores;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapter extends FragmentStatePagerAdapter {


    private final List<Fragment> lstFragment=new ArrayList<>();
    private final List<String> lstTitulos=new ArrayList<>();

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return lstFragment.get(i);
    }

    @Override
    public int getCount() {
        return lstTitulos.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lstTitulos.get(position);
    }

    public void anadirFragment(Fragment fragment, String titulos){
        lstFragment.add(fragment);
        lstTitulos.add(titulos);

    }
}
