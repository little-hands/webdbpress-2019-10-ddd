package org.littlahands.dddsample.dddsample.v1.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ScreeningV1 {
  // 採用選考ID
  private String screeningId;
  // 応募日
  private LocalDate applyDate;
  // 採用選考ステータス
  private ScreeningStatusV1 status;
  // 応募者メールアドレス
  private String applicantEmailAddress;
}
