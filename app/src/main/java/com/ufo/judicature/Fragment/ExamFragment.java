package com.ufo.judicature.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ufo.judicature.Activity.ExamActivity;
import com.ufo.judicature.Base.BaseFragment;
import com.ufo.judicature.Entity.ServiceResult;
import com.ufo.judicature.Entity.UserInfoEntity;
import com.ufo.judicature.Net.Api;
import com.ufo.judicature.Net.NetUtils;
import com.ufo.judicature.R;
import com.ufo.judicature.Widget.Toast;

/**
 * 在线考试
 */
public class ExamFragment extends BaseFragment implements View.OnClickListener {

    private TextView tv_myintegration;
    private Button btn_exam;
    private Button btn_integration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_exam, container, false);
        createView(v);
        initData();
        return v;
    }

    private void createView(View v) {
        tv_myintegration = (TextView) v.findViewById(R.id.tv_myintegration);
        btn_exam = (Button) v.findViewById(R.id.btn_exam);
        btn_integration = (Button) v.findViewById(R.id.btn_integration);
        btn_exam.setOnClickListener(this);
        btn_integration.setOnClickListener(this);
    }

    private void initData() {
        String userid = "1";
        Api.getUserInfo(mActivity, userid, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
                UserInfoEntity entity = (UserInfoEntity) rspData;
                tv_myintegration.setText("我的积分：" + entity.getData().getScore());
            }

            @Override
            public void failed(String msg) {
                Toast.show(mActivity, msg);
            }
        }, UserInfoEntity.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exam:
                Intent intent = new Intent(mActivity, ExamActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_integration:
                Toast.show(mActivity, "积分兑换功能还未上线，敬请期待。。。");
                break;
        }
    }
}
