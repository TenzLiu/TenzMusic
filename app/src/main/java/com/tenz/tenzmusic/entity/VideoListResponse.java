package com.tenz.tenzmusic.entity;

import java.util.List;

public class VideoListResponse {


    /**
     * timestamp : 1610587119
     * info : [{"userdesc":"","hd_bitrate":2131417,"fhd_hash":"AA2F0BBFC2F3DC1B84570D745E535FA2","sd_filesize":20815663,"qhd_filesize":37119505,"authors":[{"singerid":5086,"singername":"刘若英","singeravatar":"http://singerimg.kugou.com/uploadpic/softhead/{size}/20210112/20210112103951346.jpg"}],"sd_bitrate":636901,"fhd_filesize":134698466,"comment":8,"ld_bitrate":511280,"description":"以歌者身份回望自己的黄金年代","playcount":14904,"singername":"刘若英","hd_hash":"883A07BB85596248D5DCA68349B82B4A","publish":"2021-01-11 21:00:00","title":"刘若英 - 黄金年代","is_short":0,"collect_count":13,"download_count":171,"fhd_bitrate":4121393,"is_ugc":0,"mvhash":"a76e1f4ce99e53b01bc795bd10299773","album_cover":"http://imge.kugou.com/stdmusic/{size}/20210111/20210111163901233050.jpg","album_audio_id":"289635854","tags":[{"tag_id":"42042","parent_id":"4","tag_name":"流行音乐"},{"tag_id":"13","parent_id":"2","tag_name":"华语精选"},{"tag_id":"9","parent_id":"2","tag_name":"新歌推荐"},{"tag_id":"143","parent_id":"142","tag_name":"国语"},{"tag_id":"41","parent_id":"35","tag_name":"港台"},{"tag_id":"213","parent_id":"35","tag_name":"华语"},{"tag_id":"18","parent_id":"3","tag_name":"官方版"},{"tag_id":"61","parent_id":"58","tag_name":"女艺人"},{"tag_id":"1613","parent_id":"42042","tag_name":"华语流行"},{"tag_id":"1614","parent_id":"42042","tag_name":"国语流行"},{"tag_id":"374","parent_id":"42042","tag_name":"流行"}],"hd_filesize":69660556,"duration":261361,"videoname":"黄金年代","videoid":3748394,"remark":"","ld_filesize":16710028,"sd_hash":"E1C019EBED3D3F825A41EE49648E1EE7","qhd_bitrate":1135752,"img":"http://imge.kugou.com/mvhdpic/{size}/20210112/20210112093452141195.jpg","ld_hash":"5344A5FCEB423860C6F4A5A6AC97A34B","video_scale":{"height":270,"width":480},"useravatar":"","username":"","userid":0,"qhd_hash":"FE94D8DC905429A243F9DFF393B288F9"},{"userdesc":"","hd_bitrate":2134796,"fhd_hash":"2DF89B74984885E322D1DC0EFBE4E8C8","sd_filesize":19889349,"qhd_filesize":35592332,"authors":[{"singerid":5994,"singername":"谭维维","singeravatar":"http://singerimg.kugou.com/uploadpic/softhead/{size}/20201110/20201110103321104.jpg"}],"sd_bitrate":633822,"fhd_filesize":129663788,"comment":14,"ld_bitrate":509461,"description":"《上阳赋》同名片头曲MV上线！","playcount":20087,"singername":"谭维维","qhd_hash":"3D7B7B095F3A3F1B7D775856A36D5408","publish":"2021-01-11 15:00:00","title":"谭维维 - 上阳赋","is_short":0,"collect_count":64,"download_count":422,"sd_hash":"F08106C0FF155A2E25DB2443B1F957B4","is_ugc":0,"mvhash":"30ac34e14bbbd94f35ac3b51fab942ec","qhd_bitrate":1134236,"album_audio_id":"289383865","tags":[{"tag_id":"443","parent_id":"122","tag_name":"国产剧"},{"tag_id":"41278","parent_id":"122","tag_name":"古装"},{"tag_id":"444","parent_id":"122","tag_name":"电视剧"},{"tag_id":"13","parent_id":"2","tag_name":"华语精选"},{"tag_id":"9","parent_id":"2","tag_name":"新歌推荐"},{"tag_id":"143","parent_id":"142","tag_name":"国语"},{"tag_id":"213","parent_id":"35","tag_name":"华语"},{"tag_id":"40","parent_id":"35","tag_name":"内地"},{"tag_id":"18","parent_id":"3","tag_name":"官方版"},{"tag_id":"61","parent_id":"58","tag_name":"女艺人"}],"hd_filesize":66989916,"duration":250880,"videoname":"上阳赋","videoid":3745799,"remark":"《 上阳赋》电视剧片头曲","fhd_bitrate":4132052,"hd_hash":"68E1C7A318A76FF8C7336FC4FBD935E9","useravatar":"","ld_filesize":15986878,"ld_hash":"A47E0EF610AB8B1FA1484876E1BA0C1F","video_scale":{"height":270,"width":480},"img":"http://imge.kugou.com/mvhdpic/{size}/20210111/20210111172613314728.jpg","username":"","userid":0,"album_cover":"http://imge.kugou.com/stdmusic/{size}/20210108/20210108201405524396.jpg"}]
     * total : 878036
     */

    private int timestamp;
    private int total;
    private List<VideoBean> info;

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

    public List<VideoBean> getInfo() {
        return info;
    }

    public void setInfo(List<VideoBean> info) {
        this.info = info;
    }

    public static class InfoBean {

    }
}
