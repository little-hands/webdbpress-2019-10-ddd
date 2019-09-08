package org.littlahands.dddsample.dddsample.v2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class InterviewV2 {
  private String interviewId;

  private LocalDate interviewDate;

  private int interviewNumber;

  private ScreeningStepResult screeningStepResult;

  InterviewV2(LocalDate interviewDate, int interviewNumber) {
    // 引数の値を設定
    this.interviewDate = interviewDate; //TODO: 未来日チェック入れる
    this.interviewNumber = interviewNumber;

    // このコンストラクタを通った場合には必ず指定される値
    this.interviewId = UUID.randomUUID().toString();
    this.screeningStepResult = ScreeningStepResult.NotEvaluated;
  }
}
