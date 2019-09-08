package org.littlahands.dddsample.dddsample.v3.domain.screening;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class InterviewV3 {
  private LocalDate interviewDate;

  private int interviewNumber;

  private ScreeningStepResult screeningStepResult;

  InterviewV3(LocalDate interviewDate, int interviewNumber) {
    // 引数の値を設定
    this.interviewDate = interviewDate;
    this.interviewNumber = interviewNumber;

    // このコンストラクタを通った場合には必ず指定される値
    this.screeningStepResult = ScreeningStepResult.NotEvaluated;
  }

  private InterviewV3() {
  }

  static InterviewV3 reconstruct(LocalDate interviewDate, int interviewNumber, ScreeningStepResult screeningStepResult) {
    InterviewV3 interview = new InterviewV3();
    interview.interviewDate = interviewDate;
    interview.interviewNumber = interviewNumber;
    interview.screeningStepResult = screeningStepResult;
    return interview;
  }
}
