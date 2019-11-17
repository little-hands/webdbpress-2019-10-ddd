package org.littlahands.dddsample.dddsample.v1.application_service;

import org.littlahands.dddsample.dddsample.shared.ApplicationException;
import org.littlahands.dddsample.dddsample.v1.domain.InterviewV1;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningStatusV1;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;
import org.littlahands.dddsample.dddsample.v1.domain.dao.InterviewRepository;
import org.littlahands.dddsample.dddsample.v1.domain.dao.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.littlahands.dddsample.dddsample.shared.Const.CONST_EMAIL_REGEX;

@Service
public class ScreeningApplicationServiceV1 {
  @Autowired
  private ScreeningRepository screeningRepository;
  @Autowired
  private InterviewRepository interviewRepository;

  /**
   * 面談から新規候補者を登録する
   */
  @Transactional
  public String startFromPreInterview(String applicantEmailAddress)
      throws ApplicationException {
    ScreeningV1 screening = ScreeningV1.startFromPreInterview(applicantEmailAddress);
    screeningRepository.insert(screening);
    return screening.getScreeningId();
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
  public String apply(String applicantEmailAddress)
      throws ApplicationException {
    ScreeningV1 screening = ScreeningV1.apply(applicantEmailAddress);
    screeningRepository.insert(screening);
    return screening.getScreeningId();
  }

  /**
   * 次の面接を設定する
   */
  @Transactional
  public void addNextInterview(
      String screeningId, LocalDate interviewDate)
      throws ApplicationException {

    // 保存されている採用選考オブジェクトを取得
    ScreeningV1 screening = screeningRepository.findScreeningById(screeningId).get();

    // 面接設定をしてよいステータスかをチェック
    if (screening.getStatus() != ScreeningStatusV1.Interview) {
      throw new ApplicationException("不正な操作です");
    }

    // 保存されている面接オブジェクトの一覧を取得
    List<InterviewV1> interviews =
        interviewRepository.findByScreeningId(screeningId);

    InterviewV1 interview = new InterviewV1();
    interview.setInterviewId(UUID.randomUUID().toString());
    interview.setScreeningId(screeningId);
    // 面接次数は保存されているインタビューの数+1とする
    interview.setInterviewNumber(interviews.size() + 1);
    interview.setScreeningDate(interviewDate);
    interviewRepository.insert(interview);
  }

  // 面談から面接に進む処理は省略


  public ScreeningApplicationServiceV1(ScreeningRepository screeningRepository, InterviewRepository interviewRepository) {
    this.screeningRepository = screeningRepository;
    this.interviewRepository = interviewRepository;
  }

  public ScreeningApplicationServiceV1() {
  }
}
