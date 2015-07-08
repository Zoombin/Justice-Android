package com.ufo.judicature.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class FragmentAdapter
        extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = null;
    private CharSequence[] charSequences;

    public FragmentAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList, CharSequence[] charSequences) {
        super(fragmentManager);
        this.fragmentList = fragmentList;
        this.charSequences = charSequences;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if ((position < charSequences.length) && (position >= 0)) {
            return charSequences[position];
        }
        return "";
    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
//    }
}
