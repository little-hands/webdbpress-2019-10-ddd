package org.littlahands.dddsample.dddsample.v4.application_service;

import org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningId;
import org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningRepositoryV4;
import org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningStatusV4;
import org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningV4;
import org.littlahands.dddsample.dddsample.v4.domain.screening.*;
import org.littlahands.dddsample.dddsample.v4.domain.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningStatusV4.*;

@Service
public class ScreeningApplicationServiceV4 {
  @Autowired
  private ScreeningRepositoryV4 screeningRepository;

  /**
   * 面談から新規候補者を登録する
   */
  @Transactional
  public void startFromPreInterview(String applicantEmailAddress) throws org.littlahands.dddsample.dddsample.v4.domain.ApplicationException {
    ScreeningV4 screening =
        ScreeningV4.startFromPreInterview(new org.littlahands.dddsample.dddsample.v4.domain.screening.EmailAddress(applicantEmailAddress));
    screeningRepository.insert(screening);
  }

  /**
   * 新規応募者を登録する
   */
  @Transactional
  public void apply(String applicantName) throws org.littlahands.dddsample.dddsample.v4.domain.ApplicationException {
    ScreeningV4 screening =
        ScreeningV4.apply(new org.littlahands.dddsample.dddsample.v4.domain.screening.EmailAddress(applicantName));
    screeningRepository.insert(screening);
  }

  /**
   * 次の面接を設定する
   */
  @Transactional
  public void addNextInterview(String screeningId, LocalDate interviewDate) throws org.littlahands.dddsample.dddsample.v4.domain.ApplicationException {
    ScreeningV4 screening = screeningRepository.findById(org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningId.reconstruct(screeningId));
    screening.addNextInterview(interviewDate);
    screeningRepository.update(screening);
  }

  // 以下 ステータス遷移説明用のプロパティ、メソッド

  /**
   * 許可する遷移パターンを保持するMap
   * Keyが遷移元のパターン、
   * Valueが許可された遷移先パターンを表す
   */
  private Map<ScreeningStatusV4, List<ScreeningStatusV4>> permittedStatuses;

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
