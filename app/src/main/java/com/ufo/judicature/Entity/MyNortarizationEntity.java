package com.ufo.judicature.Entity;

import java.io.Serializable;

/**
 * 我的预约
 */
public class MyNortarizationEntity extends ServiceResult implements Serializable {

//    {"data":{"id":"5","user_id":"1","name":"zhang","identity_number":"320504198","phone":"188888","reserve_date":"2015-07-09","created_date":"2015-07-02 00:00:00"},"error":false,"msg":""}
    private NortarizationInfo data;

    public NortarizationInfo getData() {
        return data;
    }

    public void setData(NortarizationInfo data) {
        this.data = data;
    }

    public class NortarizationInfo {
        private String id;
        private String user_id;
        private String name;
        private String identity_number;
        private String phone;
        private String reserve_date;
        private String created_date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdentity_number() {
            return identity_number;
        }

        public void setIdentity_number(String identity_number) {
            this.identity_number = identity_number;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getReserve_date() {
            return reserve_date;
        }

        public void setReserve_date(String reserve_date) {
            this.reserve_date = reserve_date;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }
    }
}
