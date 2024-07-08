package com.amzur.order_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amzur.order_management.dto.request.OrderRequest;
import com.amzur.order_management.dto.response.OrderResponse;
import com.amzur.order_management.service.OrderServiceImpl;
@RestController
@RequestMapping("/orders")
public class OrderController {
	 @Autowired
	    private OrderServiceImpl orderService;

	    // Create an order: POST http://localhost:8080/orders
	    @PostMapping
	    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
	    	//System.out.println("Book IDs:"+ orderRequest.getBookIds());
	        return orderService.save(orderRequest);
	    }
	    @GetMapping("/id/{id}")
	    public OrderResponse getById(@PathVariable Long Id) {
	    	return orderService.getById(Id);
	    }

}
