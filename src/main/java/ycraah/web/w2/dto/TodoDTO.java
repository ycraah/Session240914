package ycraah.web.w2.dto;

import lombok.*;

import java.time.LocalDate;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TodoDTO {
  //DTO(Data Transfter Object) 데이터를 묶어서 하나의 객체로 전달
  //VO는 불변성을 유지하기 위해 Getter만 이용, DTO VO가 모두 필요한가에 대해서는 논쟁이 존재, JPA에 필수적이라 해당 프로젝트에서는 구분해서 생성
  //ModelMapper를 통해 DTO => VO, VO => DTO 변환
  private Long tno;
  private String title;
  private LocalDate dueDate;
  private boolean finished;
}
