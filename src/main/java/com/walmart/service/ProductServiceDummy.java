package com.walmart.service;

import com.walmart.constants.Constants;
import com.walmart.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(Constants.DUMMY_IMPLEMENTATION)
public class ProductServiceDummy implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceDummy.class);

    @Override
    public List<Product> search(String query) {

        LOGGER.info("*****  IN  *****");
        System.out.println("*****  DUMMY ENTERED *****");
        List<Product> productList = new ArrayList<>();
        Product product = getProduct("https://www.lider.cl/catalogo/images/bedRoomIcon.svg", 1, "Dummy Brand1", "Dummy Product1", 1);
        Product product2 = getProduct("https://www.lider.cl/catalogo/images/toysIcon.svg", 2, "Dummy Brand2", "Dummy Product2", 2);
        Product product3 = getProduct("https://www.lider.cl/catalogo/images/toysIcon.svg", 3, "Dummy Brand3", "Dummy Product3", 3);
        productList.add(product);
        productList.add(product2);
        productList.add(product3);

        return productList;
    }

    private Product getProduct(String s, int id, String demo_brand, String demo_product, int price) {
        Product product = new Product();
        product.setId(id);
        product.setBrand(demo_brand);
        product.setDescription(demo_product);
        product.setImage(s);
        product.setPrice(price);
        return product;
    }
}
