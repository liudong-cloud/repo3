package com.itheima.test;


import com.itheima.domain.Product;
import com.itheima.service.impl.ProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestController {
    @Test
    public void test(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationcontext.xml");
        ProductService ps = ac.getBean(ProductService.class);
        List<Product> productList = ps.finaAllProduct();
        for (Product product : productList) {
            System.out.println("product = " + product);
        }
    }


}
