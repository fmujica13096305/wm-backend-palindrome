package com.walmart.service;

import com.walmart.dto.Product;
import com.walmart.model.ProductRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ProductServiceDefaultTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void preconditions_validate_price_not_found() {
        ProductServiceDefault productServiceDefault = new ProductServiceDefault();
        Product product = new Product();
        product.setId(1);
        List<Product> list = new ArrayList<>();
        list.add(product);
        String query = "181 ";
        List<Product> validatePriceList = productServiceDefault.validatePrice(list, query);
        for (Product price : validatePriceList) {
            System.out.println(product.toString());
        }
        Product first = validatePriceList.get(0);

        assertThat("price not found", first.getPrice() == first.getOnSalePrice(), is(true));
    }

    @Test
    public void preconditions_validate_palindrome() {
        ProductServiceDefault productServiceDefault = new ProductServiceDefault();
        Product product = new Product();
        product.setId(1);
        product.setPrice(1000);
        product.setBrand("mademsa");
        product.setDescription("refrigerator");
        List<Product> list = new ArrayList<>();
        list.add(product);
        String query = "141";
        List<Product> validatePriceList = productServiceDefault.validatePrice(list, query);
        for (Product price : validatePriceList) {
            System.out.println(product.toString());
        }
        Product first = validatePriceList.get(0);

        assertThat("is a palindrome", first.getPrice() != first.getOnSalePrice(), is(true));
    }


    @Test
    public void preconditions_validate_is_not_palindrome() {
        ProductServiceDefault productServiceDefault = new ProductServiceDefault();
        Product product = new Product();
        product.setId(1);
        product.setPrice(1000);
        product.setOnSalePrice(56000);
        product.setBrand("mademsa");
        product.setDescription("refrigerator");
        List<Product> list = new ArrayList<>();
        list.add(product);
        String query = "123 ";
        List<Product> validatePriceList = productServiceDefault.validatePrice(list, query);
        for (Product price : validatePriceList) {
            System.out.println(product.toString());
        }
        Product first = validatePriceList.get(0);

        assertThat("price not found", first.getPrice() == first.getOnSalePrice(), is(true));
    }

    @Test
    public void preconditions_validate_request_when_MIN_QUERY_CHARACTERS_SIZE_Minor_3() {
        ProductServiceDefault productServiceDefault = new ProductServiceDefault();
        String query = "12";
        ProductRequest productRequest = productServiceDefault.generateSearchQuery(query);
        System.out.println(productRequest.getBrand());

        Assert.assertNull(productRequest.getBrand());

    }

    @Test
    public void preconditions_validate_request_when_MIN_QUERY_CHARACTERS_SIZE_Mayor_3() {
        ProductServiceDefault productServiceDefault = new ProductServiceDefault();
        String query = "12345";
        ProductRequest productRequest = productServiceDefault.generateSearchQuery(query);
        System.out.println(productRequest.getBrand());

        Assert.assertEquals(productRequest.getBrand(), "12345");

    }

    @Test
    public void preconditions_When_StateUnderTest_Then_NotDiscount() {
        ProductServiceDefault productServiceDefault = new ProductServiceDefault();
        List<Product> searchResult = productServiceDefault.search("123");
        for (Product product : searchResult) {
            System.out.println(product.toString());
        }
        Product first = searchResult.get(0);
        assertThat("123 id search is not palindrome, therefore does not have Discount", first.getPrice() == first.getOnSalePrice(), is(true));
    }

    @Test
    public void preconditions_When_StateUnderTest_Then_Discount() {
        ProductServiceDefault productServiceDefault = new ProductServiceDefault();
        List<Product> searchResult = productServiceDefault.search("121");
        for (Product product : searchResult) {
            System.out.println(product.toString());
        }
        Product first = searchResult.get(0);
        assertThat("121 id search is palindrome, therefore has Discount", first.getPrice() > first.getOnSalePrice(), is(true));
    }


}