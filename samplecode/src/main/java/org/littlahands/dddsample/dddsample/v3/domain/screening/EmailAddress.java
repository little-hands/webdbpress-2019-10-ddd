package org.littlahands.dddsample.dddsample.v3.domain.screening;

import lombok.EqualsAndHashCode;
import org.littlahands.dddsample.dddsample.v3.domain.ApplicationException;

import java.util.regex.Pattern;

import static org.littlahands.dddsample.dddsample.shared.Const.CONST_EMAIL_REGEX;

@EqualsAndHashCode
public class EmailAddress {
  private String value;

  public EmailAddress(String value) throws ApplicationException {
    if (isEmpty(value) || isInvalidFormat(value)) {
      throw new ApplicationException("Invalid applicant name.");
    }
    this.value = value;
  }

  private boolean isEmpty(String value) {
    return value == null || value.length() == 0;
  }

  private boolean isInvalidFormat(String email) {
    String emailRegex = CONST_EMAIL_REGEX; // 適切な正規表現が記述されているとする
    return !Pattern.compile(emailRegex).matcher(email).matches();
  }

  public String stringValue() {
    return value;
  }

  private EmailAddress() {
  }

  // 永続化やフロントに返す際にに必要になる、オブジェクトの中の値を取得するメソッド
  public static EmailAddress reconstruct(String value) {
    EmailAddress emailAddress = new EmailAddress();
    emailAddress.value = value;
    return emailAddress;
  }
}
