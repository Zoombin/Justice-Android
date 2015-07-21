package com.ufo.judicature.Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 预约时间
 */
public class ReserveDateEntity extends ServiceResult implements Serializable {

//    {"data":[{"date":"2015-07-23","left":4},{"date":"2015-07-24","left":5},{"date":"2015-07-25","left":5},
//        {"date":"2015-07-26","left":5},{"date":"2015-07-27","left":5},
//        {"date":"2015-07-28","left":5},{"date":"2015-07-29","left":5}],"error":false,"msg":""}
    private ArrayList<DateEntity> data;

    public ArrayList<DateEntity> getData() {
        return data;
    }

    public void setData(ArrayList<DateEntity> data) {
        this.data = data;
    }

    public class DateEntity {
        private String date;
        private String left;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getLeft() {
            return left;
        }

        public void setLeft(String left) {
            this.left = left;
        }
    }
}
