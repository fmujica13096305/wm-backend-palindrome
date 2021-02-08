package com.walmart.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.client.FindIterable;
import com.walmart.constants.Constants;
import com.walmart.dto.Product;
import com.walmart.model.ProductModel;
import com.walmart.model.ProductQuery;
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
        ProductQuery productQuery = generateSearchQuery(query);
        FindIterable<Document> documents = productModel.searchProduct(productQuery);
        List<Product> productList = validatePrice(toProductList(documents), query);
        return productList;
    }

    private List<Product> validatePrice(List<Product> unCheckedProductList, String query) {
        List<Product> productListForSale = new ArrayList<>();
        if (Utils.isPalindrome(query)) {
            for (Product product : unCheckedProductList) {
                productListForSale.add(onSaleProduct(product));
            }
        } else {
            for (Product product : unCheckedProductList) {
                productListForSale.add(normalPrizeProduct(product));
            }
        }
        return productListForSale;
    }

    private Product onSaleProduct(Product product) {
        product.setOnSalePrice(product.getPrice() * ((Constants.PERCENT_100 - Constants.DISCOUNT_PERCENTAGE) / Constants.PERCENT_100));
        System.out.println(product.getOnSalePrice() + "/" + product.getPrice() * ((Constants.PERCENT_100 - Constants.DISCOUNT_PERCENTAGE) / Constants.PERCENT_100) + "/" + (Constants.PERCENT_100 - Constants.DISCOUNT_PERCENTAGE) + "/" + ((Constants.PERCENT_100 - Constants.DISCOUNT_PERCENTAGE) / Constants.PERCENT_100) + "/" + product.getPrice());
        return product;
    }

    private Product normalPrizeProduct(Product product) {
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

    private ProductQuery generateSearchQuery(String query) {
        ProductQuery productQuery = new ProductQuery();
        if (Utils.isLong(query)) {
            productQuery.setId(Long.parseLong(query));
        }
        if (Utils.exists(query) && query.length() >= Constants.MIN_QUERY_CHARACTERS_SIZE) {
            productQuery.setDescription(query);
            productQuery.setBrand(query);
        }
        return productQuery;
    }

}
