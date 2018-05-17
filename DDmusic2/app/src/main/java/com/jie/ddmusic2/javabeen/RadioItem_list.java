package com.jie.ddmusic2.javabeen;

import java.util.List;

/**
 * Created by jie on 2017/8/1.
 */

public class RadioItem_list {

    /**
     * error_code : 22000
     * result : {"channel":"漫步春天","channelid":null,"ch_name":"public_tuijian_spring","artistid":null,"avatar":null,"count":null,"songlist":[{"songid":"1337276","title":"在树上唱歌","artist":"郭静","thumb":"http://qukufile2.qianqian.com/data2/pic/88407378/88407378.jpg@s_0,w_90","method":0,"flow":0,"artist_id":"844","all_artist_id":"844","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,195,256,320,flac"},{"songid":"9680594","title":"Ice Age","artist":"Pete Yorn","thumb":"http://qukufile2.qianqian.com/data2/music/E9851DD2CE69EBD8C9B4819CC4E56646/252578651/252578651.jpg@s_0,w_90","method":0,"flow":0,"artist_id":"43452","all_artist_id":"43452","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320"},{"songid":"976984","title":"彩虹","artist":"羽泉","thumb":"http://qukufile2.qianqian.com/data2/pic/88555531/88555531.jpg@s_0,w_90","method":0,"flow":0,"artist_id":"786","all_artist_id":"786","resource_type":"0","havehigh":2,"charge":0,"all_rate":"64,128,256,320,flac"},{"songid":null,"title":null,"artist":null,"thumb":"","method":0,"flow":0,"artist_id":null,"all_artist_id":null,"resource_type":null,"havehigh":0,"charge":0,"all_rate":""}]}
     */

    private int error_code;
    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * channel : 漫步春天
         * channelid : null
         * ch_name : public_tuijian_spring
         * artistid : null
         * avatar : null
         * count : null
         * songlist : [{"songid":"1337276","title":"在树上唱歌","artist":"郭静","thumb":"http://qukufile2.qianqian.com/data2/pic/88407378/88407378.jpg@s_0,w_90","method":0,"flow":0,"artist_id":"844","all_artist_id":"844","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,195,256,320,flac"},{"songid":"9680594","title":"Ice Age","artist":"Pete Yorn","thumb":"http://qukufile2.qianqian.com/data2/music/E9851DD2CE69EBD8C9B4819CC4E56646/252578651/252578651.jpg@s_0,w_90","method":0,"flow":0,"artist_id":"43452","all_artist_id":"43452","resource_type":"0","havehigh":2,"charge":0,"all_rate":"24,64,128,192,256,320"},{"songid":"976984","title":"彩虹","artist":"羽泉","thumb":"http://qukufile2.qianqian.com/data2/pic/88555531/88555531.jpg@s_0,w_90","method":0,"flow":0,"artist_id":"786","all_artist_id":"786","resource_type":"0","havehigh":2,"charge":0,"all_rate":"64,128,256,320,flac"},{"songid":null,"title":null,"artist":null,"thumb":"","method":0,"flow":0,"artist_id":null,"all_artist_id":null,"resource_type":null,"havehigh":0,"charge":0,"all_rate":""}]
         */

        private String channel;
        private Object channelid;
        private String ch_name;
        private Object artistid;
        private Object avatar;
        private Object count;
        private List<SonglistBean> songlist;

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public Object getChannelid() {
            return channelid;
        }

        public void setChannelid(Object channelid) {
            this.channelid = channelid;
        }

        public String getCh_name() {
            return ch_name;
        }

        public void setCh_name(String ch_name) {
            this.ch_name = ch_name;
        }

        public Object getArtistid() {
            return artistid;
        }

        public void setArtistid(Object artistid) {
            this.artistid = artistid;
        }

        public Object getAvatar() {
            return avatar;
        }

        public void setAvatar(Object avatar) {
            this.avatar = avatar;
        }

        public Object getCount() {
            return count;
        }

        public void setCount(Object count) {
            this.count = count;
        }

        public List<SonglistBean> getSonglist() {
            return songlist;
        }

        public void setSonglist(List<SonglistBean> songlist) {
            this.songlist = songlist;
        }

        public static class SonglistBean {
            /**
             * songid : 1337276
             * title : 在树上唱歌
             * artist : 郭静
             * thumb : http://qukufile2.qianqian.com/data2/pic/88407378/88407378.jpg@s_0,w_90
             * method : 0
             * flow : 0
             * artist_id : 844
             * all_artist_id : 844
             * resource_type : 0
             * havehigh : 2
             * charge : 0
             * all_rate : 24,64,128,192,195,256,320,flac
             */

            private String songid;
            private String title;
            private String artist;
            private String thumb;
            private int method;
            private int flow;
            private String artist_id;
            private String all_artist_id;
            private String resource_type;
            private int havehigh;
            private int charge;
            private String all_rate;

            public String getSongid() {
                return songid;
            }

            public void setSongid(String songid) {
                this.songid = songid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getArtist() {
                return artist;
            }

            public void setArtist(String artist) {
                this.artist = artist;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public int getMethod() {
                return method;
            }

            public void setMethod(int method) {
                this.method = method;
            }

            public int getFlow() {
                return flow;
            }

            public void setFlow(int flow) {
                this.flow = flow;
            }

            public String getArtist_id() {
                return artist_id;
            }

            public void setArtist_id(String artist_id) {
                this.artist_id = artist_id;
            }

            public String getAll_artist_id() {
                return all_artist_id;
            }

            public void setAll_artist_id(String all_artist_id) {
                this.all_artist_id = all_artist_id;
            }

            public String getResource_type() {
                return resource_type;
            }

            public void setResource_type(String resource_type) {
                this.resource_type = resource_type;
            }

            public int getHavehigh() {
                return havehigh;
            }

            public void setHavehigh(int havehigh) {
                this.havehigh = havehigh;
            }

            public int getCharge() {
                return charge;
            }

            public void setCharge(int charge) {
                this.charge = charge;
            }

            public String getAll_rate() {
                return all_rate;
            }

            public void setAll_rate(String all_rate) {
                this.all_rate = all_rate;
            }
        }
    }
}
