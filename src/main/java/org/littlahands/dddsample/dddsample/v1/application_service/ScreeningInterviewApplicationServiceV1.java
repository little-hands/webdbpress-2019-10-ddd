package org.littlahands.dddsample.dddsample.v1.application_service;

import org.littlahands.dddsample.dddsample.v1.domain.ApplicationException;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningStatusV1;
import org.littlahands.dddsample.dddsample.v1.domain.InterviewV1;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;
import org.littlahands.dddsample.dddsample.v1.domain.dao.ScreeningDao;
import org.littlahands.dddsample.dddsample.v1.domain.dao.InterviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.littlahands.dddsample.dddsample.shared.Const.CONST_EMAIL_REGEX;
import static org.littlahands.dddsample.dddsample.v1.domain.ScreeningStatusV1.Interview;

@Service
public class ScreeningInterviewApplicationServiceV1 {
  @Autowired
  private ScreeningDao screeningDao;
  @Autowired
  private InterviewDao interviewDao;

  /**
   * 面談から新規候補者を登録します
   */
  public void startFromPreInterview(String applicantEmailAddress) throws ApplicationException {
    if (applicantEmailAddress == null || applicantEmailAddress.length() == 0
        || isInvalidEmailAddress(applicantEmailAddress)) {
      throw new ApplicationException("Invalid applicant name.");
    }
    ScreeningV1 screening = new ScreeningV1();
    screening.setScreeningId(UUID.randomUUID().toString());
    screening.setStatus(ScreeningStatusV1.NotApplied);
    screening.setApplyDate(null); // 未応募なので応募日はnull
    screening.setApplicantEmailAddress(applicantEmailAddress);
    screeningDao.insert(screening);
  }

  /**
   * 新規応募者を登録します.
   */
  public void apply(String applicantEmailAddress) throws ApplicationException {
    if (applicantEmailAddress == null || applicantEmailAddress.length() == 0
        || isInvalidEmailAddress(applicantEmailAddress)) {
      throw new ApplicationException("Invalid applicant name.");
    }
    ScreeningV1 screening = new ScreeningV1();
    screening.setScreeningId(UUID.randomUUID().toString());
    screening.setStatus(Interview);
    screening.setApplyDate(LocalDate.now());
    screening.setApplicantEmailAddress(applicantEmailAddress);
    screeningDao.insert(screening);
  }

  private boolean isInvalidEmailAddress(String email) {
    if (email == null)
      return false;
    String deficientRegex = CONST_EMAIL_REGEX; // 適切な正規表現が記述されているとする
    return !Pattern.compile(deficientRegex).matcher(email).matches();
  }

  /**
   * 次の面接を設定します。
   */
  public void addNextInterview(String screeningId, LocalDate interviewDate)
      throws ApplicationException {
    ScreeningV1 screening = screeningDao.findScreeningById(screeningId);
    if (screening.getStatus() != ScreeningStatusV1.Interview) {
      throw new ApplicationException("Invalid operation");
    }

    List<InterviewV1> interviewV1s = interviewDao.findByScreeningId(screeningId);

    InterviewV1 interview = new InterviewV1();
    interview.setInterviewId(UUID.randomUUID().toString());
    interview.setScreeningId(screeningId);
    interview.setInterviewNumber(interviewV1s.size() + 1);
    interview.setScreeningDate(interviewDate);
    interviewDao.insert(interview);
  }

  // 面談から面接に進む処理は省略

}
