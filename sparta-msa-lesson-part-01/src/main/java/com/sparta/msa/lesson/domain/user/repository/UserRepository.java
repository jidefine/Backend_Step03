package com.sparta.msa.lesson.domain.user.repository;

import com.sparta.msa.lesson.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository // Repository 역할 지정
public interface UserRepository extends JpaRepository<User, Long> {

  // User 테이블 전용 SQL
  // Repository에서는 Optional로 던져야 함?

  //  JPQL(@Query)은 entity 기반이고, 네이밍 컨벤션으로 해결이 안될 경우에만 사용하는 것을 추천
  @Query("SELECT u FROM User u WHERE u.email = : email")
  Optional<User> findByEmail(String email); // query를 만들어주는 게 아니라 구현 메소드(query가 담겨있음)를 만들어 줌

  boolean existsById(Long id);

}
