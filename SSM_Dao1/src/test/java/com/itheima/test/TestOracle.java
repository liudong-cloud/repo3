package com.itheima.test;

import com.itheima.dao.IProductDao;
import com.itheima.domain.Product;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestOracle {

    @Test
    public void testFindAll(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationcontext-dao1.xml");
        IProductDao productDao =  ac.getBean(IProductDao.class);
        List<Product> products = productDao.finaAllProduct();
        for (Product product : products) {
            System.out.println(product.getCityName());
        }

    }
}
