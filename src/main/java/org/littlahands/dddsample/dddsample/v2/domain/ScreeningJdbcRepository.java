package org.littlahands.dddsample.dddsample.v2.domain;

import org.springframework.stereotype.Component;

@Component
public class ScreeningJdbcRepository implements ScreeningRepository {
  public ScreeningV2 findById(String screeningId) {
    return null;
  }

  public void insert(ScreeningV2 screening) {  }

  public void update(ScreeningV2 screening) {  }
}
