package com.sparta.msa.lesson.domain.order.entity;

import com.sparta.msa.lesson.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

  // user - 1: N - user_orders - 1: N - order <--RDMS N:N 표현하는 설계

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

//  @Column(name = "user_id")
//  Long userId;

  @ManyToOne(fetch = FetchType.LAZY) // 다른 테이블에서 가저온 컬럼
  @JoinColumn(name = "user_id", nullable = false) // order를 조회하면 user도 같이 조회함(join)
  User user;

  @Column(nullable = false)
  BigDecimal totalPrice;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  OrderStatus status;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;

  @Column(nullable = false)
  @UpdateTimestamp
  LocalDateTime updatedAt;

  @Builder
//  public Order(
//      Long userId,
//      BigDecimal totalPrice,
//      String status
//  ) {
//    this.userId = userId;
//    this.totalPrice = totalPrice;
//    this.status = status;
//  }
  public Order(
      User user,
      BigDecimal totalPrice,
      OrderStatus status
  ) {
    this.userId = user;
    this.totalPrice = totalPrice;
    this.status = status;
  }

}
