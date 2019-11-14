package org.littlahands.dddsample.dddsample.v1.application_service;

import lombok.Getter;
import org.littlahands.dddsample.dddsample.shared.ApplicationException;
import org.littlahands.dddsample.dddsample.v1.domain.InterviewV1;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningStatusV1;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;
import org.littlahands.dddsample.dddsample.v1.domain.dao.InterviewDao;
import org.littlahands.dddsample.dddsample.v1.domain.dao.ScreeningDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.littlahands.dddsample.dddsample.shared.Const.CONST_EMAIL_REGEX;

@Service
@Getter
public class ScreeningApplicationServiceV1 {
  @Autowired
  private ScreeningDao screeningDao;
  @Autowired
  private InterviewDao interviewDao;

  /**
   * 面談から新規候補者を登録する
   */
  @Transactional
  public void startFromPreInterview(String applicantEmailAddress)
      throws ApplicationException {
    // 入力チェック
    if (isEmpty(applicantEmailAddress) ||
        isInvalidFormatEmailAddress(applicantEmailAddress)) {
      throw new ApplicationException(
          "メールアドレスが正しくありません");
    }
    // デフォルトコンストラクタでインスタンス作成
    ScreeningV1 screening = new ScreeningV1();
    // IDはUUIDを使用
    screening.setScreeningId(UUID.randomUUID().toString());
    // 面談からの場合はステータス「未応募」で登録
    screening.setStatus(ScreeningStatusV1.NotApplied);
    // 未応募なので応募日はnull
    screening.setApplyDate(null);
    // メールアドレスは引数のものを登録
    screening.setApplicantEmailAddress(
        applicantEmailAddress);
    screeningDao.insert(screening);
  }

  // 文字列の空白チェック用メソッド
  private boolean isEmpty(String value) {
    return value == null || value.length() == 0;
  }

  // メールアドレスのバリデーション用メソッド
  private boolean isInvalidFormatEmailAddress(String email) {
    if (email == null) {
      return false;
    }
    // CONST_EMAIL_REGEXは適切な正規表現が記述されているとする
    String emailRegex = CONST_EMAIL_REGEX;
    return !Pattern.compile(emailRegex)
        .matcher(email).matches();
  }

  /**
   * 新規応募者を登録する
   */
  @Transactional
  public void apply(String applicantEmailAddress)
      throws ApplicationException {
    if (isEmpty(applicantEmailAddress) ||
        isInvalidFormatEmailAddress(applicantEmailAddress)) {
      throw new ApplicationException("メールアドレスが正しくありません");
    }

    ScreeningV1 screening = new ScreeningV1();
    screening.setScreeningId(UUID.randomUUID().toString());
    // 面接からの場合はステータス「面接」で登録
    screening.setStatus(ScreeningStatusV1.Interview);
    // 応募日は操作日付を使用
    screening.setApplyDate(LocalDate.now());
    screening.setApplicantEmailAddress(applicantEmailAddress);
    screeningDao.insert(screening);
  }

  /**
   * 次の面接を設定する
   */
  @Transactional
  public void addNextInterview(
      String screeningId, LocalDate interviewDate)
      throws ApplicationException {

    // 保存されている採用選考オブジェクトを取得
    ScreeningV1 screening = screeningDao.findScreeningById(screeningId).get();

    // 面接設定をしてよいステータスかをチェック
    if (screening.getStatus() != ScreeningStatusV1.Interview) {
      throw new ApplicationException("不正な操作です");
    }

    // 保存されている面接オブジェクトの一覧を取得
    List<InterviewV1> interviews =
        interviewDao.findByScreeningId(screeningId);

    InterviewV1 interview = new InterviewV1();
    interview.setInterviewId(UUID.randomUUID().toString());
    interview.setScreeningId(screeningId);
    // 面接次数は保存されているインタビューの数+1とする
    interview.setInterviewNumber(interviews.size() + 1);
    interview.setScreeningDate(interviewDate);
    interviewDao.insert(interview);
  }

  // 面談から面接に進む処理は省略

  //


  public ScreeningApplicationServiceV1(ScreeningDao screeningDao, InterviewDao interviewDao) {
    this.screeningDao = screeningDao;
    this.interviewDao = interviewDao;
  }

  public ScreeningApplicationServiceV1() {
  }
}
