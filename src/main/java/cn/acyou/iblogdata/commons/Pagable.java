package cn.acyou.iblogdata.commons;

public interface Pagable {

    int getCurrentPage();

    void setCurrentPage(int currentPage);

    int getPageSize();

    void setPageSize(int pagesize);

    boolean isEnableCount();

    void setEnableCount(boolean enable);


}
