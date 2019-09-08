package org.littlahands.dddsample.dddsample.v4.domain.screening;

import lombok.Getter;
import org.littlahands.dddsample.dddsample.shared.ApplicationException;

import java.time.LocalDate;


@Getter
public class ScreeningV4 {

  // 各プロパティの型が新たに定義したクラスになっている
  private ScreeningId screeningId;
  private LocalDate applyDate;
  private ScreeningStatusV4 status;
  private EmailAddress applicantEmailAddress;
  private Interviews interviews;

  // 生成メソッド

  private ScreeningV4() {
  }

  public static ScreeningV4 startFromPreInterview(
      EmailAddress applicantEmailAddress) {
    ScreeningV4 screening = new ScreeningV4();

    // EmailAddressインスタンスは正しいものしか生成されないので、
    // Screeningクラスでのバリデーションは不要になっている
    screening.applicantEmailAddress = applicantEmailAddress;

    // ScreeningId,Interviewsの初期値判断は各オブジェクトに委譲している
    screening.screeningId = new ScreeningId();
    screening.interviews = new Interviews();

    screening.status = ScreeningStatusV4.NotApplied;
    screening.applyDate = null;
    return screening;
  }

  public static ScreeningV4 apply(EmailAddress applicantEmailAddress) {
    ScreeningV4 screening = new ScreeningV4();
    screening.applicantEmailAddress = applicantEmailAddress;
    screening.screeningId = new ScreeningId();
    screening.interviews = new Interviews();
    screening.status = ScreeningStatusV4.Interview;
    screening.applyDate = LocalDate.now();
    return screening;
  }

  // ミューテーションメソッド

  public void addNextInterview(LocalDate interviewDate)
      throws ApplicationException {
    // どのステータスだとinterviewを追加できるかの判断は、
    // ScreeningStatusに委譲している
    if (!this.status.canAddInterview()) {
      throw new ApplicationException("不正な操作です");
    }
    // インタビュー次数の判断はInterviewsクラスに委譲している
    this.interviews.addNextInterview(interviewDate);
  }

  public void stepToNext() throws ApplicationException {
    // 状態遷移の知識はScreeningStatus enumに委譲したので、
    // Screeningはその知識を持たない
    this.status = this.status.nextStep();
  }

  // 再構成用メソッド

  // ①
  static ScreeningV4 reconstruct(
      ScreeningId screeningId,
      LocalDate applyDate,
      ScreeningStatusV4 status,
      EmailAddress applicantEmailAddress,
      Interviews interviews) {
    // ②
    ScreeningV4 screening = new ScreeningV4();
    screening.screeningId = screeningId;
    screening.applyDate = applyDate;
    screening.status = status;
    screening.applicantEmailAddress = applicantEmailAddress;
    screening.interviews = interviews;
    return screening;
  }


}
