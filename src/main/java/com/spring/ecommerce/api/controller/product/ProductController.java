package com.spring.ecommerce.api.controller.product;

import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping public List<Product> getProducts() {
        return productService.getProducts();
    }
}
