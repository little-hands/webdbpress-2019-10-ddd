package org.littlahands.dddsample.dddsample.v4.domain.screening;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class InterviewV4 {
  private LocalDate interviewDate;

  private int interviewNumber;

  private ScreeningStepResultV4 screeningStepResult;

  InterviewV4(LocalDate interviewDate, int interviewNumber) {
    // 引数の値を設定
    this.interviewDate = interviewDate;
    this.interviewNumber = interviewNumber;

    // このコンストラクタを通った場合には必ず指定される値
    this.screeningStepResult = ScreeningStepResultV4.NotEvaluated;
  }
}
