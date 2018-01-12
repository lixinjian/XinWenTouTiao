package com.xinjian.bean.news;

import java.util.List;

/**
 * Created by Meiji on 2017/5/18.
 */

public class KeJiBean {

    public String message;
    public List<Data> data;
    public int total_number;
    public boolean has_more;
    public int login_status;
    public int show_et_status;
    public String post_content_hint;
    public boolean has_more_to_refresh;
    public int action_to_last_stick;
    //    public   sub_entrance_list;
    public int feed_flag;
    public Tip tips;


    public class Data {
        public String content;
        public String code;
    }

    public class Tip {
        public String type;
        public int display_duration;
        public String display_info;
        public String display_template;
        public String open_url;
        public String web_url;
        public String download_url;
        public String app_name;
        public String package_name;
    }

    public class Content{
//        public String abstract;
    }

}
