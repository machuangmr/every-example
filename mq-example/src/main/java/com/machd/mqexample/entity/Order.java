package com.machd.mqexample.entity;

import java.io.Serializable;

/**
 * 请添加注释
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 3679574512236451254L;
    private Long id;

    private String userId;

    private String orderName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", orderName='" + orderName + '\'' +
                '}';
    }
}
