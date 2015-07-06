package com.ufo.judicature.Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 题目
 */
public class QuestionEntity extends ServiceResult implements Serializable {
//    {"data":[{"id":"2","examination_id":"1","type":"1","question":"\u6574\u4f53\u62a4\u7406\u7684\u6307\u5bfc\u601d\u60f3\u662f\uff1a",
//            "a":"\u4ee5\u95ee\u9898\u4e3a\u672c","b":"\u4ee5\u4eba\u4e3a\u672c","c":"\u4ee5\u62a4\u7406\u5bf9\u8c61\u7684\u751f\u7406\u9700\u8981\u4e3a\u672c",
//            "d":"\u4ee5\u62a4\u7406\u5bf9\u8c61\u7684\u5fc3\u7406\u9700\u8981\u4e3a\u672c","e":null,"f":null,"yes_or_no":"0","answer":"b"}],"error":false,"msg":""}
    private ArrayList<QuestionInfo> data;

    public ArrayList<QuestionInfo> getData() {
        return data;
    }

    public void setData(ArrayList<QuestionInfo> data) {
        this.data = data;
    }

    public class QuestionInfo implements Serializable {
        private String id;
        private String examination_id;
        private String type;
        private String question;
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String yes_or_no;
        private String answer;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getExamination_id() {
            return examination_id;
        }

        public void setExamination_id(String examination_id) {
            this.examination_id = examination_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }

        public String getE() {
            return e;
        }

        public void setE(String e) {
            this.e = e;
        }

        public String getF() {
            return f;
        }

        public void setF(String f) {
            this.f = f;
        }

        public String getYes_or_no() {
            return yes_or_no;
        }

        public void setYes_or_no(String yes_or_no) {
            this.yes_or_no = yes_or_no;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
