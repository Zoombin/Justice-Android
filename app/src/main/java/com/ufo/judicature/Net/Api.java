package com.ufo.judicature.Net;

import android.content.Context;

import com.ufo.judicature.Entity.ServiceResult;

public class Api {

    public static void getService(Context context, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        NetUtils.getValue(context, "?action=getServices", "正在获取数据，请稍等...", netCallBack, rspCls);
    }
}