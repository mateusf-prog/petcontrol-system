package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
