package org.littlahands.dddsample.dddsample.v1.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class InterviewV1 {
  // 面接ID
  private String interviewId;
  // 採用選考ID
  private String screeningId;
  // 選考日
  private LocalDate screeningDate;
  // 面接次数
  private int interviewNumber;
  // 面接結果
  private ScreeningStepResult screeningStepResult;
  // 採用担当者ID
  private Long recruiterId;
}
