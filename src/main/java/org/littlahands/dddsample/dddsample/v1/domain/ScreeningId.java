package org.littlahands.dddsample.dddsample.v1.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class ScreeningId {
  private String value;

  public ScreeningId() {
    this.value = UUID.randomUUID().toString();
  }

  public ScreeningId(String value) {
    this.value = value;
  }
}
