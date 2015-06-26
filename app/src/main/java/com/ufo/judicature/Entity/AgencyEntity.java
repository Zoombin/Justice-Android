package com.ufo.judicature.Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 服务机构
 */
public class AgencyEntity extends ServiceResult implements Serializable {

//    {"data":[{"id":"1","title":"\u57fa\u5c42\u6cd5\u5f8b\u670d\u52a1\u6240","sort_order":"0"},{"id":"2","title":"\u5f8b\u5e08\u4e8b\u52a1\u6240","sort_order":"1","services":[{"id":"1","name":"\u5e38\u719f\u5218\u6587\u5f8b\u5e08\u4e8b\u52a1\u6240","image":"http:\/\/code4app.com\/src\/img\/code4app_logo_1102.png","address":"\u5e38\u719f\u5e02\u4e1c\u5357\u5f00\u53d1\u533a \u864e\u5c71\u8def 204\u53f712\u697c1204\u5ba4","phone":"13514660321","latitude":"31.2582450000","longitude":"121.7195740000","created_date":null,"category_id":"2"},{"id":"2","name":"\u5e38\u719f\u5218\u6587\u4e8b\u52a1\u62402","image":"http:\/\/code4app.com\/src\/img\/code4app_logo_1102.png","address":"\u5e38\u719f\u5e02\u4e1c\u5357\u5f00\u53d1\u533a \u864e\u5c71\u8def 204\u53f712\u697c1204\u5ba4","phone":"13514660321","latitude":"31.2582450000","longitude":"121.7195740000","created_date":null,"category_id":"2"},{"id":"3","name":"\u5e38\u719f\u5218\u6587\u4e8b\u52a1\u62402","image":"http:\/\/code4app.com\/src\/img\/code4app_logo_1102.png","address":"\u5e38\u719f\u5e02\u4e1c\u5357\u5f00\u53d1\u533a \u864e\u5c71\u8def 204\u53f712\u697c1204\u5ba4","phone":"13514660321","latitude":"31.2582450000","longitude":"121.7195740000","created_date":null,"category_id":"2"}]},{"id":"3","title":"\u53f8\u6cd5\u9274\u5b9a\u673a\u6784","sort_order":"2","services":[{"id":"4","name":"\u516c\u8bc1\u59041","image":"http:\/\/code4app.com\/src\/img\/code4app_logo_1102.png","address":"\u5e38\u719f\u5e02\u4e1c\u5357\u5f00\u53d1\u533a \u864e\u5c71\u8def 204\u53f712\u697c1204\u5ba4","phone":"13514660321","latitude":"31.2582450000","longitude":"121.7195740000","created_date":null,"category_id":"3"}]},{"id":"4","title":"\u516c\u8bc1\u5904","sort_order":"3"},{"id":"5","title":"\u6cd5\u5f8b\u63f4\u52a9\u4e2d\u5fc3","sort_order":"4"}],"error":false,"msg":""}
    private ArrayList<Services> data;

    public ArrayList<Services> getData() {
        return data;
    }

    public void setData(ArrayList<Services> data) {
        this.data = data;
    }

    public class Services implements Serializable {
        private String id;
        private String title;
        private String sort_order;
        private ArrayList<Service> services;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSort_order() {
            return sort_order;
        }

        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
        }

        public ArrayList<Service> getServices() {
            return services;
        }

        public void setServices(ArrayList<Service> services) {
            this.services = services;
        }
    }

    public class Service implements Serializable {
        private String id;
        private String name;
        private String image;
        private String address;
        private String phone;
        private String latitude;
        private String longitude;
        private String created_date;
        private String category_id;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }
    }
}
