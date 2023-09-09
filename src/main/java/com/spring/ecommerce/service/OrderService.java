package com.spring.ecommerce.service;

import com.spring.ecommerce.model.Order;
import com.spring.ecommerce.model.dao.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
}
