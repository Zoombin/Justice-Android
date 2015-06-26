package com.ufo.judicature.Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 服务机构
 */
public class AgencyEntity extends ServiceResult implements Serializable {

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
