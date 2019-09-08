package org.littlahands.dddsample.dddsample.v4.domain.screening;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 面談を表すクラス
 */
@Getter
public class PreInterviewV4 {
  /**
   * 面談日
   */
  private LocalDate preInterviewDate;

  public PreInterviewV4(LocalDate preInterviewDate) {
    this.preInterviewDate = preInterviewDate;
  }
}
