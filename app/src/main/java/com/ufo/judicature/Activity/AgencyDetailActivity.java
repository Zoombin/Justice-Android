package com.ufo.judicature.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ufo.judicature.Base.BaseActivity;
import com.ufo.judicature.Entity.AgencyEntity;
import com.ufo.judicature.R;

/**
 * 服务机构详情
 */
public class AgencyDetailActivity extends BaseActivity implements View.OnClickListener {

    public static final String EXTRA_AGENCY = "EXTRA_AGENCY";
    AgencyEntity.Service serviceEntity;
    private ImageView iv_service;
    private TextView tv_service_info;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicedetail);

        Intent intent = getIntent();
        serviceEntity = (AgencyEntity.Service) intent.getSerializableExtra(EXTRA_AGENCY);
        initView();
    }

    private void initView() {
        ((ImageView) findViewById(R.id.image_back)).setOnClickListener(this);
        ((TextView) findViewById(R.id.tv_map)).setOnClickListener(this);
        ((TextView) findViewById(R.id.tv_title)).setText(serviceEntity.getName());
        iv_service = (ImageView) findViewById(R.id.iv_service);
        ImageLoader.getInstance().displayImage(serviceEntity.getImage(), iv_service);
        tv_service_info = (TextView) findViewById(R.id.tv_service_info);
        tv_service_info.setText("地址：" + serviceEntity.getAddress() + "\n" +
                        "联系电话：" + serviceEntity.getPhone() + "\n" +
                        "成立时间：" + serviceEntity.getCreated_date() + "\n"
        );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.tv_map:
                Intent intent = new Intent(self, MapActivity.class);
                intent.putExtra(MapActivity.EXTRA_SERVICE, serviceEntity);
                startActivity(intent);
                break;
        }
    }
}
