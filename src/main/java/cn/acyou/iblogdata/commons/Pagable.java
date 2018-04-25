package cn.acyou.iblogdata.commons;

public interface Pagable {

    public int getCurrentPage();

    public void setCurrentPage(int currentPage);

    public int getPageSize();

    public void setPageSize(int pagesize);

    public boolean isEnableCount();

    public void setEnableCount(boolean enable);


}
