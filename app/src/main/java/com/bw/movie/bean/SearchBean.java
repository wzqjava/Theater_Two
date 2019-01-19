package com.bw.movie.bean;

import java.util.List;

/**
 * date:2019/1/16
 * author:李壮(大壮)
 * function:搜索查询Bean
 */
public class SearchBean {

    /**
     * result : [{"address":"北京市育知东路30号华联商厦4层","commentTotal":0,"distance":0,"followCinema":0,"id":20,"logo":"http://172.17.8.100/images/movie/logo/wmyc.jpg","name":"北京沃美影城（回龙观店）"},{"address":"海淀区复兴路69号五棵松卓展时代百货五层东侧","commentTotal":0,"distance":0,"followCinema":0,"id":13,"logo":"http://172.17.8.100/images/movie/logo/bjalclgj.jpg","name":"北京耀莱成龙国际影城（五棵松店）"},{"address":"北京市朝阳区三丰北里2号楼悠唐广场B1层","commentTotal":0,"distance":0,"followCinema":0,"id":8,"logo":"http://172.17.8.100/images/movie/logo/bn.jpg","name":"北京博纳影城朝阳门旗舰店"},{"address":"北京市朝阳区建国门外大街1号国贸商城区域三地下一层3B120","commentTotal":0,"distance":0,"followCinema":0,"id":7,"logo":"http://172.17.8.100/images/movie/logo/blg.jpg","name":"北京百丽宫影城"},{"address":"北京市崇文区崇文门外大街18号国瑞城首层、地下二层","commentTotal":0,"distance":0,"followCinema":0,"id":9,"logo":"http://172.17.8.100/images/movie/logo/blh.jpg","name":"北京百老汇影城国瑞购物中心店"},{"address":"朝阳区建国路93号万达广场三层","commentTotal":0,"distance":0,"followCinema":0,"id":6,"logo":"http://172.17.8.100/images/movie/logo/wd.jpg","name":"北京CBD万达广场店"}]
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
         * address : 北京市育知东路30号华联商厦4层
         * commentTotal : 0
         * distance : 0
         * followCinema : 0
         * id : 20
         * logo : http://172.17.8.100/images/movie/logo/wmyc.jpg
         * name : 北京沃美影城（回龙观店）
         */

        private String address;
        private int commentTotal;
        private int distance;
        private int followCinema;
        private int id;
        private String logo;
        private String name;

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
