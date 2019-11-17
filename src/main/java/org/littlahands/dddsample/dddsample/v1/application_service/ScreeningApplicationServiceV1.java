package org.littlahands.dddsample.dddsample.v1.application_service;

import org.littlahands.dddsample.dddsample.shared.ApplicationException;
import org.littlahands.dddsample.dddsample.v1.domain.EmailAddress;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;
import org.littlahands.dddsample.dddsample.v1.domain.dao.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ScreeningApplicationServiceV1 {
  @Autowired
  private ScreeningRepository screeningRepository;

  /**
   * 面談から新規候補者を登録する
   */
  @Transactional
  public String startFromPreInterview(String applicantEmailAddress)
      throws ApplicationException {
    ScreeningV1 screening = ScreeningV1.startFromPreInterview(new EmailAddress(applicantEmailAddress));
    screeningRepository.insert(screening);
    return screening.getScreeningId();
  }

  /**
   * 新規応募者を登録する
   */
  @Transactional
  public String apply(String applicantEmailAddress) throws ApplicationException {
    ScreeningV1 screening = ScreeningV1.apply(new EmailAddress(applicantEmailAddress));
    screeningRepository.insert(screening);
    return screening.getScreeningId();
  }

  /**
   * 次の面接を設定する
   */
  @Transactional
  public void addNextInterview(String screeningId, LocalDate interviewDate)
      throws ApplicationException {
    // 保存されている採用選考オブジェクトを取得
    ScreeningV1 screening = screeningRepository.findScreeningById(screeningId).get();

    // 保存されている面接オブジェクトの一覧を取得
    screening.addNextInterview(interviewDate);
    screeningRepository.insert(screening);
  }

  // 面談から面接に進む処理は省略


  public ScreeningApplicationServiceV1(ScreeningRepository screeningRepository) {
    this.screeningRepository = screeningRepository;
  }

  public ScreeningApplicationServiceV1() {
  }
}
