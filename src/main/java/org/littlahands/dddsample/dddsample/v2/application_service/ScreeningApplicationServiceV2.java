package org.littlahands.dddsample.dddsample.v2.application_service;

import org.littlahands.dddsample.dddsample.shared.ApplicationException;
import org.littlahands.dddsample.dddsample.v2.domain.ScreeningRepository;
import org.littlahands.dddsample.dddsample.v2.domain.ScreeningV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ScreeningApplicationServiceV2 {
  @Autowired
  private ScreeningRepository screeningRepository;

  /**
   * 面談から新規候補者を登録する
   */
  @Transactional
  public void startFromPreInterview(String applicantEmailAddress)
      throws ApplicationException {
    // ファクトリメソッドでインスタンスを生成し、そのまま保存
    ScreeningV2 screening =
        ScreeningV2.startFromPreInterview(applicantEmailAddress);
    screeningRepository.insert(screening);
  }

  /**
   * 新規応募者を登録する
   */
  @Transactional
  public void apply(String applicantEmailAddress)
      throws ApplicationException {
    // ファクトリメソッドでインスタンスを生成し、そのまま保存
    ScreeningV2 screening =
        ScreeningV2.apply(applicantEmailAddress);
    screeningRepository.insert(screening);
  }

  /**
   * 次の面接を設定する
   */
  @Transactional
  public void addNextInterview(
      String screeningId, LocalDate interviewDate)
      throws ApplicationException {
    // 永続化されたオブジェクトを「集約単位で」取得
    ScreeningV2 screening =
        screeningRepository.findById(screeningId);
    // ミューテーションメソッドを呼び出し、保存
    screening.addNextInterview(interviewDate);
    screeningRepository.update(screening);
  }

  // 面談から面接に進む処理は省略
}
