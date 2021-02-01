package com.tenz.tenzmusic.entity;

import java.util.List;

public class SongListResponse {

    /**
     * timestamp : 1610500975
     * info : [{"pay_type_320":3,"m4afilesize":0,"price_sq":200,"first":1,"filesize":4086973,"bitrate":128,"trans_param":{"cpy_grade":5,"musicpack_advance":0,"cpy_level":1,"hash_offset":{"file_type":0,"start_byte":0,"start_ms":0,"offset_hash":"D93AD88EE0FAAA447DADE77045E3C679","end_ms":60000,"end_byte":961056},"pay_block_tpl":1,"cpy_attr0":0,"display_rate":0,"appid_block":"3124","display":0,"cid":129141438},"price":200,"inlist":1,"old_cpy":0,"pkg_price_sq":1,"pay_type":3,"musical":null,"topic_url":"","rp_type":"audio","pkg_price":1,"recommend_reason":"","filename":"刘若英 - 黄金年代","price_320":200,"topic_url_320":"","album_audio_id":289635854,"sqfilesize":22952247,"hash":"1CB58B03E930B9C9A45307361AE5EB39","mvhash":"16C853F3E7B9FA3E99E9B37E21DC725A","album_cover":"http://imge.kugou.com/stdmusic/{size}/20210111/20210111163901233050.jpg","privilege":8,"fail_process_320":4,"addtime":"2021-01-11 20:54:29","pkg_price_320":1,"duration":255,"feetype":0,"remark":"黄金年代","sqhash":"E15D70416ED9FDBA3B0633E453AF61EA","320filesize":10217181,"rp_publish":1,"cover":"http://imge.kugou.com/stdmusic/{size}/20210111/20210111163901233050.jpg","topic_url_sq":"","320hash":"E50F4E8E97348A2C39FFC82B6AE0221C","isfirst":0,"fail_process":4,"320privilege":10,"album_id":"41111907","has_accompany":0,"audio_id":91684514,"pay_type_sq":3,"extname":"mp3","sqprivilege":10,"fail_process_sq":4,"issue":16}]
     * total : 44
     */

    private int timestamp;
    private int total;
    private List<SongBean> info;

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

    public List<SongBean> getInfo() {
        return info;
    }

    public void setInfo(List<SongBean> info) {
        this.info = info;
    }

}
