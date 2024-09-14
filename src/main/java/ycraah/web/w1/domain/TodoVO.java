package ycraah.web.w1.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoVO {
  //DB 테이블 데이터를 자바 객체로 처리하기 위한 클래스, 읽기 전용으로 사용
  private Long tno;
  private String title;
  private LocalDate dueDate;
  private boolean finished;
}
