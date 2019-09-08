package org.littlahands.dddsample.dddsample.v2.application_service;

import org.littlahands.dddsample.dddsample.v1.domain.ApplicationException;
import org.littlahands.dddsample.dddsample.v2.domain.ScreeningV2;
import org.littlahands.dddsample.dddsample.v2.domain.dao.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ScreeningApplicationServiceV2 {
  @Autowired
  private ScreeningRepository screeningRepository;

  /**
   * 面談から新規候補者を登録します
   */
  public void startFromPreInterview(String applicantEmailAddress) throws ApplicationException {
    ScreeningV2 screening = ScreeningV2.startFromPreInterview(applicantEmailAddress);
    screeningRepository.insert(screening);
  }

  /**
   * 新規応募者を登録します.
   */
  public void apply(String applicantEmailAddress) throws ApplicationException {
    ScreeningV2 screening = ScreeningV2.apply(applicantEmailAddress);
    screeningRepository.insert(screening);
  }

  /**
   * 次の面接を設定します。
   */
  public void addNextInterview(Long screeningId, LocalDate interviewDate)
      throws ApplicationException {
    ScreeningV2 screening = screeningRepository.findById(screeningId);
    screening.addNextInterview(interviewDate);
    screeningRepository.update(screening);
  }
  // 面談から面接に進む処理は省略
}
