package com.bw.movie.bean;

import java.util.List;

/**
 * date:2019/1/10
 * author:赵豪轩(xuan)
 * function:
 */
public class DetailPingLunBean {


    /**
     * result : [{"commentContent":"11","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-01-09/20190109195310.jpg","commentId":1723,"commentTime":1547090212000,"commentUserId":3760,"commentUserName":"小丑八怪","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"111111","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-01-09/20190109195310.jpg","commentId":1721,"commentTime":1547089956000,"commentUserId":3760,"commentUserName":"小丑八怪","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":1},{"commentContent":"略略略略略略","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-01-10/20190110135453.thumb.700_0.gif","commentId":1720,"commentTime":1547089524000,"commentUserId":3751,"commentUserName":"全村人的希望","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"ffff","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-01-09/20190109192100.jpg","commentId":1719,"commentTime":1547087713000,"commentUserId":3754,"commentUserName":"小雅雅","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"的方法","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-01-10/20190110105539.12-34-53-u","commentId":1718,"commentTime":1547087667000,"commentUserId":3756,"commentUserName":"开心","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"刚刚v","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-01-10/20190110105539.12-34-53-u","commentId":1717,"commentTime":1547087604000,"commentUserId":3756,"commentUserName":"开心","greatNum":2,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"刚刚v","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-01-10/20190110105539.12-34-53-u","commentId":1716,"commentTime":1547087595000,"commentUserId":3756,"commentUserName":"开心","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"jjj","commentHeadPic":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eo791keia7hibbmYHyTDPS8260X7iaiaAJv2Lu8cDL0MEIT4SV1sHtKBQYOf7Wt4LCvBCicFO0urmIjxOQ/132","commentId":1715,"commentTime":1547085984000,"commentUserId":3922,"commentUserName":"初璃兮微￡_vlm","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"搜索","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-01-09/20190109132837.jpg","commentId":1703,"commentTime":1547039651000,"commentUserId":3767,"commentUserName":"枫叶","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":1697,"commentTime":1547034754000,"commentUserId":3825,"commentUserName":"NorthHarbor","greatNum":1,"hotComment":0,"isGreat":0,"replyNum":0}]
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
         * commentContent : 11
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/2019-01-09/20190109195310.jpg
         * commentId : 1723
         * commentTime : 1547090212000
         * commentUserId : 3760
         * commentUserName : 小丑八怪
         * greatNum : 0
         * hotComment : 0
         * isGreat : 0
         * replyNum : 0
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int hotComment;
        private int isGreat;
        private int replyNum;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getHotComment() {
            return hotComment;
        }

        public void setHotComment(int hotComment) {
            this.hotComment = hotComment;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }
    }
}
