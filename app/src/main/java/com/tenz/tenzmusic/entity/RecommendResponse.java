package com.tenz.tenzmusic.entity;

import java.util.List;

public class RecommendResponse {

    /**
     * timestamp : 1610440600
     * info : [{"jump_url":"","icon":"","id":1561,"is_new":0,"has_child":0,"imgurl":"","special_tag_id":1150,"song_tag_id":0,"theme":1,"bannerurl":"","name":"国语经典","album_tag_id":0},{"jump_url":"","icon":"","id":2245,"is_new":0,"has_child":0,"imgurl":"","special_tag_id":583,"song_tag_id":0,"theme":1,"bannerurl":"http://imge.kugou.com/v2/mobile_class_banner/{size}/6f96931ffde89cd1860cd2f9af1b39f2.jpg","name":"睡前好歌","album_tag_id":0},{"jump_url":"","icon":"","id":2025,"is_new":0,"has_child":0,"imgurl":"","special_tag_id":34,"song_tag_id":0,"theme":1,"bannerurl":"http://imge.kugou.com/v2/mobile_class_banner/{size}/23335d179783c0f2b5c3ededfaa9b594.jpg","name":"纯音精选","album_tag_id":0},{"jump_url":"","icon":"","id":2363,"is_new":0,"has_child":0,"imgurl":"","special_tag_id":69,"song_tag_id":0,"theme":1,"bannerurl":"http://imge.kugou.com/v2/mobile_class_banner/{size}/5fe25b1fb274e7f33027dbc287d3a2a3.jpg","name":"运动必备","album_tag_id":0},{"jump_url":"","icon":"","id":2297,"is_new":0,"has_child":0,"imgurl":"","special_tag_id":872,"song_tag_id":0,"theme":1,"bannerurl":"http://imge.kugou.com/v2/mobile_class_banner/{size}/db144362e0c2f045ee0216ddcdf5fd70.jpg","name":"网络伤感","album_tag_id":0},{"jump_url":"","icon":"","id":2251,"is_new":0,"has_child":0,"imgurl":"","special_tag_id":581,"song_tag_id":1794,"theme":1,"bannerurl":"http://imge.kugou.com/v2/mobile_class_banner/{size}/41344e747e69153ba2bd463c2ccfc18f.jpg","name":"店铺精选","album_tag_id":0}]
     */

    private int timestamp;
    private List<InfoBean> info;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * jump_url :
         * icon :
         * id : 1561
         * is_new : 0
         * has_child : 0
         * imgurl :
         * special_tag_id : 1150
         * song_tag_id : 0
         * theme : 1
         * bannerurl :
         * name : 国语经典
         * album_tag_id : 0
         */

        private String jump_url;
        private String icon;
        private int id;
        private int is_new;
        private int has_child;
        private String imgurl;
        private int special_tag_id;
        private int song_tag_id;
        private int theme;
        private String bannerurl;
        private String name;
        private int album_tag_id;

        public String getJump_url() {
            return jump_url;
        }

        public void setJump_url(String jump_url) {
            this.jump_url = jump_url;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_new() {
            return is_new;
        }

        public void setIs_new(int is_new) {
            this.is_new = is_new;
        }

        public int getHas_child() {
            return has_child;
        }

        public void setHas_child(int has_child) {
            this.has_child = has_child;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public int getSpecial_tag_id() {
            return special_tag_id;
        }

        public void setSpecial_tag_id(int special_tag_id) {
            this.special_tag_id = special_tag_id;
        }

        public int getSong_tag_id() {
            return song_tag_id;
        }

        public void setSong_tag_id(int song_tag_id) {
            this.song_tag_id = song_tag_id;
        }

        public int getTheme() {
            return theme;
        }

        public void setTheme(int theme) {
            this.theme = theme;
        }

        public String getBannerurl() {
            return bannerurl;
        }

        public void setBannerurl(String bannerurl) {
            this.bannerurl = bannerurl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAlbum_tag_id() {
            return album_tag_id;
        }

        public void setAlbum_tag_id(int album_tag_id) {
            this.album_tag_id = album_tag_id;
        }
    }
}
