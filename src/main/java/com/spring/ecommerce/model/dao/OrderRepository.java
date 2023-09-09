package com.spring.ecommerce.model.dao;

import com.spring.ecommerce.model.LocalUser;
import com.spring.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(LocalUser user);
}
