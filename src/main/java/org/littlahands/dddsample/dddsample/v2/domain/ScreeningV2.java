package org.littlahands.dddsample.dddsample.v2.domain;

import lombok.Getter;
import org.littlahands.dddsample.dddsample.shared.ApplicationException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.littlahands.dddsample.dddsample.shared.Const.CONST_EMAIL_REGEX;


@Getter
public class ScreeningV2 {
  private String screeningId;
  private LocalDate applyDate;
  private ScreeningStatusV2 status;
  private String applicantEmailAddress;
  private List<InterviewV2> interviews;


  // 生成メソッド

  /**
   * ⑤ デフォルトコンストラクタはprivateにして
   * 下記2つのファクトリメソッドからしか呼べなくしている
   */
  private ScreeningV2() {
  }

  /**
   * 面談から採用選考を登録する際のファクトリメソッド
   */
  public static ScreeningV2 startFromPreInterview(
      String applicantEmailAddress)
      throws ApplicationException {
    // 不正なインスタンスを生成させないために、コンストラクタでチェックする
    if (isEmpty(applicantEmailAddress) ||
        isInvalidFormatEmailAddress(applicantEmailAddress)) {
      throw new ApplicationException(
          "メールアドレスが正しくありません");
    }
    ScreeningV2 screening = new ScreeningV2();
    screening.screeningId =
        UUID.randomUUID().toString();
    screening.interviews = Collections.emptyList();
    screening.applicantEmailAddress =
        applicantEmailAddress;

    // ① 初期ステータスは「未応募」
    screening.status = ScreeningStatusV2.NotApplied;
    // ② 応募日はブランク
    screening.applyDate = null;

    return screening;
  }

  /**
   * 面接から採用選考を登録する際のファクトリメソッド
   */
  public static ScreeningV2 apply(
      String applicantEmailAddress)
      throws ApplicationException {
    if (isEmpty(applicantEmailAddress)
        || isInvalidFormatEmailAddress(applicantEmailAddress)) {
      throw new ApplicationException("メールアドレスが正しくありません");
    }
    ScreeningV2 screening = new ScreeningV2();
    screening.applicantEmailAddress =
        applicantEmailAddress;
    screening.screeningId =
        UUID.randomUUID().toString();
    screening.interviews = Collections.emptyList();

    // ③ 初期ステータスは「選考中」
    screening.status = ScreeningStatusV2.Interview;

    // ④ 応募日は登録日
    screening.applyDate = LocalDate.now();
    return screening;
  }


  // ミューテーションメソッド

  public void addNextInterview(
      LocalDate interviewDate)
      throws ApplicationException {
    // ① 選考ステータスが「選考中」以外のときには
    //    設定できない
    if (this.status != ScreeningStatusV2.Interview) {
      throw new ApplicationException("不正な操作です");
    }

    // ② 面接次数は1からインクリメントされる
    int nextInterviewNumber =
        this.interviews.size() + 1;
    InterviewV2 nextInterview =
        new InterviewV2(interviewDate, nextInterviewNumber);

    this.interviews.add(nextInterview);
  }


  // プライベートメソッド

  // 文字列の空白チェック用メソッド
  private static boolean isEmpty(String value) {
    return value == null || value.length() == 0;
  }

  // メールアドレスのバリデーション用メソッド
  private static boolean isInvalidFormatEmailAddress(String email) {
    if (email == null) {
      return false;
    }
    // CONST_EMAIL_REGEXは適切な正規表現が記述されているとする
    String emailRegex = CONST_EMAIL_REGEX;
    return !Pattern.compile(emailRegex)
        .matcher(email).matches();
  }

}
