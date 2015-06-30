package com.ufo.judicature.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ufo.judicature.Base.BaseActivity;
import com.ufo.judicature.Entity.NewsEntity;
import com.ufo.judicature.Entity.ServiceResult;
import com.ufo.judicature.Net.Api;
import com.ufo.judicature.Net.NetUtils;
import com.ufo.judicature.R;
import com.ufo.judicature.Widget.Toast;

/**
 * 我的预约
 */
public class MyNotarizationActivity extends BaseActivity {

    private ImageView image_back;
    private TextView tv_mynotarization;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynotarization);

        initView();
        initData();
    }


    private void initView() {
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_mynotarization = (TextView) findViewById(R.id.tv_mynotarization);
    }

    private void initData() {
        String userid = "1";
        Api.getMyReservation(self, userid, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
//                tv_mynotarization.setText();
            }

            @Override
            public void failed(String msg) {
                Toast.show(self, msg);
            }
        }, NewsEntity.class);
    }
}
