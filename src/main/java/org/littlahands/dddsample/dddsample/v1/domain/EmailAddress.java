package org.littlahands.dddsample.dddsample.v1.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class EmailAddress {
  private String value;
  public EmailAddress(String value) {
    this.value = value;
  }
}
