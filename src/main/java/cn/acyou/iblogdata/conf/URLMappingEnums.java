package cn.acyou.iblogdata.conf;

/**
 * 通过本类实现RequestMapping到视图的免配
 * @author youfang
 * @version [1.0.0, 2018-05-12]
 **/
public enum  URLMappingEnums {

    IND_0001("/","index/index", "首页"),
    WEB2APP_INDEX("/web2app/index","web2app/index", "web2app首页"),

    LOGIN_PAGE("/login","index/login", "登录页面"),
    WS_CLIENT("/client","ws/client", "Web Socket 客户端");


    private String path;
    private String page;
    private String desc;

    URLMappingEnums(String path, String page, String desc) {
        this.path = path;
        this.page = page;
        this.desc = desc;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
