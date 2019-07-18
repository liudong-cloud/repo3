package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;

public interface IOrdersService {
    /**
     * 查询所有
     */
    List<Orders> findAll(int page ,int size );

    /**
     * 查询订单信息根据id
     */
    Orders findOneById(String id);
}
