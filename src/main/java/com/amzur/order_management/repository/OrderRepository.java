package com.amzur.order_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amzur.order_management.dto.response.OrderResponse;
import com.amzur.order_management.entities.OrderEntity;





@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

	public List<OrderEntity> findByUserId(Long userId);
}
