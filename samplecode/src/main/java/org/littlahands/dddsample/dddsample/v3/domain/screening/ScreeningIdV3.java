package org.littlahands.dddsample.dddsample.v3.domain.screening;


import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode
public class ScreeningIdV3 {
  private String value;

  public String stringValue() {
    return value;
  }

  ScreeningIdV3() {
    this.value = UUID.randomUUID().toString();
  }

  public ScreeningIdV3(String value) {
    this.value = value;
  }
}
