package com.itheima.dao;

import com.itheima.domain.Member;
import com.itheima.domain.Orders;
import com.itheima.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IOrdersDao {

    @Select("select * from orders")
    @Results(value = {
            @Result(id = true ,column = "id",property = "id"),
            @Result(column ="orderNum",property = "orderNum"),
            @Result(column ="orderTime",property = "orderTime"),
            @Result(column ="orderStatus",property = "orderStatus"),
            @Result(column ="peopleCount",property = "peopleCount"),
            @Result(column ="productId" ,property = "product" ,javaType =Product.class,
                    one = @One(select = "com.itheima.dao.IProductDao.findOneById"))
    })
    List<Orders> findAll();


    @Select("select * from orders where id=#{id}")
    @Results(value = {
            @Result(id = true ,column = "id",property = "id"),
            @Result(column ="orderNum",property = "orderNum"),
            @Result(column ="orderTime",property = "orderTime"),
            @Result(column ="orderStatus",property = "orderStatus"),
            @Result(column ="peopleCount",property = "peopleCount"),
            @Result(column ="productId" ,property = "product" ,javaType =Product.class,
                    one = @One(select = "com.itheima.dao.IProductDao.findOneById")),
            @Result(column = "memberId" ,property = "member",javaType = Member.class,
                    one = @One(select = "com.itheima.dao.IMemberDao.findOneById")),
            @Result(column = "id",property = "travellers",javaType =java.util.List.class,
                    many=@Many(select = "com.itheima.dao.ITravellerDao.findById"))
    })
    Orders findOneById(String id);

}
