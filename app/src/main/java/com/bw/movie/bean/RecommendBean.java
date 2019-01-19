package com.bw.movie.bean;

import java.util.List;

/**
 * date:2019/1/5
 * author:李壮(大壮)
 * function:推荐影院Bean类
 */
public class RecommendBean {

    /**
     * result : [{"address":"东城区滨河路乙1号雍和航星园74-76号楼","commentTotal":177,"distance":0,"followCinema":2,"id":1,"logo":"http://172.17.8.100/images/movie/logo/qcgx.jpg","name":"青春光线电影院"},{"address":"西城区前门大街大栅栏街36号","commentTotal":20,"distance":0,"followCinema":2,"id":2,"logo":"http://172.17.8.100/images/movie/logo/dgl.jpg","name":"大观楼电影院"},{"address":"北京市西城区天桥南大街3号楼一层、二层（天桥艺术大厦南侧）","commentTotal":32,"distance":0,"followCinema":2,"id":3,"logo":"http://172.17.8.100/images/movie/logo/sd.jpg","name":"首都电影院"},{"address":"北京市海淀区远大路1号B座5层魔影国际影城","commentTotal":29,"distance":0,"followCinema":2,"id":4,"logo":"http://172.17.8.100/images/movie/logo/mygj.jpg","name":"魔影国际影城"},{"address":"朝阳区湖景东路11号新奥购物中心B1(东A口)","commentTotal":12,"distance":0,"followCinema":2,"id":5,"logo":"http://172.17.8.100/images/movie/logo/CGVxx.jpg","name":"CGV星星影城"},{"address":"朝阳区建国路93号万达广场三层","commentTotal":6,"distance":0,"followCinema":2,"id":6,"logo":"http://172.17.8.100/images/movie/logo/wd.jpg","name":"北京CBD万达广场店"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * address : 东城区滨河路乙1号雍和航星园74-76号楼
         * commentTotal : 177
         * distance : 0
         * followCinema : 2
         * id : 1
         * logo : http://172.17.8.100/images/movie/logo/qcgx.jpg
         * name : 青春光线电影院
         */

        private String address;
        private int commentTotal;
        private int distance;
        private int followCinema;
        private int id;
        private String logo;
        private String name;

        public final static int ATTENTION = 1;
        public final static int UNATTENTION = 2;
        public boolean isFollowCinema(){
            return followCinema == ATTENTION;
        }
        public void changeItenAttentionStatus(){
            if (isFollowCinema()){
                followCinema = UNATTENTION;
            }else {
                followCinema = ATTENTION;
            }
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCommentTotal() {
            return commentTotal;
        }

        public void setCommentTotal(int commentTotal) {
            this.commentTotal = commentTotal;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getFollowCinema() {
            return followCinema;
        }

        public void setFollowCinema(int followCinema) {
            this.followCinema = followCinema;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
