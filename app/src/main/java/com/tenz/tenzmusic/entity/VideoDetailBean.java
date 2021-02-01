package com.tenz.tenzmusic.entity;

import java.util.List;

public class VideoDetailBean {

    /**
     * mp3data : {"hash":"8a215a1942bb03b214f1b3fd91f9281d","filesize":2916176,"timelength":182,"bitrate":128}
     * songname : Rain On Me
     * id : 2623871
     * hash : A07861EF80C7F98F430C13475BE7CF5A
     * track : 3
     * error :
     * is_publish : 1
     * mvdata : {"sq":{"downurl":"http://fs.mv.web.kugou.com/202101191146/1631404a5e0fe7d291fea440ef3c74ad/G209/M0A/10/15/cYcBAF7I3AOAYZd6AwQJhpPPGpU516.mp4","bitrate":2148642,"backupdownurl":["http://fs.mv.web2.kugou.com/202101191146/2588750dd33ec917e4547491b8b93c23/G209/M0A/10/15/cYcBAF7I3AOAYZd6AwQJhpPPGpU516.mp4"],"hash":"c0dea1438c32ecd1b05c208ce88b42ba","timelength":188281,"filesize":50596230},"le":{"downurl":"http://fs.mv.web.kugou.com/202101191146/4fa8d9f7ff652aa54252f151bd80810e/G213/M07/0D/14/dYcBAF7I2-qAAmk_ALf3QuJX0nE073.mp4","bitrate":511992,"backupdownurl":["http://fs.mv.web2.kugou.com/202101191146/d9394c3fc5024ff8585247d620500ea9/G213/M07/0D/14/dYcBAF7I2-qAAmk_ALf3QuJX0nE073.mp4"],"hash":"a07861ef80c7f98f430c13475be7cf5a","timelength":188283,"filesize":12056386},"rq":{"downurl":"http://fs.mv.web.kugou.com/202101191146/fd2241cc39e23155ff414d85d9cd3e51/G210/M00/04/05/spQEAF7I3B-Af6XpBdYJLs9TKvY131.mp4","bitrate":4158023,"backupdownurl":["http://fs.mv.web2.kugou.com/202101191146/618c01fd8658ebac3610beb005508bda/G210/M00/04/05/spQEAF7I3B-Af6XpBdYJLs9TKvY131.mp4"],"hash":"bf261ae3e8dc5ebfe2bf9d4b366f67b0","timelength":188281,"filesize":97913134}}
     * comment_count : 272
     * timelength : 188281
     * status : 1
     * remark :
     * publish_date : 2020-05-23
     * collect_count : 1411
     * mvicon : http://imge.kugou.com/mvhdpic/{size}/20200523/20200523101843391844.jpg
     * errcode : 0
     * type : 2
     * like_count : 812
     * singer : Lady Gaga、Ariana Grande
     * play_count : 189757
     */

    private Mp3dataBean mp3data;
    private String songname;
    private int id;
    private String hash;
    private int track;
    private String error;
    private int is_publish;
    private MvdataBean mvdata;
    private int comment_count;
    private int timelength;
    private int status;
    private String remark;
    private String publish_date;
    private int collect_count;
    private String mvicon;
    private int errcode;
    private int type;
    private int like_count;
    private String singer;
    private int play_count;

    public Mp3dataBean getMp3data() {
        return mp3data;
    }

    public void setMp3data(Mp3dataBean mp3data) {
        this.mp3data = mp3data;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getIs_publish() {
        return is_publish;
    }

    public void setIs_publish(int is_publish) {
        this.is_publish = is_publish;
    }

    public MvdataBean getMvdata() {
        return mvdata;
    }

    public void setMvdata(MvdataBean mvdata) {
        this.mvdata = mvdata;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getTimelength() {
        return timelength;
    }

    public void setTimelength(int timelength) {
        this.timelength = timelength;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getMvicon() {
        return mvicon;
    }

    public void setMvicon(String mvicon) {
        this.mvicon = mvicon;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getPlay_count() {
        return play_count;
    }

    public void setPlay_count(int play_count) {
        this.play_count = play_count;
    }

    public static class Mp3dataBean {
        /**
         * hash : 8a215a1942bb03b214f1b3fd91f9281d
         * filesize : 2916176
         * timelength : 182
         * bitrate : 128
         */

        private String hash;
        private int filesize;
        private int timelength;
        private int bitrate;

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public int getFilesize() {
            return filesize;
        }

        public void setFilesize(int filesize) {
            this.filesize = filesize;
        }

        public int getTimelength() {
            return timelength;
        }

        public void setTimelength(int timelength) {
            this.timelength = timelength;
        }

        public int getBitrate() {
            return bitrate;
        }

        public void setBitrate(int bitrate) {
            this.bitrate = bitrate;
        }
    }

    public static class MvdataBean {
        /**
         * sq : {"downurl":"http://fs.mv.web.kugou.com/202101191146/1631404a5e0fe7d291fea440ef3c74ad/G209/M0A/10/15/cYcBAF7I3AOAYZd6AwQJhpPPGpU516.mp4","bitrate":2148642,"backupdownurl":["http://fs.mv.web2.kugou.com/202101191146/2588750dd33ec917e4547491b8b93c23/G209/M0A/10/15/cYcBAF7I3AOAYZd6AwQJhpPPGpU516.mp4"],"hash":"c0dea1438c32ecd1b05c208ce88b42ba","timelength":188281,"filesize":50596230}
         * le : {"downurl":"http://fs.mv.web.kugou.com/202101191146/4fa8d9f7ff652aa54252f151bd80810e/G213/M07/0D/14/dYcBAF7I2-qAAmk_ALf3QuJX0nE073.mp4","bitrate":511992,"backupdownurl":["http://fs.mv.web2.kugou.com/202101191146/d9394c3fc5024ff8585247d620500ea9/G213/M07/0D/14/dYcBAF7I2-qAAmk_ALf3QuJX0nE073.mp4"],"hash":"a07861ef80c7f98f430c13475be7cf5a","timelength":188283,"filesize":12056386}
         * rq : {"downurl":"http://fs.mv.web.kugou.com/202101191146/fd2241cc39e23155ff414d85d9cd3e51/G210/M00/04/05/spQEAF7I3B-Af6XpBdYJLs9TKvY131.mp4","bitrate":4158023,"backupdownurl":["http://fs.mv.web2.kugou.com/202101191146/618c01fd8658ebac3610beb005508bda/G210/M00/04/05/spQEAF7I3B-Af6XpBdYJLs9TKvY131.mp4"],"hash":"bf261ae3e8dc5ebfe2bf9d4b366f67b0","timelength":188281,"filesize":97913134}
         */

        private SqBean sq;
        private LeBean le;
        private RqBean rq;

        public SqBean getSq() {
            return sq;
        }

        public void setSq(SqBean sq) {
            this.sq = sq;
        }

        public LeBean getLe() {
            return le;
        }

        public void setLe(LeBean le) {
            this.le = le;
        }

        public RqBean getRq() {
            return rq;
        }

        public void setRq(RqBean rq) {
            this.rq = rq;
        }

        public static class SqBean {
            /**
             * downurl : http://fs.mv.web.kugou.com/202101191146/1631404a5e0fe7d291fea440ef3c74ad/G209/M0A/10/15/cYcBAF7I3AOAYZd6AwQJhpPPGpU516.mp4
             * bitrate : 2148642
             * backupdownurl : ["http://fs.mv.web2.kugou.com/202101191146/2588750dd33ec917e4547491b8b93c23/G209/M0A/10/15/cYcBAF7I3AOAYZd6AwQJhpPPGpU516.mp4"]
             * hash : c0dea1438c32ecd1b05c208ce88b42ba
             * timelength : 188281
             * filesize : 50596230
             */

            private String downurl;
            private int bitrate;
            private String hash;
            private int timelength;
            private int filesize;
            private List<String> backupdownurl;

            public String getDownurl() {
                return downurl;
            }

            public void setDownurl(String downurl) {
                this.downurl = downurl;
            }

            public int getBitrate() {
                return bitrate;
            }

            public void setBitrate(int bitrate) {
                this.bitrate = bitrate;
            }

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }

            public int getTimelength() {
                return timelength;
            }

            public void setTimelength(int timelength) {
                this.timelength = timelength;
            }

            public int getFilesize() {
                return filesize;
            }

            public void setFilesize(int filesize) {
                this.filesize = filesize;
            }

            public List<String> getBackupdownurl() {
                return backupdownurl;
            }

            public void setBackupdownurl(List<String> backupdownurl) {
                this.backupdownurl = backupdownurl;
            }
        }

        public static class LeBean {
            /**
             * downurl : http://fs.mv.web.kugou.com/202101191146/4fa8d9f7ff652aa54252f151bd80810e/G213/M07/0D/14/dYcBAF7I2-qAAmk_ALf3QuJX0nE073.mp4
             * bitrate : 511992
             * backupdownurl : ["http://fs.mv.web2.kugou.com/202101191146/d9394c3fc5024ff8585247d620500ea9/G213/M07/0D/14/dYcBAF7I2-qAAmk_ALf3QuJX0nE073.mp4"]
             * hash : a07861ef80c7f98f430c13475be7cf5a
             * timelength : 188283
             * filesize : 12056386
             */

            private String downurl;
            private int bitrate;
            private String hash;
            private int timelength;
            private int filesize;
            private List<String> backupdownurl;

            public String getDownurl() {
                return downurl;
            }

            public void setDownurl(String downurl) {
                this.downurl = downurl;
            }

            public int getBitrate() {
                return bitrate;
            }

            public void setBitrate(int bitrate) {
                this.bitrate = bitrate;
            }

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }

            public int getTimelength() {
                return timelength;
            }

            public void setTimelength(int timelength) {
                this.timelength = timelength;
            }

            public int getFilesize() {
                return filesize;
            }

            public void setFilesize(int filesize) {
                this.filesize = filesize;
            }

            public List<String> getBackupdownurl() {
                return backupdownurl;
            }

            public void setBackupdownurl(List<String> backupdownurl) {
                this.backupdownurl = backupdownurl;
            }
        }

        public static class RqBean {
            /**
             * downurl : http://fs.mv.web.kugou.com/202101191146/fd2241cc39e23155ff414d85d9cd3e51/G210/M00/04/05/spQEAF7I3B-Af6XpBdYJLs9TKvY131.mp4
             * bitrate : 4158023
             * backupdownurl : ["http://fs.mv.web2.kugou.com/202101191146/618c01fd8658ebac3610beb005508bda/G210/M00/04/05/spQEAF7I3B-Af6XpBdYJLs9TKvY131.mp4"]
             * hash : bf261ae3e8dc5ebfe2bf9d4b366f67b0
             * timelength : 188281
             * filesize : 97913134
             */

            private String downurl;
            private int bitrate;
            private String hash;
            private int timelength;
            private int filesize;
            private List<String> backupdownurl;

            public String getDownurl() {
                return downurl;
            }

            public void setDownurl(String downurl) {
                this.downurl = downurl;
            }

            public int getBitrate() {
                return bitrate;
            }

            public void setBitrate(int bitrate) {
                this.bitrate = bitrate;
            }

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }

            public int getTimelength() {
                return timelength;
            }

            public void setTimelength(int timelength) {
                this.timelength = timelength;
            }

            public int getFilesize() {
                return filesize;
            }

            public void setFilesize(int filesize) {
                this.filesize = filesize;
            }

            public List<String> getBackupdownurl() {
                return backupdownurl;
            }

            public void setBackupdownurl(List<String> backupdownurl) {
                this.backupdownurl = backupdownurl;
            }
        }
    }
}
