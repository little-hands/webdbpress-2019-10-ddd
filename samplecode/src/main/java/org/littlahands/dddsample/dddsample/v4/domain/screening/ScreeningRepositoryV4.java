package org.littlahands.dddsample.dddsample.v4.domain.screening;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

public interface ScreeningRepositoryV4 {

  ScreeningV4 findById(ScreeningIdV4 screeningId);

  void insert(ScreeningV4 screening);

  void update(ScreeningV4 screening);
}
