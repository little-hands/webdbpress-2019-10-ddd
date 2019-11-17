package org.littlahands.dddsample.dddsample.v1.domain;

import lombok.EqualsAndHashCode;
import org.littlahands.dddsample.dddsample.shared.ApplicationException;

import java.util.regex.Pattern;

import static org.littlahands.dddsample.dddsample.shared.Const.CONST_EMAIL_REGEX;

@EqualsAndHashCode
public class EmailAddress {
  private String value;
  public EmailAddress(String value) throws ApplicationException {
    validateEmailAddress(value);
    this.value = value;
  }


  private  void validateEmailAddress(String applicantEmailAddress) throws ApplicationException {
    if (isEmpty(applicantEmailAddress) ||
        isInvalidFormatEmailAddress(applicantEmailAddress)) {
      throw new ApplicationException(
          "メールアドレスが正しくありません");
    }
  }

  // 文字列の空白チェック用メソッド
  private  boolean isEmpty(String value) {
    return value == null || value.length() == 0;
  }

  // メールアドレスのバリデーション用メソッド
  private  boolean isInvalidFormatEmailAddress(String email) {
    if (email == null) {
      return false;
    }
    // CONST_EMAIL_REGEXは適切な正規表現が記述されているとする
    String emailRegex = CONST_EMAIL_REGEX;
    return !Pattern.compile(emailRegex)
        .matcher(email).matches();
  }
}
