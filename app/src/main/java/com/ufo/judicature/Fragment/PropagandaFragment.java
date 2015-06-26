package com.ufo.judicature.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ufo.judicature.Base.BaseFragment;
import com.ufo.judicature.Entity.AgencyEntity;
import com.ufo.judicature.Entity.ServiceResult;
import com.ufo.judicature.Net.Api;
import com.ufo.judicature.Net.NetUtils;
import com.ufo.judicature.R;
import com.ufo.judicature.Widget.Toast;

import java.util.ArrayList;

/**
 * 宣传普法
 */
public class PropagandaFragment extends BaseFragment {

    private RadioGroup radiogroup;
    private ViewPager pager_propaganda;
    private ArrayList<Fragment> fragmentlist= new ArrayList<>();
    private PropagandaNewsFragment f1;
    private PropagandaPhotoFragment f2;
    private PropagandaVideoFragment f3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_propaganda, container, false);
        createView(v);
        initData();
        return v;
    }

    private void createView(View v) {
        radiogroup = (RadioGroup) v.findViewById(R.id.radiogroup);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                switch (radioButtonId) {
                    case R.id.btn_news:
                        pager_propaganda.setCurrentItem(0);
                        break;
                    case R.id.btn_photo:
                        pager_propaganda.setCurrentItem(1);
                        break;
                    case R.id.btn_video:
                        pager_propaganda.setCurrentItem(2);
                        break;
                }
            }
        });

        pager_propaganda = (ViewPager) v.findViewById(R.id.pager_propaganda);
        pager_propaganda.setOffscreenPageLimit(3);

        f1 = new PropagandaNewsFragment();
        fragmentlist.add(f1);
        f2 = new PropagandaPhotoFragment();
        fragmentlist.add(f2);
        f3 = new PropagandaVideoFragment();
        fragmentlist.add(f3);

        pager_propaganda.setAdapter(new PropagandaViewPagerAdapter(getFragmentManager()));

        pager_propaganda.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        ((RadioButton)radiogroup.findViewById(R.id.btn_news)).setChecked(true);
                        break;
                    case 1:
                        ((RadioButton)radiogroup.findViewById(R.id.btn_photo)).setChecked(true);
                        break;
                    case 2:
                        ((RadioButton)radiogroup.findViewById(R.id.btn_video)).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        Api.getService(mActivity, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
                AgencyEntity a = (AgencyEntity) rspData;
                a.getData();
            }

            @Override
            public void failed(String msg) {
                Toast.show(mActivity, msg);
            }
        }, AgencyEntity.class);
    }

    public class PropagandaViewPagerAdapter extends FragmentPagerAdapter {
        public PropagandaViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return fragmentlist.size();
        }
    }
}
