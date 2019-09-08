package org.littlahands.dddsample.dddsample.v3.domain.screening;

import lombok.EqualsAndHashCode;
import org.littlahands.dddsample.dddsample.v3.domain.ApplicationException;

import java.util.regex.Pattern;

import static org.littlahands.dddsample.dddsample.shared.Const.CONST_EMAIL_REGEX;

@EqualsAndHashCode // (d1)
public class EmailAddress {
  // プリミティブな値を保持する変数
  private String value;

  public EmailAddress(String value) throws ApplicationException {
    // コンストラクタの中で書式チェックしているので、
    // 書式チェックが通ったものしかインスタンス化されない
    if (isEmpty(value) ||
        isInvalidFormatEmailAddress(value)) {
      throw new ApplicationException("メールアドレスが正しくありません");
    }
    this.value = value;
  }

  private EmailAddress() {
  }

  static EmailAddress reconstruct(String value) {
    EmailAddress emailAddress = new EmailAddress();
    emailAddress.value = value;
    return emailAddress;
  }


  // 永続化やフロントに返す際に必要になる、
  // オブジェクトの中の値を取得するメソッド
  public String stringValue() {
    return value;
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
}
