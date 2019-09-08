package org.littlahands.dddsample.dddsample.v1.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 面談を表すクラス
 */
@Getter
@Setter
public class PreInterviewV1 {
  /**
   * 面談担当者ID
   */
  private Long interviewerId;

  /**
   * 面談日
   */
  private LocalDate preInterviewDate;

}
