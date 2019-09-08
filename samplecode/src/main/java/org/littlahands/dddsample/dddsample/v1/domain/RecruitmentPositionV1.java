package org.littlahands.dddsample.dddsample.v1.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruitmentPositionV1 {
  private Long positionId;
  private String positionName;
  private boolean accepting;
}
