package cn.acyou.iblogdata.commons;

import java.io.Serializable;

public class Sort implements Serializable {

    private static final long serialVersionUID = 7739709965769082011L;
    private static final String DEFAULT_PREFIX = "p";
    private static final String SQL_DOT = ".";
    private String orderField;
    private OrderType orderType = OrderType.ASC;
    private boolean enablePrefix = true;

    public Sort() {
    }

    public Sort(String orderField, OrderType orderType, boolean enablePrefix) {
        assert orderField != null;
        this.orderField = orderField;
        this.orderType = orderType;
        this.enablePrefix = enablePrefix;
    }


    public Sort(String orderField) {
        this(orderField, OrderType.ASC, true);
    }

    /**
     * 如：http://localhost:8033/boss/student?sorts=age-desc&currentPage=1&pageSize=3
     * @param sortString 排序字段
     * @return Sort
     */
    public static Sort valueOf(String sortString) {
        String[] sortParams = sortString.split("-");
        Sort result = null;
        if (sortParams.length > 0) {
            String orderField = sortParams[0];
            OrderType orderType = OrderType.ASC;
            boolean enablePrefix = true;
            if (sortParams.length > 1) {
                orderType = OrderType.valueOf(sortParams[1].toUpperCase());
            }
            if (sortParams.length > 2) {
                enablePrefix = Boolean.valueOf(sortParams[2]);
            }
            result = new Sort(orderField.replaceAll("[A-Z]", "_$0").toLowerCase(), orderType, enablePrefix);
        }
        return result;
    }

    public static Sort valueOf(String sortKey, OrderType orderType) {
        return new Sort(sortKey, orderType, true);
    }

    public static Sort valueOf(String sortKey, OrderType orderType, boolean enablePrefix) {
        return new Sort(sortKey, orderType, enablePrefix);
    }

    public String getOrderField() {
        if (orderField.contains(SQL_DOT) || !enablePrefix) {
            return orderField;
        } else {
            return DEFAULT_PREFIX + SQL_DOT + orderField;
        }
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public boolean isEnablePrefix() {
        return enablePrefix;
    }

    public void setEnablePrefix(boolean enablePrefix) {
        this.enablePrefix = enablePrefix;
    }

    @Override
    public String toString() {
        return getOrderField() + " " + orderType;
    }
}
