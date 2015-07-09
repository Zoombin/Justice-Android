package com.ufo.judicature.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * 我的预约
 */
public class MyNortarizationEntity extends ServiceResult implements Serializable {

//    {"data":[{"id":"25","user_id":"1","name":"123","identity_number":"","phone":"123123","reserve_date":"2015-07-09","created_date":"2015-07-08 06:06:48"},
//        {"id":"23","user_id":"1","name":"123","identity_number":"","phone":"123","reserve_date":"2015-07-10","created_date":"2015-07-07 17:40:21"},{"id":"22","user_id":"1","name":"123123","identity_number":"","phone":"123213","reserve_date":"2015-07-08","created_date":"2015-07-07 17:40:10"},{"id":"21","user_id":"1","name":"123","identity_number":"","phone":"123","reserve_date":"2015-07-10","created_date":"2015-07-07 17:38:18"},{"id":"20","user_id":"1","name":"abc","identity_number":"","phone":"12345678901","reserve_date":"2015-07-14","created_date":"2015-07-07 16:07:18"},{"id":"19","user_id":"1","name":"????????????","identity_number":"","phone":"145255853639584486331789624752","reserve_date":"2015-07-09","created_date":"2015-07-07 15:59:49"},{"id":"18","user_id":"1","name":"abc","identity_number":"","phone":"15555563699888","reserve_date":"2015-07-14","created_date":"2015-07-07 15:55:56"},{"id":"17","user_id":"1","name":"sgrghjihgdevhrghyjhfhhfg","identity_number":"","phone":"1?MTK???????????dll","reserve_date":"2015-07-12","created_date":"2015-07-07 15:55:22"},{"id":"16","user_id":"1","name":"bcd","identity_number":"","phone":"123","reserve_date":"2015-07-08","created_date":"2015-07-07 15:52:07"},{"id":"15","user_id":"1","name":"abc","identity_number":"","phone":"123","reserve_date":"2015-07-08","created_date":"2015-07-07 15:51:53"},{"id":"14","user_id":"1","name":"bcd","identity_number":"","phone":"123456789","reserve_date":"2015-07-13","created_date":"2015-07-07 15:49:19"},{"id":"13","user_id":"1","name":"abc","identity_number":"","phone":"12345678901","reserve_date":"2015-07-14","created_date":"2015-07-07 15:48:46"},{"id":"12","user_id":"1","name":"abc","identity_number":"","phone":"12346678901","reserve_date":"2015-07-08","created_date":"2015-07-07 15:47:28"},{"id":"11","user_id":"1","name":"bjjggghh","identity_number":"","phone":"135445668666","reserve_date":"2015-07-11","created_date":"2015-07-07 15:15:47"},{"id":"10","user_id":"1","name":"zhang","identity_number":"320504198","phone":"188888","reserve_date":"2015-07-02","created_date":"2015-07-06 09:46:05"},{"id":"9","user_id":"1","name":"zhang","identity_number":"320504198","phone":"188888","reserve_date":"2015-07-02","created_date":"2015-07-02 00:00:00"},{"id":"8","user_id":"1","name":"zhang","identity_number":"320504198","phone":"188888","reserve_date":"2015-07-02","created_date":"2015-07-02 00:00:00"},{"id":"7","user_id":"1","name":"zhang","identity_number":"320504198","phone":"188888","reserve_date":"2015-07-02","created_date":"2015-07-02 00:00:00"},{"id":"6","user_id":"1","name":"zhang","identity_number":"320504198","phone":"188888","reserve_date":"2015-07-02","created_date":"2015-07-02 00:00:00"},{"id":"5","user_id":"1","name":"zhang","identity_number":"320504198","phone":"188888","reserve_date":"2015-07-09","created_date":"2015-07-02 00:00:00"}],"error":false,"msg":""}private NortarizationInfo data;

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
