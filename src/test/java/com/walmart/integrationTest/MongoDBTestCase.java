package com.walmart.integrationTest;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.walmart.dto.Product;
import com.walmart.service.ProductServiceDefault;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testng.Assert;

import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
@ExtendWith(SpringExtension.class)
public class MongoDBTestCase {

    private EmbeddedMongo embeddedMongo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setUp() throws Exception {
        embeddedMongo = new EmbeddedMongo();
        embeddedMongo.start();
    }


    @DisplayName("given object to save"
            + " when save object using MongoDB template"
            + " then object is saved")


    @Test
    public void test(@Autowired MongoTemplate mongoTemplate) {
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("key", "value")
                .get();
        mongoTemplate.save(objectToSave, "collection");
        assertThat(mongoTemplate.findAll(DBObject.class, "collection")).extracting("key").containsOnly("value");
    }

    @Test
    public void should_write_in_mongo() {
        Assert.assertNotNull(mongoTemplate);
        Product product = new Product();
        product.setBrand("walmart");
        mongoTemplate.save(product);

        Product Productb = mongoTemplate.findAll(Product.class).get(0);
        Assert.assertEquals("walmart", Productb.getBrand());
    }

    @org.junit.Test
    public void givenMissingServiceType_whenPostSecureRequest_thenBadRequest() throws Exception {
        Product product = new Product();
        product.setBrand("brand");

        ProductServiceDefault p = new ProductServiceDefault();
        Product Productb = mongoTemplate.findAll(Product.class).get(0);
        p.search("FF");

    }


}
