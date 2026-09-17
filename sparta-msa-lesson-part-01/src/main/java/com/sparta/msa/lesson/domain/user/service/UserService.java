package com.sparta.msa.lesson.domain.user.service;

import com.sparta.msa.lesson.domain.order.entity.Order;
import com.sparta.msa.lesson.domain.order.repository.OrderRepository;
import com.sparta.msa.lesson.domain.user.entity.User;
import com.sparta.msa.lesson.domain.user.repository.UserOrderRepository;
import com.sparta.msa.lesson.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // final이 붙은 애들만 생성자를 만들어 줌
public class UserService {

//  User user = new User(1L, "홍길동", "email", "password");

  //  public void getUser() {
//    User user = User.builder()
//        .email("xxx@gmail.com")
//        .build();
//
//    String email = user.getEmail();
//
//  }

  private final UserRepository userRepository;
  private final OrderRepository orderRepository;
  private final UserOrderRepository userOrderRepository;

//  @Autowired
//  public UserService(UserRepository userRepository) {
//    this.userRepository = userRepository;
//  }

  public List<Order> getOrderByUserId(Long userId) {
//    User user = userRepository.findById(userId)
//        .orElseThrow(() -> new RuntimeException("유저 없음"));
//
//    return user.getOrders(); // 1안
//    return orderRepository.findAllByUser(user); //2안
    return userOrderRepository.findAllByUser_Id(userId)
        .stream().map((item) -> item.getOrder())
        .toList();
  }

  public List<User> getUserList() {
    return userRepository.findAll();
  }

  public User getUser(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("유저 없음")); // Exception 보단 유연함

    return user;
  }

  public User save(String name, String email, String password) {
//    User user = userRepository.findByEmail(email)
//        .orElseThrow(() -> new RuntimeException("유저 없음"));

    if (userRepository.findByEmail(email).isPresent()) {
      throw new RuntimeException(("이메일 중복"));
    }

    User user = User.builder()
        .name(name)
        .email(email)
        .password(password)
        .build();

    return userRepository.save(user);
  }

  public User updateName(Long id, String name) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("유저 없음"));

    user.setName(name);

    return userRepository.save(user);
  }

  public void delete(Long id) {
    // 검증 -> 존재 여부
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("유저 없음"));

//    효율 위주의 삭제 코드
//    if (userRepository.existsById(id)) {
//      userRepository.existsById(id)
//    }

    userRepository.delete(user);
  }

}
