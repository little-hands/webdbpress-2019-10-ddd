package org.littlahands.dddsample.dddsample.v4.domain.screening;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScreeningJdbcRepository implements ScreeningRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final String CONST_INTERVIEWS_SELECT_QUERY = "";

  private static final String CONST_SCREENING_SELECT_QUERY = "";

  public ScreeningV4 findById(ScreeningId screeningId) {
    List<InterviewV4> interviews =
        jdbcTemplate.query(
            CONST_INTERVIEWS_SELECT_QUERY,  // ①
            new Object[]{screeningId.stringValue()},
            (rs, rowNum) ->
                InterviewV4.reconstruct( // ②
                    rs.getDate("interview_date").toLocalDate(),
                    rs.getInt("interview_number"),
                    ScreeningStepResult.valueOf(
                        rs.getString("screening_step_result"))));
    return jdbcTemplate.query(
        CONST_SCREENING_SELECT_QUERY,  // ④
        new Object[]{screeningId.stringValue()},
        (rs, rowNum) ->
            ScreeningV4.reconstruct( // ④
                ScreeningId.reconstruct(rs.getString("screening_id")),
                rs.getDate("apply_date").toLocalDate(),
                ScreeningStatusV4.valueOf(rs.getString("screening_status")),
                EmailAddress.reconstruct(rs.getString("applicant_email_address")),
                Interviews.reconstruct(interviews))).get(0);
    // 簡単化のためにget(0)しているが、
    // 本来はOptionalでラップして返すのが望ましい
  }

  public void insert(ScreeningV4 screening) {
    // 省略、insert文にEntityの値を詰め替えてください
  }

  public void update(ScreeningV4 screening) {
    // 省略、update文にEntityの値を詰め替えてください
  }
}
