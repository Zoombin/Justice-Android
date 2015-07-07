package com.ufo.judicature.Entity;

import java.io.Serializable;

/**
 * 注册
 */
public class SignUpEntity extends ServiceResult implements Serializable {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
