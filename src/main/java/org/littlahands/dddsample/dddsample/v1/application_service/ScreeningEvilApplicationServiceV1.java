package org.littlahands.dddsample.dddsample.v1.application_service;

import org.littlahands.dddsample.dddsample.v1.domain.dao.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * V1の実装だと、間違った実装が容易に作れてしまうという実例
 */
@Service
public class ScreeningEvilApplicationServiceV1 {
  @Autowired
  private ScreeningRepository screeningRepository;

  /**
   * 新規候補者を登録するが、
   * ルール・制約にことごとく違反する
   */
  @Transactional
  public void evilStartFromPreInterview(String applicantEmailAddress) {

//    ScreeningV1 screening = new ScreeningV1();
//
//    // × IDは現在時刻をtoStringしたもの → 時刻が被ったら死亡
//    screening.setScreeningId(LocalDate.now().toString());
//
//    // × いきなり不合格で登録
//    screening.setStatus(ScreeningStatusV1.Refected);
//
//    // × なぜか7日前を指定
//    screening.setApplyDate(LocalDate.now().minusDays(7));
//
//    // × メールアドレスはバリデーションなしで引数の値をそのまま保存
//    screening.setApplicantEmailAddress(applicantEmailAddress);
//
//    screeningRepository.insert(screening);
  }


}
