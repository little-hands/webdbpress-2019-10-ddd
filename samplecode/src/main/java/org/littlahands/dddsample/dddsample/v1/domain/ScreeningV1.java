package org.littlahands.dddsample.dddsample.v1.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ScreeningV1 {
  private String screeningId;
  private LocalDate applyDate;
  private ScreeningStatusV1 status;
  private String applicantEmailAddress;
}
