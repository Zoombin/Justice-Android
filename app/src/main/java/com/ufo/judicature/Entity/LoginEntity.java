package com.ufo.judicature.Entity;

import java.io.Serializable;

/**
 * 登录信息
 */
public class LoginEntity extends ServiceResult implements Serializable {

//    {"data":{"id":"11","account":"aaa","score":"0","is_lawyer":"0"},"error":false,"msg":"\u767b\u5f55\u6210\u529f"}
    private User data;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public class User implements Serializable {
        private String id;
        private String account;
        private String score;
        private String is_lawyer;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIs_lawyer() {
            return is_lawyer;
        }

        public void setIs_lawyer(String is_lawyer) {
            this.is_lawyer = is_lawyer;
        }
    }
}
