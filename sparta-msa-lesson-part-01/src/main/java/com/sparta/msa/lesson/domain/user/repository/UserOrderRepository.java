package com.sparta.msa.lesson.domain.user.repository;

import com.sparta.msa.lesson.domain.user.entity.UserOrder;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

  List<UserOrder> findAllByUser_Id(Long userId);

}
