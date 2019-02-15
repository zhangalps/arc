package com.example.zhanghegang.arumenu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * package : com.example.zhanghegang.arumenu.entity
 * anthor : 张贺岗
 * Date : 2019/2/15
 * Use : <类的用途>
 */
public class NewsEntity implements Serializable {

    /**
     * result : [{"_id":"1060213","title":"汉和帝刘肇驾崩","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201002/6/8711586858.jpg","year":106,"month":2,"day":13,"des":"在1913年前的今天，0106年2月13日 (农历腊月廿二)，汉和帝刘肇驾崩。","lunar":"乙巳年腊月廿二"},{"_id":"17660213","title":"英国的经济学家马尔萨斯出生","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201002/6/D1152111826.jpg","year":1766,"month":2,"day":13,"des":"在253年前的今天，1766年2月13日 (农历正月初五)，英国的经济学家马尔萨斯出生。","lunar":"丙戌年正月初五"}]
     * reason : 请求成功！
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * _id : 1060213
         * title : 汉和帝刘肇驾崩
         * pic : http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201002/6/8711586858.jpg
         * year : 106
         * month : 2
         * day : 13
         * des : 在1913年前的今天，0106年2月13日 (农历腊月廿二)，汉和帝刘肇驾崩。
         * lunar : 乙巳年腊月廿二
         */

        private String _id;
        private String title;
        private String pic;
        private int year;
        private int month;
        private int day;
        private String des;
        private String lunar;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getLunar() {
            return lunar;
        }

        public void setLunar(String lunar) {
            this.lunar = lunar;
        }
    }
}
