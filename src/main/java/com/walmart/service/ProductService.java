package com.walmart.service;

import com.walmart.dto.Product;

import java.util.List;

public interface ProductService {
    public List<Product> search(String query);
}
