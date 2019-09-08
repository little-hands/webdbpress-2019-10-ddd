package org.littlahands.dddsample.dddsample.v3.domain.screening;

import lombok.Getter;
import org.littlahands.dddsample.dddsample.v3.domain.ApplicationException;

import java.time.LocalDate;


@Getter
public class ScreeningV3 {

  // 各プロパティが新たに作成したクラスになっている
  private ScreeningIdV3 screeningId;
  private LocalDate applyDate;
  private ScreeningStatusV3 status;
  private EmailAddress applicantEmailAddress;
  private Interviews interviews;

  // factory methods

  private ScreeningV3() {
  }

  public static ScreeningV3 startFromPreInterview(EmailAddress applicantEmailAddress) {
    ScreeningV3 screening = new ScreeningV3();

    // メールアドレスインスタンスは正しいものしか生成されないので、
    // Screeningクラスでのバリデーションは不要になっている
    screening.applicantEmailAddress = applicantEmailAddress;

    // ScreeningId,Interviewsの初期値判断は各オブジェクトに移譲している
    screening.screeningId = new ScreeningIdV3();
    screening.interviews = new Interviews();

    screening.status = ScreeningStatusV3.NotApplied;
    screening.applyDate = null;
    return screening;
  }

  public static ScreeningV3 apply(EmailAddress applicantEmailAddress) {
    ScreeningV3 screening = new ScreeningV3();
    screening.applicantEmailAddress = applicantEmailAddress;
    screening.screeningId = new ScreeningIdV3();
    screening.interviews = new Interviews();
    screening.status = ScreeningStatusV3.Interview;
    screening.applyDate = LocalDate.now();
    return screening;
  }

  // mutator methods

  public void addNextInterview(LocalDate interviewDate) throws ApplicationException {
    // どのステータスだとinterviewを追加できるかの判断は、
    // ScreeningStatusに移譲する
    if (!this.status.canAddInterview()) {
      throw new ApplicationException("Invalid operation");
    }

    // インタビュー次数の判断はInterviewsクラスに移譲する
    this.interviews.addNextInterview(interviewDate);
  }

  // Javaのデフォルト可視性はパッケージプライベートなので、
  // 同じパッケージ外からは参照できない
  static ScreeningV3 reconstruct(ScreeningIdV3 screeningId,
                                 LocalDate applyDate,
                                 ScreeningStatusV3 status,
                                 EmailAddress applicantEmailAddress,
                                 Interviews interviews) {
    ScreeningV3 screening = new ScreeningV3();
    screening.screeningId = screeningId;
    screening.applyDate = applyDate;
    screening.status = status;
    screening.applicantEmailAddress = applicantEmailAddress;
    screening.interviews = interviews;
    return screening;
  }
}
