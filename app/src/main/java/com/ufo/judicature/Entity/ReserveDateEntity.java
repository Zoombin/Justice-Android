package com.ufo.judicature.Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 预约时间
 */
public class ReserveDateEntity extends ServiceResult implements Serializable {

//    {
//        "data": [
//        "2015-07-07",
//                "2015-07-08",
//                "2015-07-09",
//                "2015-07-10",
//                "2015-07-11",
//                "2015-07-12",
//                "2015-07-13"
//        ],
//        "error": false,
//            "msg": ""
//    }
    private ArrayList<String> data;

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }
}
