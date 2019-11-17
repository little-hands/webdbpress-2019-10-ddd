package org.littlahands.dddsample.dddsample.v1.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.littlahands.dddsample.dddsample.shared.ApplicationException;

import java.time.LocalDate;
import java.util.UUID;

@Setter(AccessLevel.PRIVATE)
@Getter
public class ScreeningV1 {
  // 採用選考ID
  private ScreeningId screeningId;
  // 応募日
  private LocalDate applyDate;
  // 採用選考ステータス
  private ScreeningStatusV1 status;
  // 応募者メールアドレス
  private EmailAddress applicantEmailAddress;

  private Interviews interviews;

  private ScreeningV1() {
    // IDはUUIDを使用
    screeningId = new ScreeningId();
    interviews = new Interviews();
  }

  public static ScreeningV1 startFromPreInterview(EmailAddress applicantEmailAddress) {
    ScreeningV1 screening = new ScreeningV1();
    // 面談からの場合はステータス「未応募」で登録
    screening.setStatus(ScreeningStatusV1.NotApplied);
    // 未応募なので応募日はnull
    screening.setApplyDate(null);
    // メールアドレスは引数のものを登録
    screening.setApplicantEmailAddress(applicantEmailAddress);
    return screening;
  }

  public static ScreeningV1 apply(EmailAddress applicantEmailAddress) {
    ScreeningV1 screening = new ScreeningV1();
    // 面接からの場合はステータス「面接」で登録
    screening.setStatus(ScreeningStatusV1.Interview);
    // 応募日は操作日付を使用
    screening.setApplyDate(LocalDate.now());
    screening.setApplicantEmailAddress(applicantEmailAddress);
    return screening;
  }


  public void addNextInterview(LocalDate interviewDate) throws ApplicationException {
    // 面接設定をしてよいステータスかをチェック
    if (!this.status.canAddInterview()) {
      throw new ApplicationException("不正な操作です");
    }
    interviews.addNextInterview(interviewDate);
  }

  // private methods


  public Interviews getInterviews() {
    return this.interviews;
  }
}
