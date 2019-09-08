package org.littlahands.dddsample.dddsample.v2.domain;

import lombok.Getter;
import org.littlahands.dddsample.dddsample.v1.domain.ApplicationException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Getter
public class ScreeningV2 {
  private String screeningId;
  private LocalDate applyDate;
  private ScreeningStatusV2 status;
  private String applicantEmailAddress;
  private List<InterviewV2> interviews;

  // factory methods

  /**
   * デフォルトコンストラクタはprivateにして
   * 下記2つのファクトリメソッドからしか呼べなくしている
   */
  private ScreeningV2() {
  }

  /**
   * 面談から採用選考を登録する際のファクトリメソッド
   */
  public static ScreeningV2 startFromPreInterview(String applicantEmailAddress) throws ApplicationException {
    if (applicantEmailAddress == null || applicantEmailAddress.length() == 0) {
      throw new ApplicationException("Invalid applicant name.");
    }
    ScreeningV2 screening = new ScreeningV2();
    screening.screeningId = UUID.randomUUID().toString();
    screening.interviews = Collections.emptyList();
    screening.applicantEmailAddress = applicantEmailAddress;

    // ① 初期ステータスは「未応募」
    screening.status = ScreeningStatusV2.NotApplied;
    // ② 応募日はブランク
    screening.applyDate = null;

    return screening;
  }

  /**
   * 面接から採用選考を登録する際のファクトリメソッド
   */
  public static ScreeningV2 apply(String applicantEmailAddress) throws ApplicationException {
    if (applicantEmailAddress == null || applicantEmailAddress.length() == 0) {
      throw new ApplicationException("Invalid applicant name.");
    }
    ScreeningV2 screening = new ScreeningV2();
    screening.applicantEmailAddress = applicantEmailAddress;
    screening.screeningId = UUID.randomUUID().toString();
    screening.interviews = Collections.emptyList();

    // ③ 初期ステータスは「選考中」
    screening.status = ScreeningStatusV2.Interview;

    // ④ 応募日は登録日
    screening.applyDate = LocalDate.now();

    return screening;
  }

  // mutator methods

  public void addNextInterview(LocalDate interviewDate)
      throws ApplicationException {
    // ④ 選考ステータスが「選考中」以外のときには設定できない
    if (this.status != ScreeningStatusV2.Interview) {
      throw new ApplicationException("Invalid operation");
    }

    // ⑤ 面接次数は1からインクリメントされる
    int nextInterviewNumber = this.interviews.size() + 1;
    InterviewV2 nextInterview =
        new InterviewV2(interviewDate, nextInterviewNumber);

    this.interviews.add(nextInterview);
  }


}
