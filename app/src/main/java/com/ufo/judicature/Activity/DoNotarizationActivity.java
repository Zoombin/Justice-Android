package com.ufo.judicature.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ufo.judicature.Base.BaseActivity;
import com.ufo.judicature.Entity.NewsEntity;
import com.ufo.judicature.Entity.ServiceResult;
import com.ufo.judicature.Net.Api;
import com.ufo.judicature.Net.NetUtils;
import com.ufo.judicature.R;
import com.ufo.judicature.Widget.Toast;

/**
 * 我要预约
 */
public class DoNotarizationActivity extends BaseActivity {

    private TextView tv_commit;
    private EditText tv_nortarization_name;
    private EditText tv_nortarization_idcard;
    private EditText tv_nortarization_phone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donotarization);

        initView();
    }

    private void initView() {
        tv_commit = (TextView) findViewById(R.id.tv_commit);
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donotarization();
            }
        });
        tv_nortarization_name = (EditText) findViewById(R.id.tv_nortarization_name);
        tv_nortarization_idcard = (EditText) findViewById(R.id.tv_nortarization_idcard);
        tv_nortarization_phone = (EditText) findViewById(R.id.tv_nortarization_phone);
    }

    private void donotarization() {
        String name = tv_nortarization_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.show(self, "请输入姓名！");
            return;
        }
        String idcard = tv_nortarization_idcard.getText().toString().trim();
        if (TextUtils.isEmpty(idcard)) {
            Toast.show(self, "请输入身份证号！");
            return;
        }
        String phone = tv_nortarization_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.show(self, "请输入手机号码！");
            return;
        }

        String userid = "1";
        Api.getDoReservation(self, userid, name, idcard, phone, new NetUtils.NetCallBack<ServiceResult>() {
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
