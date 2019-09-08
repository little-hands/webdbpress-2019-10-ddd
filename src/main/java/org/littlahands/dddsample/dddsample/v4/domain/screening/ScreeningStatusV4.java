package org.littlahands.dddsample.dddsample.v4.domain.screening;


import org.littlahands.dddsample.dddsample.v4.domain.ApplicationException;

// @formatter:off
@SuppressWarnings("Duplicates")
public enum ScreeningStatusV4 {
  /** 未応募 */
  NotApplied,

  /** 書類選考 */
  DocumentScreening,
  /** 書類不合格 */
  DocumentScreeningRejected,
  /** 書類選考辞退 */
  DocumentScreeningDeclined,

  /** 面接選考中 */
  Interview,
  /** 面接不合格 */
  InterviewRejected,
  /** 面接辞退 */
  InterviewDeclined,

  /** 内定 */
  Offered,
  /** 内定辞退 */
  OfferDeclined,

  /** 入社済 */
  Entered;

  /**
   * 次のステップのステータスを取得します
   */
  public ScreeningStatusV4 nextStep() throws ApplicationException {
    switch (this) {
      case      NotApplied:         return DocumentScreening;
      case      DocumentScreening:  return Interview;
      case      Interview:          return Offered;
      case      Offered:            return Entered;
      default:                      throw new ApplicationException("許可されていない状態遷移です。");
    }
  }
  /**
   * 「戻る」した時のステータスを取得します
   */
  public ScreeningStatusV4 previousStep() throws ApplicationException {
    switch (this) {
      case      DocumentScreeningRejected:    return DocumentScreening;
      case      DocumentScreeningDeclined:    return DocumentScreening;
      case      Interview:                    return DocumentScreening;
      case      InterviewRejected:            return Interview;
      case      InterviewDeclined:            return Interview;
      case      Offered:                      return Interview;
      case      OfferDeclined:                return Offered;
      case      Entered:                      return Offered;
      default:                                throw new ApplicationException("許可されていない状態遷移です。");
    }
  }
  /**
   * 「不合格」した時のステータスを取得します
   */
  public ScreeningStatusV4 rejectStep() throws ApplicationException {
    switch (this) {
      case      DocumentScreening:   return DocumentScreeningRejected;
      case      Interview:           return InterviewRejected;
      default:                       throw new ApplicationException("許可されていない状態遷移です。");
    }
  }
  /**
   * 「辞退」した時のステータスを取得します
   */
  public ScreeningStatusV4 declineStep() throws ApplicationException {
    switch (this) {
      case      DocumentScreening:  return DocumentScreeningDeclined;
      case      Interview:          return InterviewDeclined;
      case      Offered:            return OfferDeclined;
      default:                      throw new ApplicationException("許可されていない状態遷移です。");
    }
  }

  public boolean canAddInterview() {
    return this == Interview;
  }
}
// @formatter:on