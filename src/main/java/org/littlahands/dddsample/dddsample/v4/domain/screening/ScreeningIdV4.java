package org.littlahands.dddsample.dddsample.v4.domain.screening;


import java.util.UUID;

public class ScreeningIdV4 {
  private String value;

  public String stringValue() {
    return value;
  }

  ScreeningIdV4() {
    this.value = UUID.randomUUID().toString();
  }

  public ScreeningIdV4(String value) {
    this.value = value;
  }
}
