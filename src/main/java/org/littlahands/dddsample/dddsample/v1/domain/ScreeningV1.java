package org.littlahands.dddsample.dddsample.v1.domain;

import lombok.Getter;
import lombok.Setter;
import org.littlahands.dddsample.dddsample.shared.ApplicationException;

import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.littlahands.dddsample.dddsample.shared.Const.CONST_EMAIL_REGEX;

@Setter
@Getter
public class ScreeningV1 {
  // 採用選考ID
  private String screeningId;
  // 応募日
  private LocalDate applyDate;
  // 採用選考ステータス
  private ScreeningStatusV1 status;
  // 応募者メールアドレス
  private String applicantEmailAddress;

  private ScreeningV1() {
  }

  public static ScreeningV1 startFromPreInterview(String applicantEmailAddress) throws ApplicationException {
    // 入力チェック
    validateEmailAddress(applicantEmailAddress);

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
    return screening;
  }

  public static ScreeningV1 apply(String applicantEmailAddress) throws ApplicationException {
    // 入力チェック
    validateEmailAddress(applicantEmailAddress);

    ScreeningV1 screening = new ScreeningV1();
    screening.setScreeningId(UUID.randomUUID().toString());
    // 面接からの場合はステータス「面接」で登録
    screening.setStatus(ScreeningStatusV1.Interview);
    // 応募日は操作日付を使用
    screening.setApplyDate(LocalDate.now());
    screening.setApplicantEmailAddress(applicantEmailAddress);
    return screening;
  }

  // private methods

  private static void validateEmailAddress(String applicantEmailAddress) throws ApplicationException {
    if (isEmpty(applicantEmailAddress) ||
        isInvalidFormatEmailAddress(applicantEmailAddress)) {
      throw new ApplicationException(
          "メールアドレスが正しくありません");
    }
  }

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
