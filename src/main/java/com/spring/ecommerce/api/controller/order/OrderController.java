package com.spring.ecommerce.api.controller.order;

import com.spring.ecommerce.model.LocalUser;
import com.spring.ecommerce.model.Order;
import com.spring.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping public List<Order> getOrders(@AuthenticationPrincipal LocalUser user) {
        return orderService.getOrders(user);
    }
}
