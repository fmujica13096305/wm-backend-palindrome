package com.walmart.service;

public interface ProductServiceFactory {
    ProductService get(String serviceTypeName);
}
