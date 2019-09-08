package org.littlahands.dddsample.dddsample.v4.domain.screening;

import org.littlahands.dddsample.dddsample.v4.domain.ApplicationException;

public class ApplicantName {
  private String value;

  public ApplicantName(String value) throws ApplicationException {
    if (value == null || value.length() == 0) {
      throw new ApplicationException("Invalid applicant name.");
    }
    this.value = value;
  }

  public String stringValue() {
    return value;
  }
}
