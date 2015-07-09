package com.ufo.judicature.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ufo.judicature.Base.BaseActivity;
import com.ufo.judicature.Entity.DoNortarizationEntity;
import com.ufo.judicature.Entity.NewsEntity;
import com.ufo.judicature.Entity.ReserveDateEntity;
import com.ufo.judicature.Entity.ServiceResult;
import com.ufo.judicature.JudiApplication;
import com.ufo.judicature.Net.Api;
import com.ufo.judicature.Net.NetUtils;
import com.ufo.judicature.R;
import com.ufo.judicature.Utils.Config;
import com.ufo.judicature.Widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 我要预约
 */
public class DoNotarizationActivity extends BaseActivity {

    private ImageView image_back;
    private TextView tv_commit;
    private Button btn_date;
    private EditText tv_nortarization_name;
    private EditText tv_nortarization_idcard;
    private EditText tv_nortarization_phone;
    String[] dates;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donotarization);

        initView();
        initData();
    }

    private void initView() {
        btn_date = (Button) findViewById(R.id.btn_date);
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(self);
                builder.setTitle("请选择预约时间");
                builder.setItems(dates, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btn_date.setText(dates[which]);
                    }
                });
                builder.show();
            }
        });
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    private void initData() {
        Api.getReserveDate(self, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
                ArrayList<String> data = ((ReserveDateEntity) rspData).getData();
                String[] strArr = new String[data.size()];
                dates = data.toArray(strArr);
            }

            @Override
            public void failed(String msg) {
                Toast.show(self, msg);
                finish();
            }
        }, ReserveDateEntity.class);
    }

    private void donotarization() {
        String date = btn_date.getText().toString().trim();
        if (date.equals("请选择预约时间")) {
            Toast.show(self, "请选择预约时间！");
            return;
        }
        String name = tv_nortarization_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.show(self, "请输入姓名！");
            return;
        }
        if (!isChineseName(name)) {
            Toast.show(self, "请输入正确的姓名！");
            return;
        }

        String idcard = tv_nortarization_idcard.getText().toString().trim();
        if (TextUtils.isEmpty(idcard)) {
            Toast.show(self, "请输入身份证号！");
            return;
        }
        if (!isIdCard(idcard)) {
            Toast.show(self, "请输入正确的身份证号！");
            return;
        }

        String phone = tv_nortarization_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.show(self, "请输入手机号码！");
            return;
        }
        if (!isPhoneNumber(phone)) {
            Toast.show(self, "请输入正确的手机号码！");
            return;
        }

        Api.getDoReservation(self, Config.getUserId(), name, idcard, phone, date, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
                DoNortarizationEntity doNortarizationEntity = (DoNortarizationEntity) rspData;
                Toast.show(self, doNortarizationEntity.getMsg());
                finish();
            }

            @Override
            public void failed(String msg) {
                Toast.show(self, msg);
            }
        }, DoNortarizationEntity.class);
    }

    public boolean isChineseName(String name) {
        Pattern pattern = Pattern.compile("^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]){2,5}$");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public boolean isIdCard(String idcard) {
        //定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
        Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
        //通过Pattern获得Matcher
        Matcher idNumMatcher = idNumPattern.matcher(idcard);
        //判断用户输入是否为身份证号
        if (idNumMatcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPhoneNumber(String phone) {
        if (phone.length() != 11) {
            return false;
        }
        try {
            Long.parseLong(phone);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
