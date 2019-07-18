package com.itheima.controller;

import com.itheima.domain.Product;
import com.itheima.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ControllerProduct {

    @Autowired
    private ProductService productService;
    @Secured("ROLE_ADMIN")
    @RequestMapping("/findAll.do")
    public String findAll(Model model){
        List<Product> productList = productService.finaAllProduct();
        model.addAttribute("productList",productList);
        return "product-list";
    }

    @RequestMapping("/save.do")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    //@PreAuthorize("authentication.principal.username='test1'")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll.do";
    }
}
