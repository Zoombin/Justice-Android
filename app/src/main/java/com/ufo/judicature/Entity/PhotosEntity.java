package com.ufo.judicature.Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 图片
 */
public class PhotosEntity extends ServiceResult implements Serializable {

//    {"data":[{"id":"3","title":"\u7a7a\u964d\u5175\u8df3\u4f1e\u8bad\u7ec33","created_date":"2015-06-25 22:39:59",
//            "photos":[{"id":"4","image":"http:\/\/code4app.com\/src\/img\/code4app_logo_1102.png","content":null,"is_cover":"1","gallery_id":"3"},
//        {"id":"5","image":"http:\/\/code4app.com\/src\/img\/code4app_logo_1102.png","content":null,"is_cover":"0","gallery_id":"3"},
//        {"id":"6","image":"http:\/\/code4app.com\/src\/img\/code4app_logo_1102.png","content":null,"is_cover":"0","gallery_id":"3"}],
//        "cover":"http:\/\/code4app.com\/src\/img\/code4app_logo_1102.png"},
//
//        {"id":"2","title":"\u7a7a\u964d\u5175\u8df3\u4f1e\u8bad\u7ec32",
//            "created_date":"2015-06-12 22:39:59","photos":[{"id":"2","image":"http:\/\/code4app.com\/src\/img\/code4app_logo_1102.png","content":null,"is_cover":"1","gallery_id":"2"},{"id":"3","image":"http:\/\/code4app.com\/src\/img\/code4app_logo_1102.png","content":null,"is_cover":"0","gallery_id":"2"}],"cover":"http:\/\/code4app.com\/src\/img\/code4app_logo_1102.png"},{"id":"1","title":"\u7a7a\u964d\u5175\u8df3\u4f1e\u8bad\u7ec3","created_date":"2015-06-26 22:39:59","photos":[{"id":"1","image":"http:\/\/code4app.com\/src\/img\/code4app_logo_1102.png","content":null,"is_cover":"1","gallery_id":"1"}],"cover":"http:\/\/code4app.com\/src\/img\/code4app_logo_1102.png"}],"error":false,"msg":""}

    private ArrayList<PhotoEntity> data;

    public ArrayList<PhotoEntity> getData() {
        return data;
    }

    public void setData(ArrayList<PhotoEntity> data) {
        this.data = data;
    }

    public class PhotoEntity implements Serializable {
        private String id;
        private String title;
        private String created_date;
        private ArrayList<Photo> photos;
        private String cover;

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

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

        public ArrayList<Photo> getPhotos() {
            return photos;
        }

        public void setPhotos(ArrayList<Photo> photos) {
            this.photos = photos;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }

    public class Photo implements Serializable {
        private String id;
        private String image;
        private String gallery_id;
        private String content;
        private String is_cover;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getGallery_id() {
            return gallery_id;
        }

        public void setGallery_id(String gallery_id) {
            this.gallery_id = gallery_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIs_cover() {
            return is_cover;
        }

        public void setIs_cover(String is_cover) {
            this.is_cover = is_cover;
        }
    }
}
