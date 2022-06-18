package com.schoolnews.manage.application.constant;

/**
 * 网址相关常量
 */
public final class AddressContants {


    private static String SERVER_ADDR_HOME = "";

    static {
        if (GlobalConfigContants.intEnviSwitch == 0) {
   //         SERVER_ADDR_HOME = "http://192.168.0.114:8086";//测试环境使用
            SERVER_ADDR_HOME = "http://192.168.0.5:8080";

        }
    }

    public static final String API_SERVER_LOGIN = SERVER_ADDR_HOME + "/user/login";
    public static final String API_SERVER_FEE_LIST = SERVER_ADDR_HOME + "/news/collectionList"; //获取收藏列表
    public static final String API_SERVER_COMMON_LIST = SERVER_ADDR_HOME + "/news/commentList";
    public static final String API_SERVER_ADD_COMMON_LIST = SERVER_ADDR_HOME + "/news/addComment";
    public static final String API_SERVER_COLLECT = SERVER_ADDR_HOME + "/news/addCollection";
    public static final String API_SERVER_CANCEL_COLLECT = SERVER_ADDR_HOME + "/news/deleteCollection";
    public static final String API_SERVER_LIKE = SERVER_ADDR_HOME + "/admin/file/upload";
    public static final String API_SERVER_NO_LIKE = SERVER_ADDR_HOME + "/admin/file/upload";
    public static final String API_SERVER_FEEDBACK = SERVER_ADDR_HOME + "/admin/user/feedback";

    public static final String API_SERVER_ZHAUNZHANG_LIST = SERVER_ADDR_HOME + "/news/list";
    public static final String API_SERVER_SEARCH = SERVER_ADDR_HOME + "/news/key";


}
