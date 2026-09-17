package com.sparta.msa.lesson.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
import org.springframework.util.StringUtils;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@FieldDefaults(level = AccessLevel.PRIVATE) // 접근제한자 private 일괄 적용
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA는 기본 생성자를 필요
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id; // Primaty Key

  //  @Setter 검색 조건 없이 그냥 수정 가능하기 때문에 지양
  @Column(nullable = false, length = 50) // DB에서 NOT NULL
      String name;

  @Column(nullable = false, unique = true) // JPA @Column의 length 기본값은 255
  String email;

  @Column(nullable = false)
  String password;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt; // JPA가 DB에서 created_At이어도 알아서 인식함

  @UpdateTimestamp
  @Column
  LocalDateTime updatedAt; // 데이터 확인 시점에서 보면 과거 이력이라서 과거분사형으로 작성

//  실무에서 @OneToMany 잘 안 씀(쿼리 최적화 안됨, 추적이 어려움, 쌍방참조가 안됨)
//  @OneToMany(mappedBy = "user")
//  List<Order> orders = new ArrayList<>();


  @Builder // 빌더 패턴으로 객체를 생성
  public User(
      String name,
      String email,
      String password
  ) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public void setName(String name) { // setter 용도로 권장됨
    if (StringUtils.hasText(name)) {
      this.name = name;
    }
  }

}
