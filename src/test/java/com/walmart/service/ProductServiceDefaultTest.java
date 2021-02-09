package com.walmart.service;

import com.walmart.dto.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    public void search() {
    }

    @Test
    public void testGiven_Preconditions_When_StateUnderTest_Then_NotDiscount() {
        ProductServiceDefault productServiceDefault = new ProductServiceDefault();
        List<Product> searchResult = productServiceDefault.search("123");
        for (Product product : searchResult) {
            System.out.println(product.toString());
        }
        Product first = searchResult.get(0);
        assertThat("123 id search is not palindrome, therefore does not have Discount", first.getPrice() == first.getOnSalePrice(), is(true));
    }

    @Test
    public void testGiven_Preconditions_When_StateUnderTest_Then_Discount() {
        ProductServiceDefault productServiceDefault = new ProductServiceDefault();
        List<Product> searchResult = productServiceDefault.search("121");
        for (Product product : searchResult) {
            System.out.println(product.toString());
        }
        Product first = searchResult.get(0);
        assertThat("121 id search is palindrome, therefore has Discount", first.getPrice() > first.getOnSalePrice(), is(true));
    }


    @Test
    public void testGiven_Preconditions_When_StateUnderTest_Then_ExpectedBehavior2() {
        ProductServiceDefault productServiceDefault = new ProductServiceDefault();
        List<Product> searchResult = productServiceDefault.search("bmfwuq");
        for (Product product : searchResult) {
            System.out.println(product.toString());
        }
        assertThat("bmfwuq search Should Bring 11 articules ", searchResult.size(), is(11));

    }
}