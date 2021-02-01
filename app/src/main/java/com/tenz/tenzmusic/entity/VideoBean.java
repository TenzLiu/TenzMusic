package com.tenz.tenzmusic.entity;

import java.util.List;

public class VideoBean {

    /**
     * userdesc :
     * hd_bitrate : 2131417
     * fhd_hash : AA2F0BBFC2F3DC1B84570D745E535FA2
     * sd_filesize : 20815663
     * qhd_filesize : 37119505
     * authors : [{"singerid":5086,"singername":"刘若英","singeravatar":"http://singerimg.kugou.com/uploadpic/softhead/{size}/20210112/20210112103951346.jpg"}]
     * sd_bitrate : 636901
     * fhd_filesize : 134698466
     * comment : 8
     * ld_bitrate : 511280
     * description : 以歌者身份回望自己的黄金年代
     * playcount : 14904
     * singername : 刘若英
     * hd_hash : 883A07BB85596248D5DCA68349B82B4A
     * publish : 2021-01-11 21:00:00
     * title : 刘若英 - 黄金年代
     * is_short : 0
     * collect_count : 13
     * download_count : 171
     * fhd_bitrate : 4121393
     * is_ugc : 0
     * mvhash : a76e1f4ce99e53b01bc795bd10299773
     * album_cover : http://imge.kugou.com/stdmusic/{size}/20210111/20210111163901233050.jpg
     * album_audio_id : 289635854
     * tags : [{"tag_id":"42042","parent_id":"4","tag_name":"流行音乐"},{"tag_id":"13","parent_id":"2","tag_name":"华语精选"},{"tag_id":"9","parent_id":"2","tag_name":"新歌推荐"},{"tag_id":"143","parent_id":"142","tag_name":"国语"},{"tag_id":"41","parent_id":"35","tag_name":"港台"},{"tag_id":"213","parent_id":"35","tag_name":"华语"},{"tag_id":"18","parent_id":"3","tag_name":"官方版"},{"tag_id":"61","parent_id":"58","tag_name":"女艺人"},{"tag_id":"1613","parent_id":"42042","tag_name":"华语流行"},{"tag_id":"1614","parent_id":"42042","tag_name":"国语流行"},{"tag_id":"374","parent_id":"42042","tag_name":"流行"}]
     * hd_filesize : 69660556
     * duration : 261361
     * videoname : 黄金年代
     * videoid : 3748394
     * remark :
     * ld_filesize : 16710028
     * sd_hash : E1C019EBED3D3F825A41EE49648E1EE7
     * qhd_bitrate : 1135752
     * img : http://imge.kugou.com/mvhdpic/{size}/20210112/20210112093452141195.jpg
     * ld_hash : 5344A5FCEB423860C6F4A5A6AC97A34B
     * video_scale : {"height":270,"width":480}
     * useravatar :
     * username :
     * userid : 0
     * qhd_hash : FE94D8DC905429A243F9DFF393B288F9
     */

    private String userdesc;
    private int hd_bitrate;
    private String fhd_hash;
    private long sd_filesize;
    private long qhd_filesize;
    private int sd_bitrate;
    private long fhd_filesize;
    private int comment;
    private int ld_bitrate;
    private String description;
    private int playcount;
    private String singername;
    private String hd_hash;
    private String publish;
    private String title;
    private int is_short;
    private int collect_count;
    private int download_count;
    private int fhd_bitrate;
    private int is_ugc;
    private String mvhash;
    private String album_cover;
    private String album_audio_id;
    private long hd_filesize;
    private long duration;
    private String videoname;
    private int videoid;
    private String remark;
    private long ld_filesize;
    private String sd_hash;
    private int qhd_bitrate;
    private String img;
    private String ld_hash;
    private VideoScaleBean video_scale;
    private String useravatar;
    private String username;
    private int userid;
    private String qhd_hash;
    private List<AuthorsBean> authors;
    private List<TagsBean> tags;

    public String getUserdesc() {
        return userdesc;
    }

    public void setUserdesc(String userdesc) {
        this.userdesc = userdesc;
    }

    public int getHd_bitrate() {
        return hd_bitrate;
    }

    public void setHd_bitrate(int hd_bitrate) {
        this.hd_bitrate = hd_bitrate;
    }

    public String getFhd_hash() {
        return fhd_hash;
    }

    public void setFhd_hash(String fhd_hash) {
        this.fhd_hash = fhd_hash;
    }

    public int getSd_bitrate() {
        return sd_bitrate;
    }

    public void setSd_bitrate(int sd_bitrate) {
        this.sd_bitrate = sd_bitrate;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getLd_bitrate() {
        return ld_bitrate;
    }

    public void setLd_bitrate(int ld_bitrate) {
        this.ld_bitrate = ld_bitrate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public String getSingername() {
        return singername;
    }

    public void setSingername(String singername) {
        this.singername = singername;
    }

    public String getHd_hash() {
        return hd_hash;
    }

    public void setHd_hash(String hd_hash) {
        this.hd_hash = hd_hash;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIs_short() {
        return is_short;
    }

    public void setIs_short(int is_short) {
        this.is_short = is_short;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public int getDownload_count() {
        return download_count;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }

    public int getFhd_bitrate() {
        return fhd_bitrate;
    }

    public void setFhd_bitrate(int fhd_bitrate) {
        this.fhd_bitrate = fhd_bitrate;
    }

    public int getIs_ugc() {
        return is_ugc;
    }

    public void setIs_ugc(int is_ugc) {
        this.is_ugc = is_ugc;
    }

    public String getMvhash() {
        return mvhash;
    }

    public void setMvhash(String mvhash) {
        this.mvhash = mvhash;
    }

    public String getAlbum_cover() {
        return album_cover;
    }

    public void setAlbum_cover(String album_cover) {
        this.album_cover = album_cover;
    }

    public String getAlbum_audio_id() {
        return album_audio_id;
    }

    public void setAlbum_audio_id(String album_audio_id) {
        this.album_audio_id = album_audio_id;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public int getVideoid() {
        return videoid;
    }

    public void setVideoid(int videoid) {
        this.videoid = videoid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSd_hash() {
        return sd_hash;
    }

    public void setSd_hash(String sd_hash) {
        this.sd_hash = sd_hash;
    }

    public int getQhd_bitrate() {
        return qhd_bitrate;
    }

    public void setQhd_bitrate(int qhd_bitrate) {
        this.qhd_bitrate = qhd_bitrate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLd_hash() {
        return ld_hash;
    }

    public void setLd_hash(String ld_hash) {
        this.ld_hash = ld_hash;
    }

    public VideoScaleBean getVideo_scale() {
        return video_scale;
    }

    public void setVideo_scale(VideoScaleBean video_scale) {
        this.video_scale = video_scale;
    }

    public String getUseravatar() {
        return useravatar;
    }

    public void setUseravatar(String useravatar) {
        this.useravatar = useravatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getQhd_hash() {
        return qhd_hash;
    }

    public void setQhd_hash(String qhd_hash) {
        this.qhd_hash = qhd_hash;
    }

    public List<AuthorsBean> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorsBean> authors) {
        this.authors = authors;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public long getSd_filesize() {
        return sd_filesize;
    }

    public void setSd_filesize(long sd_filesize) {
        this.sd_filesize = sd_filesize;
    }

    public long getQhd_filesize() {
        return qhd_filesize;
    }

    public void setQhd_filesize(long qhd_filesize) {
        this.qhd_filesize = qhd_filesize;
    }

    public long getFhd_filesize() {
        return fhd_filesize;
    }

    public void setFhd_filesize(long fhd_filesize) {
        this.fhd_filesize = fhd_filesize;
    }

    public long getHd_filesize() {
        return hd_filesize;
    }

    public void setHd_filesize(long hd_filesize) {
        this.hd_filesize = hd_filesize;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getLd_filesize() {
        return ld_filesize;
    }

    public void setLd_filesize(long ld_filesize) {
        this.ld_filesize = ld_filesize;
    }

    public static class VideoScaleBean {
        /**
         * height : 270
         * width : 480
         */

        private int height;
        private int width;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }

    public static class AuthorsBean {
        /**
         * singerid : 5086
         * singername : 刘若英
         * singeravatar : http://singerimg.kugou.com/uploadpic/softhead/{size}/20210112/20210112103951346.jpg
         */

        private int singerid;
        private String singername;
        private String singeravatar;

        public int getSingerid() {
            return singerid;
        }

        public void setSingerid(int singerid) {
            this.singerid = singerid;
        }

        public String getSingername() {
            return singername;
        }

        public void setSingername(String singername) {
            this.singername = singername;
        }

        public String getSingeravatar() {
            return singeravatar;
        }

        public void setSingeravatar(String singeravatar) {
            this.singeravatar = singeravatar;
        }
    }

    public static class TagsBean {
        /**
         * tag_id : 42042
         * parent_id : 4
         * tag_name : 流行音乐
         */

        private String tag_id;
        private String parent_id;
        private String tag_name;

        public String getTag_id() {
            return tag_id;
        }

        public void setTag_id(String tag_id) {
            this.tag_id = tag_id;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }
    }

}
