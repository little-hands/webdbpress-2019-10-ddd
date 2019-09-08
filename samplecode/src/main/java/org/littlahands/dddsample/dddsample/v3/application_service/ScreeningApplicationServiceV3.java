package org.littlahands.dddsample.dddsample.v3.application_service;

import org.littlahands.dddsample.dddsample.v3.domain.screening.*;
import org.littlahands.dddsample.dddsample.v3.domain.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ScreeningApplicationServiceV3 {
  @Autowired
  private ScreeningRepositoryV3 screeningRepository;

  /**
   * 面談から新規候補者を登録します
   */
  public void startFromPreInterview(String applicantEmailAddress) throws ApplicationException {
    ScreeningV3 screening = ScreeningV3.startFromPreInterview(new EmailAddress(applicantEmailAddress));
    screeningRepository.insert(screening);
  }

  /**
   * 新規応募者を登録します.
   */
  public void apply(String applicantName) throws ApplicationException {
    ScreeningV3 screening = ScreeningV3.apply(new EmailAddress(applicantName));
    screeningRepository.insert(screening);
  }

  /**
   * 次の面接を設定します。
   */
  public void addNextInterview(String screeningId, LocalDate interviewDate) throws ApplicationException {
    ScreeningV3 screening = screeningRepository.findById(new ScreeningIdV3(screeningId));
    screening.addNextInterview(interviewDate);
    screeningRepository.update(screening);
  }


}
