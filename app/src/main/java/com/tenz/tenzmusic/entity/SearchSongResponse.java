package com.tenz.tenzmusic.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchSongResponse {

    /**
     * aggregation : [{"key":"DJ","count":480},{"key":"现场","count":329},{"key":"广场舞","count":43},{"key":"伴奏","count":480},{"key":"铃声","count":0}]
     * tab :
     * info : [{"othername_original":"","pay_type_320":0,"m4afilesize":330942,"price_sq":0,"isoriginal":0,"filesize":1243428,"source":"","bitrate":128,"topic":"","trans_param":{"cid":-1,"pay_block_tpl":1,"musicpack_advance":0,"display_rate":0,"display":0,"cpy_attr0":0},"price":0,"Accompany":1,"old_cpy":1,"songname_original":"你好<\/em>","singername":"贝瓦儿歌","pay_type":0,"sourceid":0,"topic_url":"","fail_process_320":0,"pkg_price":0,"feetype":0,"filename":"贝瓦儿歌 - 你好<\/em>","price_320":0,"songname":"你好<\/em>","group":[],"hash":"ee79cfef5dd44b4f420146f9b267be06","mvhash":"f5eeb27b6397b1a2617097b91417db58","rp_type":"audio","privilege":0,"album_audio_id":89340334,"rp_publish":1,"album_id":"4098688","ownercount":15,"fold_type":0,"audio_id":2720910,"pkg_price_sq":0,"320filesize":0,"isnew":0,"duration":77,"pkg_price_320":0,"srctype":1,"fail_process_sq":0,"sqfilesize":0,"fail_process":0,"320hash":"","extname":"mp3","sqhash":"","pay_type_sq":0,"320privilege":0,"sqprivilege":0,"album_name":"贝瓦儿歌 系列13","othername":""}]
     * correctiontype : 0
     * timestamp : 1611219937
     * allowerr : 0
     * total : 164
     * istag : 0
     * istagresult : 0
     * forcecorrection : 0
     * correctiontip :
     */

    private String tab;
    private int correctiontype;
    private int timestamp;
    private int allowerr;
    private int total;
    private int istag;
    private int istagresult;
    private int forcecorrection;
    private String correctiontip;
    private List<AggregationBean> aggregation;
    private List<InfoBean> info;

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public int getCorrectiontype() {
        return correctiontype;
    }

    public void setCorrectiontype(int correctiontype) {
        this.correctiontype = correctiontype;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getAllowerr() {
        return allowerr;
    }

    public void setAllowerr(int allowerr) {
        this.allowerr = allowerr;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIstag() {
        return istag;
    }

    public void setIstag(int istag) {
        this.istag = istag;
    }

    public int getIstagresult() {
        return istagresult;
    }

    public void setIstagresult(int istagresult) {
        this.istagresult = istagresult;
    }

    public int getForcecorrection() {
        return forcecorrection;
    }

    public void setForcecorrection(int forcecorrection) {
        this.forcecorrection = forcecorrection;
    }

    public String getCorrectiontip() {
        return correctiontip;
    }

    public void setCorrectiontip(String correctiontip) {
        this.correctiontip = correctiontip;
    }

    public List<AggregationBean> getAggregation() {
        return aggregation;
    }

    public void setAggregation(List<AggregationBean> aggregation) {
        this.aggregation = aggregation;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class AggregationBean {
        /**
         * key : DJ
         * count : 480
         */

        private String key;
        private int count;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    public static class InfoBean {
        /**
         * othername_original :
         * pay_type_320 : 0
         * m4afilesize : 330942
         * price_sq : 0
         * isoriginal : 0
         * filesize : 1243428
         * source :
         * bitrate : 128
         * topic :
         * trans_param : {"cid":-1,"pay_block_tpl":1,"musicpack_advance":0,"display_rate":0,"display":0,"cpy_attr0":0}
         * price : 0
         * Accompany : 1
         * old_cpy : 1
         * songname_original : 你好</em>
         * singername : 贝瓦儿歌
         * pay_type : 0
         * sourceid : 0
         * topic_url :
         * fail_process_320 : 0
         * pkg_price : 0
         * feetype : 0
         * filename : 贝瓦儿歌 - 你好</em>
         * price_320 : 0
         * songname : 你好</em>
         * group : []
         * hash : ee79cfef5dd44b4f420146f9b267be06
         * mvhash : f5eeb27b6397b1a2617097b91417db58
         * rp_type : audio
         * privilege : 0
         * album_audio_id : 89340334
         * rp_publish : 1
         * album_id : 4098688
         * ownercount : 15
         * fold_type : 0
         * audio_id : 2720910
         * pkg_price_sq : 0
         * 320filesize : 0
         * isnew : 0
         * duration : 77
         * pkg_price_320 : 0
         * srctype : 1
         * fail_process_sq : 0
         * sqfilesize : 0
         * fail_process : 0
         * 320hash :
         * extname : mp3
         * sqhash :
         * pay_type_sq : 0
         * 320privilege : 0
         * sqprivilege : 0
         * album_name : 贝瓦儿歌 系列13
         * othername :
         */

        private String othername_original;
        private int pay_type_320;
        private int m4afilesize;
        private int price_sq;
        private int isoriginal;
        private int filesize;
        private String source;
        private int bitrate;
        private String topic;
        private TransParamBean trans_param;
        private int price;
        private int Accompany;
        private int old_cpy;
        private String songname_original;
        private String singername;
        private int pay_type;
        private int sourceid;
        private String topic_url;
        private int fail_process_320;
        private int pkg_price;
        private int feetype;
        private String filename;
        private int price_320;
        private String songname;
        private String hash;
        private String mvhash;
        private String rp_type;
        private int privilege;
        private int album_audio_id;
        private int rp_publish;
        private String album_id;
        private int ownercount;
        private int fold_type;
        private int audio_id;
        private int pkg_price_sq;
        @SerializedName("320filesize")
        private int _$320filesize;
        private int isnew;
        private int duration;
        private int pkg_price_320;
        private int srctype;
        private int fail_process_sq;
        private int sqfilesize;
        private int fail_process;
        @SerializedName("320hash")
        private String _$320hash;
        private String extname;
        private String sqhash;
        private int pay_type_sq;
        @SerializedName("320privilege")
        private int _$320privilege;
        private int sqprivilege;
        private String album_name;
        private String othername;
        private List<?> group;

        public String getOthername_original() {
            return othername_original;
        }

        public void setOthername_original(String othername_original) {
            this.othername_original = othername_original;
        }

        public int getPay_type_320() {
            return pay_type_320;
        }

        public void setPay_type_320(int pay_type_320) {
            this.pay_type_320 = pay_type_320;
        }

        public int getM4afilesize() {
            return m4afilesize;
        }

        public void setM4afilesize(int m4afilesize) {
            this.m4afilesize = m4afilesize;
        }

        public int getPrice_sq() {
            return price_sq;
        }

        public void setPrice_sq(int price_sq) {
            this.price_sq = price_sq;
        }

        public int getIsoriginal() {
            return isoriginal;
        }

        public void setIsoriginal(int isoriginal) {
            this.isoriginal = isoriginal;
        }

        public int getFilesize() {
            return filesize;
        }

        public void setFilesize(int filesize) {
            this.filesize = filesize;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public int getBitrate() {
            return bitrate;
        }

        public void setBitrate(int bitrate) {
            this.bitrate = bitrate;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
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

        public int getAccompany() {
            return Accompany;
        }

        public void setAccompany(int Accompany) {
            this.Accompany = Accompany;
        }

        public int getOld_cpy() {
            return old_cpy;
        }

        public void setOld_cpy(int old_cpy) {
            this.old_cpy = old_cpy;
        }

        public String getSongname_original() {
            return songname_original;
        }

        public void setSongname_original(String songname_original) {
            this.songname_original = songname_original;
        }

        public String getSingername() {
            return singername;
        }

        public void setSingername(String singername) {
            this.singername = singername;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public int getSourceid() {
            return sourceid;
        }

        public void setSourceid(int sourceid) {
            this.sourceid = sourceid;
        }

        public String getTopic_url() {
            return topic_url;
        }

        public void setTopic_url(String topic_url) {
            this.topic_url = topic_url;
        }

        public int getFail_process_320() {
            return fail_process_320;
        }

        public void setFail_process_320(int fail_process_320) {
            this.fail_process_320 = fail_process_320;
        }

        public int getPkg_price() {
            return pkg_price;
        }

        public void setPkg_price(int pkg_price) {
            this.pkg_price = pkg_price;
        }

        public int getFeetype() {
            return feetype;
        }

        public void setFeetype(int feetype) {
            this.feetype = feetype;
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

        public String getSongname() {
            return songname;
        }

        public void setSongname(String songname) {
            this.songname = songname;
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

        public String getRp_type() {
            return rp_type;
        }

        public void setRp_type(String rp_type) {
            this.rp_type = rp_type;
        }

        public int getPrivilege() {
            return privilege;
        }

        public void setPrivilege(int privilege) {
            this.privilege = privilege;
        }

        public int getAlbum_audio_id() {
            return album_audio_id;
        }

        public void setAlbum_audio_id(int album_audio_id) {
            this.album_audio_id = album_audio_id;
        }

        public int getRp_publish() {
            return rp_publish;
        }

        public void setRp_publish(int rp_publish) {
            this.rp_publish = rp_publish;
        }

        public String getAlbum_id() {
            return album_id;
        }

        public void setAlbum_id(String album_id) {
            this.album_id = album_id;
        }

        public int getOwnercount() {
            return ownercount;
        }

        public void setOwnercount(int ownercount) {
            this.ownercount = ownercount;
        }

        public int getFold_type() {
            return fold_type;
        }

        public void setFold_type(int fold_type) {
            this.fold_type = fold_type;
        }

        public int getAudio_id() {
            return audio_id;
        }

        public void setAudio_id(int audio_id) {
            this.audio_id = audio_id;
        }

        public int getPkg_price_sq() {
            return pkg_price_sq;
        }

        public void setPkg_price_sq(int pkg_price_sq) {
            this.pkg_price_sq = pkg_price_sq;
        }

        public int get_$320filesize() {
            return _$320filesize;
        }

        public void set_$320filesize(int _$320filesize) {
            this._$320filesize = _$320filesize;
        }

        public int getIsnew() {
            return isnew;
        }

        public void setIsnew(int isnew) {
            this.isnew = isnew;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getPkg_price_320() {
            return pkg_price_320;
        }

        public void setPkg_price_320(int pkg_price_320) {
            this.pkg_price_320 = pkg_price_320;
        }

        public int getSrctype() {
            return srctype;
        }

        public void setSrctype(int srctype) {
            this.srctype = srctype;
        }

        public int getFail_process_sq() {
            return fail_process_sq;
        }

        public void setFail_process_sq(int fail_process_sq) {
            this.fail_process_sq = fail_process_sq;
        }

        public int getSqfilesize() {
            return sqfilesize;
        }

        public void setSqfilesize(int sqfilesize) {
            this.sqfilesize = sqfilesize;
        }

        public int getFail_process() {
            return fail_process;
        }

        public void setFail_process(int fail_process) {
            this.fail_process = fail_process;
        }

        public String get_$320hash() {
            return _$320hash;
        }

        public void set_$320hash(String _$320hash) {
            this._$320hash = _$320hash;
        }

        public String getExtname() {
            return extname;
        }

        public void setExtname(String extname) {
            this.extname = extname;
        }

        public String getSqhash() {
            return sqhash;
        }

        public void setSqhash(String sqhash) {
            this.sqhash = sqhash;
        }

        public int getPay_type_sq() {
            return pay_type_sq;
        }

        public void setPay_type_sq(int pay_type_sq) {
            this.pay_type_sq = pay_type_sq;
        }

        public int get_$320privilege() {
            return _$320privilege;
        }

        public void set_$320privilege(int _$320privilege) {
            this._$320privilege = _$320privilege;
        }

        public int getSqprivilege() {
            return sqprivilege;
        }

        public void setSqprivilege(int sqprivilege) {
            this.sqprivilege = sqprivilege;
        }

        public String getAlbum_name() {
            return album_name;
        }

        public void setAlbum_name(String album_name) {
            this.album_name = album_name;
        }

        public String getOthername() {
            return othername;
        }

        public void setOthername(String othername) {
            this.othername = othername;
        }

        public List<?> getGroup() {
            return group;
        }

        public void setGroup(List<?> group) {
            this.group = group;
        }

        public static class TransParamBean {
            /**
             * cid : -1
             * pay_block_tpl : 1
             * musicpack_advance : 0
             * display_rate : 0
             * display : 0
             * cpy_attr0 : 0
             */

            private int cid;
            private int pay_block_tpl;
            private int musicpack_advance;
            private int display_rate;
            private int display;
            private int cpy_attr0;

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public int getPay_block_tpl() {
                return pay_block_tpl;
            }

            public void setPay_block_tpl(int pay_block_tpl) {
                this.pay_block_tpl = pay_block_tpl;
            }

            public int getMusicpack_advance() {
                return musicpack_advance;
            }

            public void setMusicpack_advance(int musicpack_advance) {
                this.musicpack_advance = musicpack_advance;
            }

            public int getDisplay_rate() {
                return display_rate;
            }

            public void setDisplay_rate(int display_rate) {
                this.display_rate = display_rate;
            }

            public int getDisplay() {
                return display;
            }

            public void setDisplay(int display) {
                this.display = display;
            }

            public int getCpy_attr0() {
                return cpy_attr0;
            }

            public void setCpy_attr0(int cpy_attr0) {
                this.cpy_attr0 = cpy_attr0;
            }
        }
    }
}
