package com.sparta.msa.lesson.domain.order.service;

import com.sparta.msa.lesson.domain.order.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  public void getOrder() {
    Order order = Order.builder().build(); // OrderRepository.findById(id)로 불러옴

    String userName = order.getUser().getName(); // 조인이 걸려서 user 정보를 불러옴

  }

}
