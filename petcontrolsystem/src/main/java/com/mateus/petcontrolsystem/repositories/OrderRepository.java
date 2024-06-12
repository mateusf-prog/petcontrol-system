package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
