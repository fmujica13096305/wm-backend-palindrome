package com.walmart.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.client.FindIterable;
import com.walmart.constants.Constants;
import com.walmart.dto.Product;
import com.walmart.model.ProductModel;
import com.walmart.model.ProductRequest;
import com.walmart.utils.Utils;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(Constants.DEFAULT_IMPLEMENTATION)
public class ProductServiceDefault implements ProductService {

    @Override
    public List<Product> search(String query) {

        ProductModel productModel = new ProductModel();
        ProductRequest productRequest = generateSearchQuery(query);
        FindIterable<Document> documents = productModel.searchProduct(productRequest);
        List<Product> productList = validatePrice(toProductList(documents), query);
        return productList;
    }

    protected List<Product> validatePrice(List<Product> unCheckedProductList, String query) {
        List<Product> productListForSale = new ArrayList<>();
        if (Utils.isPalindrome(query)) {
            for (Product product : unCheckedProductList) {
                productListForSale.add(onSaleProduct(product));
            }
        } else {
            for (Product product : unCheckedProductList) {
                productListForSale.add(normalPriceProduct(product));
            }
        }
        return productListForSale;
    }

    private Product onSaleProduct(Product product) {
        product.setOnSalePrice(product.getPrice() * ((Constants.PERCENT_100 - Constants.DISCOUNT_PERCENTAGE) / Constants.PERCENT_100));
        return product;
    }

    private Product normalPriceProduct(Product product) {
        product.setOnSalePrice(product.getPrice());
        return product;
    }

    private List<Product> toProductList(FindIterable<Document> documents) {
        List<Product> productList = new ArrayList<>();
        for (Document document : documents) {
            try {
                productList.add(Utils.documentToProduct(document));
            } catch (JsonProcessingException e) {
                System.err.println("Error converting JSON" + e.getOriginalMessage());
            }
        }
        return productList;
    }

    protected ProductRequest generateSearchQuery(String query) {
        ProductRequest productRequest = new ProductRequest();
        if (Utils.isLong(query)) {
            productRequest.setId(Long.parseLong(query));
        }
        if (Utils.exists(query) && query.length() >= Constants.MIN_QUERY_CHARACTERS_SIZE) {
            productRequest.setDescription(query);
            productRequest.setBrand(query);
        }
        return productRequest;
    }

}
