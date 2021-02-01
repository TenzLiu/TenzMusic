package com.tenz.tenzmusic.entity;

import java.util.List;

public class SongSheetResponse {

    /**
     * timestamp : 1610447024
     * info : [{"rankid":6666,"id":1,"children":[],"intro":"数据来源：酷狗\r\n排序方式：按歌曲搜索播放量的涨幅排序\r\n更新周期：每天","album_img_9":"http://imge.kugou.com/stdmusic/{size}/20210109/20210109121306222222.jpg","banner7url":"http://imge.kugou.com/mcommon/{size}/20190906/20190906162522894877.jpg","jump_title":"","rankname":"酷狗飙升榜","isvol":1,"banner_9":"http://imge.kugou.com/mcommon/{size}/20190909/20190909175722740417.png","issue":12,"custom_type":0,"img_9":"","classify":1,"update_frequency":"每天","imgurl":"http://imge.kugou.com/mcommon/{size}/20190906/20190906162520714932.jpg","show_play_button":0,"songinfo":[{"songname":"胖虎 - 白月光与朱砂痣"},{"songname":"小匆匆 - 忘情果 (女声版)"},{"songname":"小眼鑫 - 美丽的神话"}],"bannerurl":"http://imge.kugou.com/mcommonbanner/{size}/20190214/20190214100333414437.jpg","jump_url":"","haschildren":0,"ranktype":2}]
     * total : 33
     */

    private int timestamp;
    private int total;
    private List<InfoBean> info;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * rankid : 6666
         * id : 1
         * children : []
         * intro : 数据来源：酷狗
         排序方式：按歌曲搜索播放量的涨幅排序
         更新周期：每天
         * album_img_9 : http://imge.kugou.com/stdmusic/{size}/20210109/20210109121306222222.jpg
         * banner7url : http://imge.kugou.com/mcommon/{size}/20190906/20190906162522894877.jpg
         * jump_title :
         * rankname : 酷狗飙升榜
         * isvol : 1
         * banner_9 : http://imge.kugou.com/mcommon/{size}/20190909/20190909175722740417.png
         * issue : 12
         * custom_type : 0
         * img_9 :
         * classify : 1
         * update_frequency : 每天
         * imgurl : http://imge.kugou.com/mcommon/{size}/20190906/20190906162520714932.jpg
         * show_play_button : 0
         * songinfo : [{"songname":"胖虎 - 白月光与朱砂痣"},{"songname":"小匆匆 - 忘情果 (女声版)"},{"songname":"小眼鑫 - 美丽的神话"}]
         * bannerurl : http://imge.kugou.com/mcommonbanner/{size}/20190214/20190214100333414437.jpg
         * jump_url :
         * haschildren : 0
         * ranktype : 2
         */

        private int rankid;
        private int id;
        private String intro;
        private String album_img_9;
        private String banner7url;
        private String jump_title;
        private String rankname;
        private int isvol;
        private String banner_9;
        private int issue;
        private int custom_type;
        private String img_9;
        private int classify;
        private String update_frequency;
        private String imgurl;
        private int show_play_button;
        private String bannerurl;
        private String jump_url;
        private int haschildren;
        private int ranktype;
        private List<?> children;
        private List<SonginfoBean> songinfo;

        public int getRankid() {
            return rankid;
        }

        public void setRankid(int rankid) {
            this.rankid = rankid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getAlbum_img_9() {
            return album_img_9;
        }

        public void setAlbum_img_9(String album_img_9) {
            this.album_img_9 = album_img_9;
        }

        public String getBanner7url() {
            return banner7url;
        }

        public void setBanner7url(String banner7url) {
            this.banner7url = banner7url;
        }

        public String getJump_title() {
            return jump_title;
        }

        public void setJump_title(String jump_title) {
            this.jump_title = jump_title;
        }

        public String getRankname() {
            return rankname;
        }

        public void setRankname(String rankname) {
            this.rankname = rankname;
        }

        public int getIsvol() {
            return isvol;
        }

        public void setIsvol(int isvol) {
            this.isvol = isvol;
        }

        public String getBanner_9() {
            return banner_9;
        }

        public void setBanner_9(String banner_9) {
            this.banner_9 = banner_9;
        }

        public int getIssue() {
            return issue;
        }

        public void setIssue(int issue) {
            this.issue = issue;
        }

        public int getCustom_type() {
            return custom_type;
        }

        public void setCustom_type(int custom_type) {
            this.custom_type = custom_type;
        }

        public String getImg_9() {
            return img_9;
        }

        public void setImg_9(String img_9) {
            this.img_9 = img_9;
        }

        public int getClassify() {
            return classify;
        }

        public void setClassify(int classify) {
            this.classify = classify;
        }

        public String getUpdate_frequency() {
            return update_frequency;
        }

        public void setUpdate_frequency(String update_frequency) {
            this.update_frequency = update_frequency;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public int getShow_play_button() {
            return show_play_button;
        }

        public void setShow_play_button(int show_play_button) {
            this.show_play_button = show_play_button;
        }

        public String getBannerurl() {
            return bannerurl;
        }

        public void setBannerurl(String bannerurl) {
            this.bannerurl = bannerurl;
        }

        public String getJump_url() {
            return jump_url;
        }

        public void setJump_url(String jump_url) {
            this.jump_url = jump_url;
        }

        public int getHaschildren() {
            return haschildren;
        }

        public void setHaschildren(int haschildren) {
            this.haschildren = haschildren;
        }

        public int getRanktype() {
            return ranktype;
        }

        public void setRanktype(int ranktype) {
            this.ranktype = ranktype;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }

        public List<SonginfoBean> getSonginfo() {
            return songinfo;
        }

        public void setSonginfo(List<SonginfoBean> songinfo) {
            this.songinfo = songinfo;
        }

        public static class SonginfoBean {
            /**
             * songname : 胖虎 - 白月光与朱砂痣
             */

            private String songname;

            public String getSongname() {
                return songname;
            }

            public void setSongname(String songname) {
                this.songname = songname;
            }
        }
    }
}
