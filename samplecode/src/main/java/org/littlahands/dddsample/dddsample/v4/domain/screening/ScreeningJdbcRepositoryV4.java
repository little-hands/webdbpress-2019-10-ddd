package org.littlahands.dddsample.dddsample.v4.domain.screening;

import org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningIdV4;
import org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningV4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ScreeningJdbcRepositoryV4 implements ScreeningRepositoryV4 {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningV4 findById(org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningIdV4 screeningId) {
    return jdbcTemplate.query(
        "SELECT screening_id, apply_date, screenint_status, applicant_name FROM screenings WHERE screening_id = ?", new Object[]{screeningId.stringValue()},
        (rs, rowNum) ->
            org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningV4.reconstruct(
                new ScreeningIdV4(rs.getString("screening_id")),
                null,
                null,
                null,
                null)).get(0);
  }

  @Override
  public void insert(ScreeningV4 screening) {
  }

  @Override
  public void update(ScreeningV4 screening) {

  }
}
