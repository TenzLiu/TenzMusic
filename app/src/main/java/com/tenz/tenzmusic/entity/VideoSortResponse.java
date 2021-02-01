package com.tenz.tenzmusic.entity;

import java.util.List;

public class VideoSortResponse {

    /**
     * list : [{"channel_id":54,"sort_way":5,"is_short":0,"name":"舞蹈","h5_link":""},{"channel_id":49,"sort_way":5,"is_short":0,"name":"搞笑","h5_link":""},{"channel_id":29,"sort_way":5,"is_short":0,"name":"儿童","h5_link":""},{"channel_id":40,"sort_way":5,"is_short":0,"name":"影视","h5_link":""},{"channel_id":91,"sort_way":5,"is_short":0,"name":"MV","h5_link":""},{"channel_id":142,"sort_way":5,"is_short":0,"name":"音乐盘点","h5_link":""},{"channel_id":38,"sort_way":5,"is_short":0,"name":"动漫","h5_link":""},{"channel_id":22,"sort_way":5,"is_short":0,"name":"现场","h5_link":""},{"channel_id":68,"sort_way":5,"is_short":0,"name":"综艺","h5_link":""},{"channel_id":117,"sort_way":1,"is_short":0,"name":"TME LIVE","h5_link":""},{"channel_id":67,"sort_way":7,"is_short":0,"name":"翻唱","h5_link":""},{"channel_id":141,"sort_way":1,"is_short":0,"name":"酷爱大牌","h5_link":""},{"channel_id":34,"sort_way":5,"is_short":0,"name":"华语","h5_link":""},{"channel_id":75,"sort_way":5,"is_short":0,"name":"韩国","h5_link":""},{"channel_id":74,"sort_way":5,"is_short":0,"name":"日本","h5_link":""},{"channel_id":39,"sort_way":5,"is_short":0,"name":"欧美","h5_link":""}]
     * timestamp : 1610588840
     */

    private int timestamp;
    private List<ListBean> list;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * channel_id : 54
         * sort_way : 5
         * is_short : 0
         * name : 舞蹈
         * h5_link :
         */

        private int channel_id;
        private int sort_way;
        private int is_short;
        private String name;
        private String h5_link;

        public int getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(int channel_id) {
            this.channel_id = channel_id;
        }

        public int getSort_way() {
            return sort_way;
        }

        public void setSort_way(int sort_way) {
            this.sort_way = sort_way;
        }

        public int getIs_short() {
            return is_short;
        }

        public void setIs_short(int is_short) {
            this.is_short = is_short;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getH5_link() {
            return h5_link;
        }

        public void setH5_link(String h5_link) {
            this.h5_link = h5_link;
        }
    }
}
