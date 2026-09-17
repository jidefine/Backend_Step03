package com.sparta.msa.lesson.domain.order.repository;

import com.sparta.msa.lesson.domain.order.entity.Order;
import com.sparta.msa.lesson.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Repository 역할 지정
public interface OrderRepository extends JpaRepository<User, Long> {

//  List<Order> findAllByUser(User user);

  List<Order> findAllByUser_Id(Long userId);

}
