package com.ibrahima.myspringproj.controller;

import com.ibrahima.myspringproj.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    Logger logger= LoggerFactory.getLogger(ProductController.class);
    private static List<Product> products = new ArrayList<>();


    @GetMapping("/")
    public String hello(){
        return "Welcome to Spring boot";
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody final Product product){
        System.out.println(product);
        if(!products.contains(product)){
            products.add(product);
        }
        return product;
    }

    @GetMapping("/product")
    public List<Product> getAllProducts(){
        if(products.isEmpty()){
            products.add(new Product("1", "premier test", new BigDecimal(251.45)));
            products.add(new Product("2", "deuxième test", new BigDecimal(590.45)));
            products.add(new Product("3", "troisième test", new BigDecimal(750.45)));
        }

        return products;
    }

   @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable final String id){
        Product product = null;
        for (Product prd : products){
            if(id.equals(prd.getId())){
                product = prd;
            }
        }

        if(product != null){
            logger.info("Product found : {}", product);
            return product;
        }
        else {
            logger.error("Product not found with ID : {}", id);
            return new Product();
        }
   }
}
