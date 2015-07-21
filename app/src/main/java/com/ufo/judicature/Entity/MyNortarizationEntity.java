package com.ufo.judicature.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * 我的预约
 */
public class MyNortarizationEntity extends ServiceResult implements Serializable {

//    {"data":[{"id":"88","account":"daiye","name":"\u6234\u7ef4","identity_number":"320520198305040913","phone":"18662670711","type":"??????\/??????","reserve_date":"2015-07-24","created_date":"2015-07-22 07:03:53"},{"id":"74","account":"daiye","name":"\u6234\u70e8","identity_number":"320520198305040913","phone":"18662670711","type":null,"reserve_date":"2015-07-15","created_date":"2015-07-13 09:51:57"}],"error":false,"msg":""}

    private List<NortarizationInfo> data;

    public List<NortarizationInfo> getData() {
        return data;
    }

    public void setData(List<NortarizationInfo> data) {
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
        private String type;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
