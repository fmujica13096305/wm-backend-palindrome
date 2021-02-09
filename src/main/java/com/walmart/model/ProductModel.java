package com.walmart.model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.walmart.client.MongoClientConfiguration;
import com.walmart.utils.Utils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductModel {

    private static Document queryBuilder(ProductRequest productRequest) {
        List<Bson> conditions = new ArrayList<>();
        checkAndAddEqualsCondition(conditions, productRequest.getId() > 0, "id", productRequest.getId());
        checkAndAddContainsCondition(conditions, Utils.exists(productRequest.getBrand()), "brand", productRequest.getBrand());
        checkAndAddContainsCondition(conditions, Utils.exists(productRequest.getDescription()), "description", productRequest.getDescription());
        return new Document("$or", conditions);
    }

    private static void checkAndAddEqualsCondition(List<Bson> conditions, boolean shouldAddCondition, String attribute, Object value) {
        if (shouldAddCondition) {
            conditions.add(buildEqualsCondition(attribute, value));
        }
    }

    private static void checkAndAddContainsCondition(List<Bson> conditions, boolean shouldAddCondition, String attribute, String value) {
        if (shouldAddCondition) {
            conditions.add(buildContainsCondition(attribute, value));
        }
    }

    private static Document buildEqualsCondition(String attribute, Object value) {
        return new Document(attribute, value);
    }

    private static Bson buildContainsCondition(String attribute, String value) {
        return Filters.regex(attribute, value, "i");
    }

    public FindIterable<Document> searchProduct(ProductRequest productRequest) {
        FindIterable<Document> searchResult;
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoClientConfiguration.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        MongoCollection<Document> productCollection = mongoOperation.getCollection("products");
        Document query = queryBuilder(productRequest);
        searchResult = productCollection.find(query);
        for (Document document : searchResult) {
            System.out.println("Doc: " + document.toJson());
        }
        return searchResult;
    }


}
