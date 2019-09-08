package org.littlahands.dddsample.dddsample.v4.domain.screening;

import lombok.Getter;
import lombok.Setter;
import org.littlahands.dddsample.dddsample.v4.domain.ApplicationException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


@Getter
public class ScreeningV4 {
  /* 最大面談設定回数 */
  public static final int MAX_PRE_INTERVIEW_TIMES = 2;

  private ScreeningIdV4 screeningId;

  private LocalDate applyDate;

  private ScreeningStatusV4 status;

  private EmailAddress applicantEmailAddress;

  private List<InterviewV4> interviews;

  private List<PreInterviewV4> preInterviews;

  public void stepToNext() throws ApplicationException {
    this.status = this.status.nextStep();
  }

  // factory methods

  public static ScreeningV4 apply(EmailAddress applicantEmailAddress) {
    ScreeningV4 screening = new ScreeningV4();

    // 引数の値を設定する項目
    screening.applicantEmailAddress = applicantEmailAddress;

    // コンストラクタで設定する値を判断する項目
    screening.screeningId = new ScreeningIdV4();
    screening.applyDate = LocalDate.now();
    screening.status = ScreeningStatusV4.Interview;
    screening.interviews = Collections.emptyList();
    screening.preInterviews = Collections.emptyList();
    return screening;
  }

  public static ScreeningV4 startFromPreInterview(EmailAddress applicantEmailAddress) {
    ScreeningV4 screening = new ScreeningV4();

    // 引数の値を設定する項目
    screening.applicantEmailAddress = applicantEmailAddress;

    // コンストラクタで設定する値を判断する項目
    screening.screeningId = new ScreeningIdV4();
    screening.applyDate = null;
    screening.status = ScreeningStatusV4.NotApplied;
    screening.interviews = Collections.emptyList();
    screening.preInterviews = Collections.emptyList();
    return screening;
  }

  // mutator methods

  public void addNextInterview(LocalDate interviewDate) throws ApplicationException {
    if (!this.status.canAddInterview()) {
      throw new ApplicationException("Invalid operation");
    }
    org.littlahands.dddsample.dddsample.v4.domain.screening.InterviewV4 nextInterview =
        new org.littlahands.dddsample.dddsample.v4.domain.screening.InterviewV4(interviewDate, this.interviews.size() + 1);
    this.interviews.add(nextInterview);
  }

  public void addPreInterview(LocalDate interviewDate) throws ApplicationException {
    if (!this.status.canAddInterview()) {
      throw new ApplicationException("Invalid operation");
    }
    if (this.preInterviews.size() > MAX_PRE_INTERVIEW_TIMES) {
      throw new ApplicationException("Max preInterview time reached.");
    }

    PreInterviewV4 preInterviews = new PreInterviewV4(interviewDate);
    this.preInterviews.add(preInterviews);
  }

  // private constructor

  static ScreeningV4 reconstruct(ScreeningIdV4 screeningId, LocalDate applyDate, ScreeningStatusV4 status, EmailAddress applicantEmailAddress, List<InterviewV4> interviews) {
    ScreeningV4 screening = new ScreeningV4();
    screening.screeningId = screeningId;
    screening.applyDate = applyDate;
    screening.status = status;
    screening.applicantEmailAddress = applicantEmailAddress;
    screening.interviews = interviews;
    return screening;
  }

  private ScreeningV4() {
  }

}
