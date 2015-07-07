package com.ufo.judicature.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.easemob.chatuidemo.DemoHXSDKHelper;
import com.easemob.chatuidemo.activity.LoginActivity;
import com.ufo.judicature.Activity.DoNotarizationActivity;
import com.ufo.judicature.Activity.MyNotarizationActivity;
import com.ufo.judicature.Activity.NotarizationInfoActivity;
import com.ufo.judicature.Base.BaseFragment;
import com.ufo.judicature.R;

/**
 * 公证服务
 */
public class NotarizationFragment extends BaseFragment implements View.OnClickListener {

    private Button btn_notarization_info;
    private Button btn_do_notarization;
    private Button btn_my_notarization;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notarization, container, false);
        createView(v);
        return v;
    }

    private void createView(View v) {
        btn_notarization_info = (Button) v.findViewById(R.id.btn_notarization_info);
        btn_do_notarization = (Button) v.findViewById(R.id.btn_do_notarization);
        btn_my_notarization = (Button) v.findViewById(R.id.btn_my_notarization);
        btn_notarization_info.setOnClickListener(this);
        btn_do_notarization.setOnClickListener(this);
        btn_my_notarization.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_notarization_info:
                intent = new Intent(mActivity, NotarizationInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_do_notarization:
                if (!DemoHXSDKHelper.getInstance().isLogined()) {
                    startActivity(new Intent(mActivity, LoginActivity.class));
                    return;
                }
                intent = new Intent(mActivity, DoNotarizationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_my_notarization:
                if (!DemoHXSDKHelper.getInstance().isLogined()) {
                    startActivity(new Intent(mActivity, LoginActivity.class));
                    return;
                }
                intent = new Intent(mActivity, MyNotarizationActivity.class);
                startActivity(intent);
                break;
        }
    }
}
