package com.tenz.tenzmusic.entity;

import com.google.gson.annotations.SerializedName;

public class SongBean {

    /**
     * pay_type_320 : 3
     * m4afilesize : 0
     * price_sq : 200
     * first : 1
     * filesize : 4086973
     * bitrate : 128
     * trans_param : {"cpy_grade":5,"musicpack_advance":0,"cpy_level":1,"hash_offset":{"file_type":0,"start_byte":0,"start_ms":0,"offset_hash":"D93AD88EE0FAAA447DADE77045E3C679","end_ms":60000,"end_byte":961056},"pay_block_tpl":1,"cpy_attr0":0,"display_rate":0,"appid_block":"3124","display":0,"cid":129141438}
     * price : 200
     * inlist : 1
     * old_cpy : 0
     * pkg_price_sq : 1
     * pay_type : 3
     * musical : null
     * topic_url :
     * rp_type : audio
     * pkg_price : 1
     * recommend_reason :
     * filename : 刘若英 - 黄金年代
     * price_320 : 200
     * topic_url_320 :
     * album_audio_id : 289635854
     * sqfilesize : 22952247
     * hash : 1CB58B03E930B9C9A45307361AE5EB39
     * mvhash : 16C853F3E7B9FA3E99E9B37E21DC725A
     * album_cover : http://imge.kugou.com/stdmusic/{size}/20210111/20210111163901233050.jpg
     * privilege : 8
     * fail_process_320 : 4
     * addtime : 2021-01-11 20:54:29
     * pkg_price_320 : 1
     * duration : 255
     * feetype : 0
     * remark : 黄金年代
     * sqhash : E15D70416ED9FDBA3B0633E453AF61EA
     * 320filesize : 10217181
     * rp_publish : 1
     * cover : http://imge.kugou.com/stdmusic/{size}/20210111/20210111163901233050.jpg
     * topic_url_sq :
     * 320hash : E50F4E8E97348A2C39FFC82B6AE0221C
     * isfirst : 0
     * fail_process : 4
     * 320privilege : 10
     * album_id : 41111907
     * has_accompany : 0
     * audio_id : 91684514
     * pay_type_sq : 3
     * extname : mp3
     * sqprivilege : 10
     * fail_process_sq : 4
     * issue : 16
     */

    private int pay_type_320;
    private long m4afilesize;
    private int price_sq;
    private int first;
    private long filesize;
    private int bitrate;
    private TransParamBean trans_param;
    private int price;
    private int inlist;
    private int old_cpy;
    private int pkg_price_sq;
    private int pay_type;
    private Object musical;
    private String topic_url;
    private String rp_type;
    private int pkg_price;
    private String recommend_reason;
    private String filename;
    private int price_320;
    private String topic_url_320;
    private int album_audio_id;
    private long sqfilesize;
    private String hash;
    private String mvhash;
    private String album_cover;
    private int privilege;
    private int fail_process_320;
    private String addtime;
    private int pkg_price_320;
    private long duration;
    private int feetype;
    private String remark;
    private String sqhash;
    @SerializedName("320filesize")
    private int _$320filesize;
    private int rp_publish;
    private String cover;
    private String topic_url_sq;
    @SerializedName("320hash")
    private String _$320hash;
    private int isfirst;
    private int fail_process;
    @SerializedName("320privilege")
    private int _$320privilege;
    private String album_id;
    private int has_accompany;
    private int audio_id;
    private int pay_type_sq;
    private String extname;
    private int sqprivilege;
    private int fail_process_sq;
    private int issue;

    public int getPay_type_320() {
        return pay_type_320;
    }

    public void setPay_type_320(int pay_type_320) {
        this.pay_type_320 = pay_type_320;
    }

    public int getPrice_sq() {
        return price_sq;
    }

    public void setPrice_sq(int price_sq) {
        this.price_sq = price_sq;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public TransParamBean getTrans_param() {
        return trans_param;
    }

    public void setTrans_param(TransParamBean trans_param) {
        this.trans_param = trans_param;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getInlist() {
        return inlist;
    }

    public void setInlist(int inlist) {
        this.inlist = inlist;
    }

    public int getOld_cpy() {
        return old_cpy;
    }

    public void setOld_cpy(int old_cpy) {
        this.old_cpy = old_cpy;
    }

    public int getPkg_price_sq() {
        return pkg_price_sq;
    }

    public void setPkg_price_sq(int pkg_price_sq) {
        this.pkg_price_sq = pkg_price_sq;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public Object getMusical() {
        return musical;
    }

    public void setMusical(Object musical) {
        this.musical = musical;
    }

    public String getTopic_url() {
        return topic_url;
    }

    public void setTopic_url(String topic_url) {
        this.topic_url = topic_url;
    }

    public String getRp_type() {
        return rp_type;
    }

    public void setRp_type(String rp_type) {
        this.rp_type = rp_type;
    }

    public int getPkg_price() {
        return pkg_price;
    }

    public void setPkg_price(int pkg_price) {
        this.pkg_price = pkg_price;
    }

    public String getRecommend_reason() {
        return recommend_reason;
    }

    public void setRecommend_reason(String recommend_reason) {
        this.recommend_reason = recommend_reason;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getPrice_320() {
        return price_320;
    }

    public void setPrice_320(int price_320) {
        this.price_320 = price_320;
    }

    public String getTopic_url_320() {
        return topic_url_320;
    }

    public void setTopic_url_320(String topic_url_320) {
        this.topic_url_320 = topic_url_320;
    }

    public int getAlbum_audio_id() {
        return album_audio_id;
    }

    public void setAlbum_audio_id(int album_audio_id) {
        this.album_audio_id = album_audio_id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
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

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public int getFail_process_320() {
        return fail_process_320;
    }

    public void setFail_process_320(int fail_process_320) {
        this.fail_process_320 = fail_process_320;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public int getPkg_price_320() {
        return pkg_price_320;
    }

    public void setPkg_price_320(int pkg_price_320) {
        this.pkg_price_320 = pkg_price_320;
    }

    public int getFeetype() {
        return feetype;
    }

    public void setFeetype(int feetype) {
        this.feetype = feetype;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSqhash() {
        return sqhash;
    }

    public void setSqhash(String sqhash) {
        this.sqhash = sqhash;
    }

    public int get_$320filesize() {
        return _$320filesize;
    }

    public void set_$320filesize(int _$320filesize) {
        this._$320filesize = _$320filesize;
    }

    public int getRp_publish() {
        return rp_publish;
    }

    public void setRp_publish(int rp_publish) {
        this.rp_publish = rp_publish;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTopic_url_sq() {
        return topic_url_sq;
    }

    public void setTopic_url_sq(String topic_url_sq) {
        this.topic_url_sq = topic_url_sq;
    }

    public String get_$320hash() {
        return _$320hash;
    }

    public void set_$320hash(String _$320hash) {
        this._$320hash = _$320hash;
    }

    public int getIsfirst() {
        return isfirst;
    }

    public void setIsfirst(int isfirst) {
        this.isfirst = isfirst;
    }

    public int getFail_process() {
        return fail_process;
    }

    public void setFail_process(int fail_process) {
        this.fail_process = fail_process;
    }

    public int get_$320privilege() {
        return _$320privilege;
    }

    public void set_$320privilege(int _$320privilege) {
        this._$320privilege = _$320privilege;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public int getHas_accompany() {
        return has_accompany;
    }

    public void setHas_accompany(int has_accompany) {
        this.has_accompany = has_accompany;
    }

    public int getAudio_id() {
        return audio_id;
    }

    public void setAudio_id(int audio_id) {
        this.audio_id = audio_id;
    }

    public int getPay_type_sq() {
        return pay_type_sq;
    }

    public void setPay_type_sq(int pay_type_sq) {
        this.pay_type_sq = pay_type_sq;
    }

    public String getExtname() {
        return extname;
    }

    public void setExtname(String extname) {
        this.extname = extname;
    }

    public int getSqprivilege() {
        return sqprivilege;
    }

    public void setSqprivilege(int sqprivilege) {
        this.sqprivilege = sqprivilege;
    }

    public int getFail_process_sq() {
        return fail_process_sq;
    }

    public void setFail_process_sq(int fail_process_sq) {
        this.fail_process_sq = fail_process_sq;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    public long getM4afilesize() {
        return m4afilesize;
    }

    public void setM4afilesize(long m4afilesize) {
        this.m4afilesize = m4afilesize;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public long getSqfilesize() {
        return sqfilesize;
    }

    public void setSqfilesize(long sqfilesize) {
        this.sqfilesize = sqfilesize;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public static class TransParamBean {
        /**
         * cpy_grade : 5
         * musicpack_advance : 0
         * cpy_level : 1
         * hash_offset : {"file_type":0,"start_byte":0,"start_ms":0,"offset_hash":"D93AD88EE0FAAA447DADE77045E3C679","end_ms":60000,"end_byte":961056}
         * pay_block_tpl : 1
         * cpy_attr0 : 0
         * display_rate : 0
         * appid_block : 3124
         * display : 0
         * cid : 129141438
         */

        private int cpy_grade;
        private int musicpack_advance;
        private int cpy_level;
        private HashOffsetBean hash_offset;
        private int pay_block_tpl;
        private int cpy_attr0;
        private int display_rate;
        private String appid_block;
        private int display;
        private int cid;

        public int getCpy_grade() {
            return cpy_grade;
        }

        public void setCpy_grade(int cpy_grade) {
            this.cpy_grade = cpy_grade;
        }

        public int getMusicpack_advance() {
            return musicpack_advance;
        }

        public void setMusicpack_advance(int musicpack_advance) {
            this.musicpack_advance = musicpack_advance;
        }

        public int getCpy_level() {
            return cpy_level;
        }

        public void setCpy_level(int cpy_level) {
            this.cpy_level = cpy_level;
        }

        public HashOffsetBean getHash_offset() {
            return hash_offset;
        }

        public void setHash_offset(HashOffsetBean hash_offset) {
            this.hash_offset = hash_offset;
        }

        public int getPay_block_tpl() {
            return pay_block_tpl;
        }

        public void setPay_block_tpl(int pay_block_tpl) {
            this.pay_block_tpl = pay_block_tpl;
        }

        public int getCpy_attr0() {
            return cpy_attr0;
        }

        public void setCpy_attr0(int cpy_attr0) {
            this.cpy_attr0 = cpy_attr0;
        }

        public int getDisplay_rate() {
            return display_rate;
        }

        public void setDisplay_rate(int display_rate) {
            this.display_rate = display_rate;
        }

        public String getAppid_block() {
            return appid_block;
        }

        public void setAppid_block(String appid_block) {
            this.appid_block = appid_block;
        }

        public int getDisplay() {
            return display;
        }

        public void setDisplay(int display) {
            this.display = display;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public static class HashOffsetBean {
            /**
             * file_type : 0
             * start_byte : 0
             * start_ms : 0
             * offset_hash : D93AD88EE0FAAA447DADE77045E3C679
             * end_ms : 60000
             * end_byte : 961056
             */

            private int file_type;
            private int start_byte;
            private int start_ms;
            private String offset_hash;
            private int end_ms;
            private int end_byte;

            public int getFile_type() {
                return file_type;
            }

            public void setFile_type(int file_type) {
                this.file_type = file_type;
            }

            public int getStart_byte() {
                return start_byte;
            }

            public void setStart_byte(int start_byte) {
                this.start_byte = start_byte;
            }

            public int getStart_ms() {
                return start_ms;
            }

            public void setStart_ms(int start_ms) {
                this.start_ms = start_ms;
            }

            public String getOffset_hash() {
                return offset_hash;
            }

            public void setOffset_hash(String offset_hash) {
                this.offset_hash = offset_hash;
            }

            public int getEnd_ms() {
                return end_ms;
            }

            public void setEnd_ms(int end_ms) {
                this.end_ms = end_ms;
            }

            public int getEnd_byte() {
                return end_byte;
            }

            public void setEnd_byte(int end_byte) {
                this.end_byte = end_byte;
            }
        }
    }

}
