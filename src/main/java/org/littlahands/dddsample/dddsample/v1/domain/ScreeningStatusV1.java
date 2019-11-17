package org.littlahands.dddsample.dddsample.v1.domain;


public enum ScreeningStatusV1 {
  /**
   * 未応募
   */
  NotApplied,
  /**
   * 面接選考中
   */
  Interview,
  /**
   * 不合格
   */
  Refected,
  /**
   * 合格
   */
  Passed;

  public boolean canAddInterview() {
    return this == ScreeningStatusV1.Interview;
  }
}