package org.littlahands.dddsample.dddsample.v4.application_service;

import org.littlahands.dddsample.dddsample.v4.domain.screening.*;
import org.littlahands.dddsample.dddsample.v4.domain.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ScreeningApplicationServiceV4 {
  @Autowired
  private ScreeningRepositoryV4 screeningRepository;

  /**
   * 面談から新規候補者を登録します
   */
  public void startFromPreInterview(String applicantEmailAddress) throws ApplicationException {
    ScreeningV4 screening = ScreeningV4.startFromPreInterview(new EmailAddress(applicantEmailAddress));
    screeningRepository.insert(screening);
  }

  /**
   * 新規応募者を登録します.
   */
  public void apply(String applicantName) throws ApplicationException {
    ScreeningV4 screening = ScreeningV4.apply(new EmailAddress(applicantName));
    screeningRepository.insert(screening);
  }

  /**
   * 面接を設定します。
   */
  public void addNextInterview(String screeningId, LocalDate interviewDate) throws ApplicationException {
    ScreeningV4 screening = screeningRepository.findById(new ScreeningIdV4(screeningId));
    screening.addNextInterview(interviewDate);
    screeningRepository.update(screening);
  }


  /**
   * 面談を設定します。
   */
  public void addPreInterview(String screeningId, LocalDate interviewDate) throws ApplicationException {
    ScreeningV4 screening = screeningRepository.findById(new ScreeningIdV4(screeningId));
    screening.addPreInterview(interviewDate);
    screeningRepository.update(screening);
  }
}
