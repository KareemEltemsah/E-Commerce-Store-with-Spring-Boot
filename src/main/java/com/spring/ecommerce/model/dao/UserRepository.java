package com.spring.ecommerce.model.dao;

import com.spring.ecommerce.model.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<LocalUser, Long> {
}
