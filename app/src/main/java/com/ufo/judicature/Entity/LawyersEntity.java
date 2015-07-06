package com.ufo.judicature.Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 律师
 */
public class LawyersEntity extends ServiceResult implements Serializable {
//    {
//        "data": [
//        {
//            "id": "1",
//                "name": "刑法律师",
//                "sort_order": "0",
//                "lawyers": [
//            {
//                "id": "3",
//                    "account": "yang12",
//                    "is_lawyer": "1",
//                    "nickname": "杨律师",
//                    "lawyer_category_id": "1"
//            }
//            ]
//        },
//        {
//            "id": "2",
//                "name": "民法律师",
//                "sort_order": "1",
//                "lawyers": [
//            {
//                "id": "8",
//                    "account": "yang122",
//                    "is_lawyer": "1",
//                    "nickname": "王先生",
//                    "lawyer_category_id": "2"
//            }
//            ]
//        }
//        ],
//        "error": false,
//            "msg": ""
//    }

    private ArrayList<LawyerGroup> data;

    public ArrayList<LawyerGroup> getData() {
        return data;
    }

    public void setData(ArrayList<LawyerGroup> data) {
        this.data = data;
    }

    public class LawyerGroup implements Serializable {
        private String id;
        private String name;
        private String sort_order;
        private ArrayList<Lawyer> lawyers;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSort_order() {
            return sort_order;
        }

        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
        }

        public ArrayList<Lawyer> getLawyers() {
            return lawyers;
        }

        public void setLawyers(ArrayList<Lawyer> lawyers) {
            this.lawyers = lawyers;
        }
    }

    public class Lawyer {
        private String id;
        private String account;
        private String is_lawyer;
        private String nickname;
        private String lawyer_category_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getIs_lawyer() {
            return is_lawyer;
        }

        public void setIs_lawyer(String is_lawyer) {
            this.is_lawyer = is_lawyer;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getLawyer_category_id() {
            return lawyer_category_id;
        }

        public void setLawyer_category_id(String lawyer_category_id) {
            this.lawyer_category_id = lawyer_category_id;
        }
    }
}
