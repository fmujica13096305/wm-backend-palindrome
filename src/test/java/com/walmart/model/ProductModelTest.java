package com.walmart.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.Application;
import com.walmart.dto.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(classes = Application.class)
public class ProductModelTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testGiven_Preconditions_When_StateUnderTest_Then_ExpectedBehavior2() throws JsonProcessingException {

        String json = "{\"_id\": {\"$oid\": \"5fb58313dfcd05cca3fab8bd\"}, \"id\": 1, \"brand\": \"ooy eqrceli\", \"description\": \"rlñlw brhrka\", \"image\": \"www.lider.cl/catalogo/images/whiteLineIcon.svg\", \"price\": 498724}";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Product readValue = mapper.readValue(json, Product.class);
        System.out.println("readValue = " + readValue.toString());
    }

    @Test
    public void searchProduct() {
        ProductModel m = new ProductModel();
        ProductRequest productRequest = new ProductRequest();
        productRequest.setId(1);
        productRequest.setDescription("hqhoy");
        System.out.println(m.searchProduct(productRequest).first());
        System.out.println(m.searchProduct(productRequest).first().toJson());
    }
}