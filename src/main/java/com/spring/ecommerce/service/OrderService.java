package com.spring.ecommerce.service;

import com.spring.ecommerce.model.LocalUser;
import com.spring.ecommerce.model.Order;
import com.spring.ecommerce.model.dao.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getOrders(LocalUser user) {
        return orderRepository.findByUser(user);
    }
}
