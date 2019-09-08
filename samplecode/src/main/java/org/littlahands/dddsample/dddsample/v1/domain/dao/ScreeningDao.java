package org.littlahands.dddsample.dddsample.v1.domain.dao;

import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;
import org.springframework.stereotype.Component;

@Component
public class ScreeningDao {
  public ScreeningV1 findScreeningById(String screeningId) {
    return new ScreeningV1();
  }

  public void insert(ScreeningV1 screening) {

  }
}
