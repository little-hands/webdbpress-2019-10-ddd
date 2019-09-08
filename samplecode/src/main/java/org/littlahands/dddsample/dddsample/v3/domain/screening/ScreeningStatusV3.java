package org.littlahands.dddsample.dddsample.v3.domain.screening;


// @formatter:off
public enum ScreeningStatusV3 {
  // どのステータスの時に面接を設定できるかはcanAddInterviewを見ればわかる
  /** 未応募 */
  NotApplied(false),
  /** 面接選考中 */
  Interview(true),
  /** 不合格 */
  Refected(false),
  /** 合格 */
  Passed(false);

  private boolean canAddInterview;

  ScreeningStatusV3(boolean canAddInterview) {
    this.canAddInterview = canAddInterview;
  }

  public boolean canAddInterview() {
    return canAddInterview;
  }
}
// @formatter:on