package com.walmart.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.Application;
import com.walmart.dto.Product;
import com.walmart.integrationTest.EmbeddedMongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(classes = Application.class)
public class ProductModelTest {
    private EmbeddedMongo embeddedMongo;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Before
    public void setUp() throws Exception {
        embeddedMongo = new EmbeddedMongo();
        embeddedMongo.start();
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testGivenExpectedBehavior() throws JsonProcessingException {

        String json = "{\"_id\": {\"$oid\": \"5fb58313dfcd05cca3fab8bd\"}, \"id\": 1, \"brand\": \"ooy eqrceli\", \"description\": \"rlñlw brhrka\", \"image\": \"www.lider.cl/catalogo/images/whiteLineIcon.svg\", \"price\": 498724}";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Product readValue = mapper.readValue(json, Product.class);
        System.out.println("readValue = " + readValue.toString());
    }


    @Test
    public void findAll() {
        Query query = new Query();
        List<Product> a = mongoTemplate.findAll(Product.class);
        System.out.println();
    }
}