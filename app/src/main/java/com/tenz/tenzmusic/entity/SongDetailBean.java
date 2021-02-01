package com.tenz.tenzmusic.entity;

import java.util.List;

public class SongDetailBean {


    /**
     * hash : 2b1473d8770ee75f5377d54da8054394
     * timelength : 269035
     * filesize : 4305181
     * audio_name : 陈奕迅 - 是但求其爱
     * have_album : 1
     * album_name : 是但求其爱
     * album_id : 40224966
     * img : http://imge.kugou.com/stdmusic/20201119/20201119171503191308.jpg
     * have_mv : 1
     * video_id : 3456026
     * author_name : 陈奕迅
     * song_name : 是但求其爱
     * lyrics : ﻿[id:$00000000] [ar:陈奕迅] [ti:是但求其爱] [by:] [hash:2b1473d8770ee75f5377d54da8054394] [al:] [sign:] [qq:] [total:269035] [offset:0] [00:00.00]陈奕迅 - 是但求其爱 [00:00.93]作词：小克 [00:00.93]作曲：林家谦 [00:00.93]编曲：林家谦 [00:00.93]监制：陈奕迅、林家谦 [00:00.93]若爱是但求开心我问 [00:05.98]要不要求其伤心 [00:24.49]论尽半生不懂爱 [00:29.70]回头没有心计划未来 [00:34.76]才来独处好好检讨什么叫爱 [00:42.37]你便来 [00:45.31]混乱里结识到你 [00:50.49]浪漫叫一切粉饰同盼待 [00:55.66]某一刹骤觉感情深得可爱 [01:01.58]在倾吐那刻回响 [01:07.55]感情从不是爱 [01:13.99]若爱是但求终身你问 [01:19.10]怕只怕求其终生被困 [01:24.12]你宠爱父母亲 [01:26.80]我为良朋怜悯 [01:29.44]怎都算是个好人 [01:34.78]若爱是但求衷心我问 [01:39.74]要不要求其忠心 [01:45.21]纵双方理念多相同 [01:49.19]却不相融 [01:51.85]莫论配衬 [02:11.13]再会时 [02:14.03]没料到深深拥抱 [02:19.14]合力擦出了火花和意外 [02:24.24]某一刹幻觉恋情可一可再 [02:30.78]在参透那刻回想 [02:36.01]恋情全不是爱 [02:42.54]若爱是但求终身你问 [02:47.55]怕只怕求其终生被困 [02:52.86]你工作觅满分 [02:55.50]我为前途勤奋 [02:58.34]怎可再另有心神 [03:03.21]若爱是但求衷心我问 [03:08.42]要不要求其忠心 [03:13.80]纵双方理念多相同 [03:17.66]却不相融 [03:20.29]莫论配衬 [03:24.50]若爱是但求安心 [03:29.54]怕只怕求其安稳 [03:44.77]若爱是但求今生抱憾 [03:50.34]要不要求其他生 [03:55.56]看双方各自的本能 [03:59.46]爱的伤痕 [04:02.34]极度配衬 [04:06.45]爱七色五味多纷陈 [04:10.82]更多灰尘 [04:15.07]落入五蕴
     * author_id : 420
     * privilege : 8
     * privilege2 : 1000
     * play_url : https://webfs.yun.kugou.com/202101141649/1ff7522451e946f33bbc00940bcb9fa2/G244/M0A/05/14/NA4DAF-2N-GAcHS1AEGxHbdtlt8530.mp3
     * authors : [{"author_id":"420","author_name":"陈奕迅","is_publish":"1","sizable_avatar":"http://singerimg.kugou.com/uploadpic/softhead/{size}/20180622/20180622193316603.jpg","avatar":"http://singerimg.kugou.com/uploadpic/softhead/400/20180622/20180622193316603.jpg"}]
     * is_free_part : 0
     * bitrate : 128
     * recommend_album_id : 40224966
     * audio_id : 87001279
     * has_privilege : true
     * play_backup_url : https://webfs.cloud.kugou.com/202101141649/ca5387f865bcd401fef43fcbf887a32e/G244/M0A/05/14/NA4DAF-2N-GAcHS1AEGxHbdtlt8530.mp3
     */

    private String hash;
    private long timelength;
    private long filesize;
    private String audio_name;
    private int have_album;
    private String album_name;
    private String album_id;
    private String img;
    private int have_mv;
    private String video_id;
    private String author_name;
    private String song_name;
    private String lyrics;
    private String author_id;
    private int privilege;
    private String privilege2;
    private String play_url;
    private int is_free_part;
    private int bitrate;
    private String recommend_album_id;
    private String audio_id;
    private boolean has_privilege;
    private String play_backup_url;
    private List<AuthorsBean> authors;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getTimelength() {
        return timelength;
    }

    public void setTimelength(long timelength) {
        this.timelength = timelength;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public String getAudio_name() {
        return audio_name;
    }

    public void setAudio_name(String audio_name) {
        this.audio_name = audio_name;
    }

    public int getHave_album() {
        return have_album;
    }

    public void setHave_album(int have_album) {
        this.have_album = have_album;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getHave_mv() {
        return have_mv;
    }

    public void setHave_mv(int have_mv) {
        this.have_mv = have_mv;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public String getPrivilege2() {
        return privilege2;
    }

    public void setPrivilege2(String privilege2) {
        this.privilege2 = privilege2;
    }

    public String getPlay_url() {
        return play_url;
    }

    public void setPlay_url(String play_url) {
        this.play_url = play_url;
    }

    public int getIs_free_part() {
        return is_free_part;
    }

    public void setIs_free_part(int is_free_part) {
        this.is_free_part = is_free_part;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public String getRecommend_album_id() {
        return recommend_album_id;
    }

    public void setRecommend_album_id(String recommend_album_id) {
        this.recommend_album_id = recommend_album_id;
    }

    public String getAudio_id() {
        return audio_id;
    }

    public void setAudio_id(String audio_id) {
        this.audio_id = audio_id;
    }

    public boolean isHas_privilege() {
        return has_privilege;
    }

    public void setHas_privilege(boolean has_privilege) {
        this.has_privilege = has_privilege;
    }

    public String getPlay_backup_url() {
        return play_backup_url;
    }

    public void setPlay_backup_url(String play_backup_url) {
        this.play_backup_url = play_backup_url;
    }

    public List<AuthorsBean> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorsBean> authors) {
        this.authors = authors;
    }

    public static class AuthorsBean {
        /**
         * author_id : 420
         * author_name : 陈奕迅
         * is_publish : 1
         * sizable_avatar : http://singerimg.kugou.com/uploadpic/softhead/{size}/20180622/20180622193316603.jpg
         * avatar : http://singerimg.kugou.com/uploadpic/softhead/400/20180622/20180622193316603.jpg
         */

        private String author_id;
        private String author_name;
        private String is_publish;
        private String sizable_avatar;
        private String avatar;

        public String getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getIs_publish() {
            return is_publish;
        }

        public void setIs_publish(String is_publish) {
            this.is_publish = is_publish;
        }

        public String getSizable_avatar() {
            return sizable_avatar;
        }

        public void setSizable_avatar(String sizable_avatar) {
            this.sizable_avatar = sizable_avatar;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
