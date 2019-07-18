package com.itheima.service;

import com.itheima.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IProductService {


    /**
     * 查找所有
     * @return
     */
    List<Product> finaAllProduct();

    /**
     * 添加一件商品
     * @param product
     */
    void save(Product product);

}
