package org.littlahands.dddsample.dddsample.v3.domain.screening;

public enum ScreeningStatusV3 {
  // 区分値のあとに書かれているboolean値は、canAddInterviewの値を示す
  // NotAppliedの場合はcanAddInterviewがfalse、
  // つまり面接追加不可ということを示す

  /** 未応募 */
  NotApplied(false),

  /** 書類選考 */
  DocumentScreening(false),
  /** 書類不合格 */
  DocumentScreeningRejected(false),
  /** 書類選考辞退 */
  DocumentScreeningDeclined(false),

  /** 面接選考中 */
  Interview(true),
  /** 面接不合格 */
  InterviewRejected(false),
  /** 面接辞退 */
  InterviewDeclined(false),

  /** 内定 */
  Offered(false),
  /** 内定辞退 */
  OfferDeclined(false),

  /** 入社済 */
  Entered(false);

  /** 面接追加可否 */
  private boolean canAddInterview;

  // コンストラクタ
  ScreeningStatusV3(boolean canAddInterview) {
    this.canAddInterview = canAddInterview;
  }

  /** 面接追加可否を返す */
  public boolean canAddInterview() {
    return canAddInterview;
  }
}