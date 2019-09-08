package org.littlahands.dddsample.dddsample.v3.domain.screening;

import lombok.Getter;
import org.littlahands.dddsample.dddsample.shared.ApplicationException;

import java.time.LocalDate;


@Getter
public class ScreeningV3 {

  // 各プロパティの型が新たに定義したクラスになっている
  private ScreeningId screeningId;
  private LocalDate applyDate;
  private ScreeningStatusV3 status;
  private EmailAddress applicantEmailAddress;
  private Interviews interviews;

  // 生成メソッド

  private ScreeningV3() {
  }

  public static ScreeningV3 startFromPreInterview(
      EmailAddress applicantEmailAddress) {
    ScreeningV3 screening = new ScreeningV3();

    // EmailAddressインスタンスは正しいものしか生成されないので、
    // Screeningクラスでのバリデーションは不要になっている
    screening.applicantEmailAddress = applicantEmailAddress;

    // ScreeningId,Interviewsの初期値判断は各オブジェクトに委譲している
    screening.screeningId = new ScreeningId();
    screening.interviews = new Interviews();

    screening.status = ScreeningStatusV3.NotApplied;
    screening.applyDate = null;
    return screening;
  }

  public static ScreeningV3 apply(EmailAddress applicantEmailAddress) {
    ScreeningV3 screening = new ScreeningV3();
    screening.applicantEmailAddress = applicantEmailAddress;
    screening.screeningId = new ScreeningId();
    screening.interviews = new Interviews();
    screening.status = ScreeningStatusV3.Interview;
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

  public void setStatus(ScreeningStatusV3 screeningStatus) {
    this.status = screeningStatus;
  }

  // 再構成用メソッド

  // ①
  static ScreeningV3 reconstruct(
      ScreeningId screeningId,
      LocalDate applyDate,
      ScreeningStatusV3 status,
      EmailAddress applicantEmailAddress,
      Interviews interviews) {
    // ②
    ScreeningV3 screening = new ScreeningV3();
    screening.screeningId = screeningId;
    screening.applyDate = applyDate;
    screening.status = status;
    screening.applicantEmailAddress = applicantEmailAddress;
    screening.interviews = interviews;
    return screening;
  }


}
