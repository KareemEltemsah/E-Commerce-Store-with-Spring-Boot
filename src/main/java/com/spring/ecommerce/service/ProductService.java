package com.spring.ecommerce.service;

import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.dao.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
}
