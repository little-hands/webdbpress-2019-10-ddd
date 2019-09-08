package org.littlahands.dddsample.dddsample.v3.domain.screening;


import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode
public class ScreeningId {
  // プリミティブな値を保持する変数
  private String value;

  // デフォルトコンストラクタ
  // UUIDで採番するということをこのクラス自身が知識として持っている
  public ScreeningId() {
    this.value = UUID.randomUUID().toString();
  }

  private ScreeningId(String value) {
    this.value = value;
  }

  public static ScreeningId reconstruct(String value) {
    return new ScreeningId(value);
  }

  // 永続化やフロントに返す際に必要になる、
  // オブジェクトの中の値を取得するメソッド
  public String stringValue() {
    return value;
  }


}