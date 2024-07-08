package com.amzur.order_management.service;

import java.util.List;

import com.amzur.order_management.dto.request.OrderRequest;
import com.amzur.order_management.dto.response.OrderResponse;

public interface OrderService {
	
	public OrderResponse save(OrderRequest orderRequest);
   public OrderResponse getById(Long Id);

}
