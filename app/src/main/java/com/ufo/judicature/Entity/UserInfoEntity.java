package com.ufo.judicature.Entity;

import java.io.Serializable;

/**
 * 个人信息
 */
public class UserInfoEntity extends ServiceResult implements Serializable {

//    {"data":{"account":"zhangbin","score":"99"},"error":false,"msg":""}
    private User data;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public class User implements Serializable {
        private String account;
        private String score;

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
    }
}
