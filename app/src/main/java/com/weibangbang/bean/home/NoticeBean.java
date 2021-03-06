package com.weibangbang.bean.home;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/10/9 9:40
 * 功能描述：
 */
public class NoticeBean {


    /**
     * code : 1
     * msg :
     * data : [{"notice_id":3,"notice_title":"公告实验","notice_brief":"","notice_content":"<p>真的没问题<\/p><p><img src=\"/public/static/admin/ueditor/php/upload/image/20181014/1539517118155272.gif\" title=\"1539517118155272.gif\" alt=\"1.gif\"/><\/p>","notice_creattime":1539517119},{"notice_id":2,"notice_title":"马到成功","notice_brief":"","notice_content":"<p>中秋会员优惠<img src=\"/public/static/admin/ueditor/php/upload/image/20181014/1539516954539451.jpg\" title=\"1539516954539451.jpg\" alt=\"【高燃】游戏视频精彩集锦常用BGM_3405187513632803.jpg\"/><\/p>","notice_creattime":1537687868}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * notice_id : 3
         * notice_title : 公告实验
         * notice_brief :
         * notice_content : <p>真的没问题</p><p><img src="/public/static/admin/ueditor/php/upload/image/20181014/1539517118155272.gif" title="1539517118155272.gif" alt="1.gif"/></p>
         * notice_creattime : 1539517119
         */

        private int notice_id;
        private String notice_title;
        private String notice_brief;
        private String notice_content;
        private int notice_creattime;

        public int getNotice_id() {
            return notice_id;
        }

        public void setNotice_id(int notice_id) {
            this.notice_id = notice_id;
        }

        public String getNotice_title() {
            return notice_title;
        }

        public void setNotice_title(String notice_title) {
            this.notice_title = notice_title;
        }

        public String getNotice_brief() {
            return notice_brief;
        }

        public void setNotice_brief(String notice_brief) {
            this.notice_brief = notice_brief;
        }

        public String getNotice_content() {
            return notice_content;
        }

        public void setNotice_content(String notice_content) {
            this.notice_content = notice_content;
        }

        public int getNotice_creattime() {
            return notice_creattime;
        }

        public void setNotice_creattime(int notice_creattime) {
            this.notice_creattime = notice_creattime;
        }
    }
}
