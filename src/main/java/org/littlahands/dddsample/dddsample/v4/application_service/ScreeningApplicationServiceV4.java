package org.littlahands.dddsample.dddsample.v4.application_service;

import org.littlahands.dddsample.dddsample.shared.ApplicationException;
import org.littlahands.dddsample.dddsample.v4.domain.screening.EmailAddress;
import org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningId;
import org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningRepository;
import org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningV4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ScreeningApplicationServiceV4 {
  @Autowired
  private ScreeningRepository screeningRepository;

  /**
   * 面談から新規候補者を登録する
   */
  @Transactional
  public void startFromPreInterview(String applicantEmailAddress) throws ApplicationException {
    ScreeningV4 screening =
        ScreeningV4.startFromPreInterview(new EmailAddress(applicantEmailAddress));
    screeningRepository.insert(screening);
  }

  /**
   * 新規応募者を登録する
   */
  @Transactional
  public void apply(String applicantName) throws ApplicationException {
    ScreeningV4 screening =
        ScreeningV4.apply(new EmailAddress(applicantName));
    screeningRepository.insert(screening);
  }

  /**
   * 次の面接を設定する
   */
  @Transactional
  public void addNextInterview(String screeningId, LocalDate interviewDate) throws ApplicationException {
    ScreeningV4 screening = screeningRepository.findById(org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningId.reconstruct(screeningId));
    screening.addNextInterview(interviewDate);
    screeningRepository.update(screening);
  }


  /**
   * 採用選考を次のステップに進める
   */
  @Transactional
  public void stepToNext(String screeningId)
      throws ApplicationException {
    // 永続化されたオブジェクトを取得する
    ScreeningV4 screening = screeningRepository
        .findById(ScreeningId.reconstruct(screeningId));
    // Screeningクラスの「次のステップに進める」というメソッドを呼び、
    // 永続化するだけ
    screening.stepToNext();
    screeningRepository.update(screening);
  }

  // 不合格、辞退、戻すのメソッドは別途作成する
}
