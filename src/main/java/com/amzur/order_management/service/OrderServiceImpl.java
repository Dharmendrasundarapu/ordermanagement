package com.amzur.order_management.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amzur.order_management.dto.request.OrderRequest;
import com.amzur.order_management.dto.response.OrderResponse;
import com.amzur.order_management.entities.LineItemEntity;
import com.amzur.order_management.entities.OrderEntity;
import com.amzur.order_management.repository.LineItemRepository;
import com.amzur.order_management.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
    private OrderRepository orderRepository;
    @Autowired
    private LineItemRepository lineItemRepository;

	@Override
	public OrderResponse save(OrderRequest orderRequest) {
		
		OrderEntity orderEntity = new OrderEntity();
		LineItemEntity lineItemEntity = new LineItemEntity();

		orderEntity.setUserId(orderRequest.getUserId());
		BeanUtils.copyProperties(orderRequest, orderEntity);
	//	BeanUtils.copyProperties(orderRequest, lineItemEntity);
		orderEntity = orderRepository.save(orderEntity);
		final Long orderId = orderEntity.getId();
		List<LineItemEntity> lineItems = orderRequest.getBookIds().stream()
		    .map(bookId -> {
		        LineItemEntity lineItem = new LineItemEntity();
		        lineItem.setOrderId(orderId);
		        lineItem.setBookId(bookId);
		        return lineItem;
		    })
		    .collect(Collectors.toList());

		lineItemRepository.saveAll(lineItems);
		OrderResponse orderResponse = new OrderResponse();
		BeanUtils.copyProperties(orderEntity, orderResponse);

		return orderResponse;
	}

	@Override
	public List<OrderResponse> getByUserId(Long userId) {
		return orderRepository.findByUserId(userId).stream().map(this::convertEntitytoResponse).collect(Collectors.toList());
		
	}
	public List<OrderResponse> getByOrderId(Long orderId){
		return lineItemRepository.findByOrderId(orderId).stream().map(this::convertEntitytoResponses).collect(Collectors.toList());
	}

	public OrderResponse convertEntitytoResponse(OrderEntity orderEntity) {
		OrderResponse orderResponse=new OrderResponse();
		BeanUtils.copyProperties(orderEntity, orderResponse);
		return orderResponse;
	}
	public OrderResponse convertEntitytoResponses(LineItemEntity lineItemEntity) {
		OrderResponse orderResponse=new OrderResponse();
		BeanUtils.copyProperties(lineItemEntity, orderResponse);
		return orderResponse;
	}
	
	

	

}
