package org.littlahands.dddsample.dddsample.v3.application_service;

import org.littlahands.dddsample.dddsample.v3.domain.screening.*;
import org.littlahands.dddsample.dddsample.v3.domain.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.littlahands.dddsample.dddsample.v3.domain.screening.ScreeningStatusV3.*;

@Service
public class ScreeningApplicationServiceV3 {
  @Autowired
  private ScreeningRepositoryV3 screeningRepository;

  /**
   * 面談から新規候補者を登録する
   */
  @Transactional
  public void startFromPreInterview(String applicantEmailAddress) throws ApplicationException {
    ScreeningV3 screening =
        ScreeningV3.startFromPreInterview(new EmailAddress(applicantEmailAddress));
    screeningRepository.insert(screening);
  }

  /**
   * 新規応募者を登録する
   */
  @Transactional
  public void apply(String applicantName) throws ApplicationException {
    ScreeningV3 screening =
        ScreeningV3.apply(new EmailAddress(applicantName));
    screeningRepository.insert(screening);
  }

  /**
   * 次の面接を設定する
   */
  @Transactional
  public void addNextInterview(String screeningId, LocalDate interviewDate) throws ApplicationException {
    ScreeningV3 screening = screeningRepository.findById(ScreeningId.reconstruct(screeningId));
    screening.addNextInterview(interviewDate);
    screeningRepository.update(screening);
  }

  // 以下 ステータス遷移説明用のプロパティ、メソッド

  /**
   * 許可する遷移パターンを保持するMap
   * Keyが遷移元のパターン、
   * Valueが許可された遷移先パターンを表す
   */
  private Map<ScreeningStatusV3, List<ScreeningStatusV3>> permittedStatuses;

  /**
   * 面接ステータスを更新する
   */
  @Transactional
  public void updateStatus(
      String screeningId,
      ScreeningStatusV3 screeningStatus)
      throws ApplicationException {
    // 永続化されたインスタンスを取得
    ScreeningV3 screening = screeningRepository
        .findById(ScreeningId.reconstruct(screeningId));
    // 許可された遷移パターンに合致するかをバリデーション
    if (!isPermittedStatus(
        screening.getStatus(), screeningStatus)) {
      throw new ApplicationException("不正な操作です");
    }
    // バリデーションを通過したら、ステータスを引数の値で更新し、保存する
    screening.setStatus(screeningStatus);
    screeningRepository.update(screening);
  }

  // 引数のステータスと、現在のステータスを比較して、
  // 遷移が許可されているパターンかをチェックする
  private boolean isPermittedStatus(
      ScreeningStatusV3 beforeStatus,
      ScreeningStatusV3 afterStatus) {
    return this.permittedStatuses
        .get(beforeStatus).contains(afterStatus);
  }

  // クラスの初期化処理として、
  // 遷移が許可されているパターンを定義する
  public ScreeningApplicationServiceV3() {
    this.permittedStatuses = new HashMap<>();
    this.permittedStatuses.put(
        NotApplied, Arrays.asList(DocumentScreening));
    this.permittedStatuses.put(
        DocumentScreening, Arrays.asList(
            Interview, DocumentScreeningRejected,
            DocumentScreeningDeclined));
    // 以下省略
  }

}
