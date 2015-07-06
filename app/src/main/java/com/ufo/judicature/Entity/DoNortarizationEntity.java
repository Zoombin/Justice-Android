package com.ufo.judicature.Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 我要预约
 */
public class DoNortarizationEntity extends ServiceResult implements Serializable {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
