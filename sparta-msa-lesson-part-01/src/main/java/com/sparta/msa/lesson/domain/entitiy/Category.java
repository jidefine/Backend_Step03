package com.sparta.msa.lesson.domain.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "categories")
public class Category {

  @Getter
  @FieldDefaults(level = AccessLevel.PRIVATE)
  @Schema(name = "CategoryRequest")
  public static class Request {

    String name;
    Long parentId;
  }

  @Getter
  @FieldDefaults(level = AccessLevel.PRIVATE)
  public static class Response {

    Long id;
    String name;
  }

  @Getter
  @Builder
  @FieldDefaults(level = AccessLevel.PRIVATE)
  public static class parent {

    Long id;
    String name;
    List<Child> child;
    /*
     * {
     * "id" : 1,
     * "name" : "카테고리 이름",
     * "child" : [
     * {
     * "id" : 2,
     * "name" : "자식 케타고리"
     * }
     * ]
     * }
     * */
  }

  @Getter
  @Builder
  @FieldDefaults(level = AccessLevel.PRIVATE)
  public static class Child {

    Long id;
    String name;
  }

}

