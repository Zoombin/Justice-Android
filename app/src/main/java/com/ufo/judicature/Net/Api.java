package com.ufo.judicature.Net;

import android.content.Context;

import com.ufo.judicature.Entity.ServiceResult;

public class Api {

    /**
     * 服务机构
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getService(Context context, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        NetUtils.getValue(context, "?action=getServices", "正在获取数据，请稍等...", netCallBack, rspCls);
    }

    /**
     * 新闻
     * @param context
     * @param netCallBack
     * @param rspCls
     */
    public static void getNews(Context context, int page, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        NetUtils.getValue(context, "?action=getNews&page=" + page, null, netCallBack, rspCls);
    }

    public static void getVideos(Context context, int page, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        NetUtils.getValue(context, "?action=getVideos&page=" + page, null, netCallBack, rspCls);
    }
}
