package com.ufo.judicature.Fragment;

import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.easemob.chatuidemo.activity.ChatActivity;
import com.easemob.chatuidemo.activity.ChatAllHistoryFragment;
import com.easemob.chatuidemo.activity.LoginActivity;
import com.ufo.judicature.Entity.LawyersEntity;
import com.ufo.judicature.Entity.ServiceResult;
import com.ufo.judicature.Net.Api;
import com.ufo.judicature.Net.NetUtils;
import com.ufo.judicature.Widget.Toast;

import com.easemob.chatuidemo.DemoHXSDKHelper;
import com.easemob.chatuidemo.domain.User;
import com.ufo.judicature.Base.BaseFragment;
import com.ufo.judicature.R;

import java.util.ArrayList;

/**
 * 律师在线
 */
public class LawyerFragment extends BaseFragment {

    private TextView tv_layer;
    private TextView tv_history;
    private ViewPager pager_lawyer;
    protected FragmentAdapter adapter;
    private LawyerListFragment lawyerListFragment;
    private ChatAllHistoryFragment chatAllHistoryFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_lawyer, container, false);


        pager_lawyer = (ViewPager) v.findViewById(R.id.pager_lawyer);

        tv_layer = (TextView) v.findViewById(R.id.tv_layer);
        tv_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager_lawyer.setCurrentItem(0);
            }
        });

        tv_history = (TextView) v.findViewById(R.id.tv_history);
        tv_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager_lawyer.setCurrentItem(1);
            }
        });

        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        ArrayList<Fragment> fragmentlist = new ArrayList();
        lawyerListFragment = new LawyerListFragment();
        chatAllHistoryFragment = new ChatAllHistoryFragment();
        fragmentlist.add(lawyerListFragment);
        fragmentlist.add(chatAllHistoryFragment);

        pager_lawyer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1)
                    chatAllHistoryFragment.refresh();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        String[] s = new String[2];
        s[0] = "律师";
        s[1] = "会话";
        adapter = new FragmentAdapter(fragmentManager, fragmentlist, s);
        pager_lawyer.setAdapter(adapter);

        return v;
    }
}
