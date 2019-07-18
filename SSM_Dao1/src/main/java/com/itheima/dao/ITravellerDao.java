package com.itheima.dao;

import com.itheima.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {

    @Select("select * " +
            "from traveller t ,order_traveller ot " +
            "where t.id = ot.travellerId and ot.orderId=#{orderId}")
    List<Traveller> findById(String orderId);
}
