package org.littlahands.dddsample.dddsample.v1.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class InterviewV1 {
  // 面接ID
  private String interviewId;
  // 選考日
  private LocalDate screeningDate;
  // 面接次数
  private int interviewNumber;
  // 面接結果
  private ScreeningStepResult screeningStepResult;
  // 採用担当者ID
  private Long recruiterId;

  InterviewV1(int interviewNumber,LocalDate screeningDate) {
    this.interviewId = UUID.randomUUID().toString();
    this.screeningDate = screeningDate;
    this.interviewNumber = interviewNumber;
  }
}
