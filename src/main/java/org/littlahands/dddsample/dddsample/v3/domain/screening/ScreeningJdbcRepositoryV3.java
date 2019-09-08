package org.littlahands.dddsample.dddsample.v3.domain.screening;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("SqlNoDataSourceInspection")
@Component
class ScreeningJdbcRepositoryV3 implements ScreeningRepositoryV3 {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final String CONST_INTERVIEWS_SELECT_QUERY = "";

  private static final String CONST_SCREENING_SELECT_QUERY = "";

  public ScreeningV3 findById(ScreeningId screeningId) {
    List<InterviewV3> interviews =
        jdbcTemplate.query(
            CONST_INTERVIEWS_SELECT_QUERY,  // (d1)
            new Object[]{screeningId.stringValue()},
            (rs, rowNum) ->
                InterviewV3.reconstruct( // (d2)
                    rs.getDate("interview_date").toLocalDate(),
                    rs.getInt("interview_number"),
                    ScreeningStepResult.valueOf(
                        rs.getString("screening_step_result"))));
    return jdbcTemplate.query(
        CONST_SCREENING_SELECT_QUERY,  // (d3)
        new Object[]{screeningId.stringValue()},
        (rs, rowNum) ->
            ScreeningV3.reconstruct( // (d4)
                ScreeningId.reconstruct(rs.getString("screening_id")),
                rs.getDate("apply_date").toLocalDate(),
                ScreeningStatusV3.valueOf(rs.getString("screening_status")),
                EmailAddress.reconstruct(rs.getString("applicant_email_address")),
                Interviews.reconstruct(interviews))).get(0);
    // 簡単化のためにget(0)しているが、
    // 本来はOptionalでラップして返すのが望ましい
  }

  public void insert(ScreeningV3 screening) {
    // 省略、insert文にEntityの値を詰め替えてください
  }

  public void update(ScreeningV3 screening) {
    // 省略、update文にEntityの値を詰め替えてください
  }
}
