package com.ufo.judicature.Activity;

import android.os.Bundle;

import com.ufo.judicature.Base.BaseActivity;
import com.ufo.judicature.Entity.NewsEntity;
import com.ufo.judicature.Entity.ServiceResult;
import com.ufo.judicature.Net.Api;
import com.ufo.judicature.Net.NetUtils;
import com.ufo.judicature.R;
import com.ufo.judicature.Widget.Toast;

/**
 * exam page
 *
 */
public class ExamActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        initView();
        initData();
    }

    private void initView() {

    }

    private void initData() {
        Api.getQuestion(self, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
//                Toast.show(self, msg);

            }

            @Override
            public void failed(String msg) {
                Toast.show(self, msg);
            }
        }, NewsEntity.class);
    }
}
