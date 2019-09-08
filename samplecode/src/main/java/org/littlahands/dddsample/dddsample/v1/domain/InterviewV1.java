package org.littlahands.dddsample.dddsample.v1.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class InterviewV1 {
  private String interviewId;
  private String screeningId;
  private LocalDate screeningDate;
  private int interviewNumber;
  private ScreeningStepResult screeningStepResult;
  private Long recruiterId;
}
