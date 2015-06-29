package com.ufo.judicature.Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 视频
 */
public class VideosEntity extends ServiceResult implements Serializable {

//    {"data":[{"id":"3","title":"\u6cb3\u5317\u67aa\u51fb\u68483","content":"\u66b4\u529b\u66b4\u529b",
//            "image":"http:\/\/code4app.com\/src\/img\/code4app_logo_1102.png","stream":"http:\/\/112.124.98.9\/dushuhu-web\/downloads\/1.mp4","created_date":"2015-06-23 11:37:27"}
//        ,{"id":"2","title":"\u6cb3\u5317\u67aa\u51fb\u6848","content":"\u66b4\u529b\u66b4\u529b","image":"http:\/\/code4app.com\/src\/img\/code4app_logo_1102.png","stream":"http:\/\/112.124.98.9\/dushuhu-web\/downloads\/1.mp4","created_date":"2015-06-15 11:37:27"},{"id":"1","title":"\u6cb3\u5317\u67aa\u51fb\u6848","content":"\u66b4\u529b\u66b4\u529b","image":"http:\/\/code4app.com\/src\/img\/code4app_logo_1102.png","stream":"http:\/\/112.124.98.9\/dushuhu-web\/downloads\/1.mp4","created_date":"2015-06-27 11:37:27"}],"error":false,"msg":""}
    private ArrayList<VideoEntity> data;

    public ArrayList<VideoEntity> getData() {
        return data;
    }

    public void setData(ArrayList<VideoEntity> data) {
        this.data = data;
    }

    public class VideoEntity implements Serializable {
        private String id;
        private String title;
        private String image;
        private String content;
        private String stream;
        private String created_date;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getContent() {
            return content;
        }

        public String getStream() {
            return stream;
        }

        public void setStream(String stream) {
            this.stream = stream;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }
    }
}
