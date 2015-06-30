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

    /**
     * 视频
     * @param context
     * @param page
     * @param netCallBack
     * @param rspCls
     */
    public static void getVideos(Context context, int page, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        NetUtils.getValue(context, "?action=getVideos&page=" + page, null, netCallBack, rspCls);
    }

    /**
     * 我的预约
     * @param context
     * @param userid
     * @param netCallBack
     * @param rspCls
     */
    public static void getMyReservation(Context context, String userid, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        NetUtils.getValue(context, "?action=getReservation&userid=" + userid, null, netCallBack, rspCls);
    }

    /**
     * 我要预约
     * @param context
     * @param userid
     * @param name
     * @param identityNumber
     * @param phone
     * @param netCallBack
     * @param rspCls
     */
    public static void getDoReservation(Context context, String userid, String name, String identityNumber, String phone, final NetUtils.NetCallBack<ServiceResult> netCallBack, final Class<?> rspCls) {
        NetUtils.getValue(context, "?action=getreserve&userid=" + userid + "&name=" + name + "&phone=" + phone + "&identityNumber=" + identityNumber, null, netCallBack, rspCls);
    }
}
