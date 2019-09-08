package org.littlahands.dddsample.dddsample.v3.domain.screening;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("SqlNoDataSourceInspection")
@Component
public class ScreeningRepositoryV3 {

  private static String CONST_INTERVIEWS_SELECT_QUERY = "";
  private static String CONST_INTERVIEWS_DELETE_QUERY = "";
  private static String CONST_INTERVIEWS_INSERT_QUERY = "";

  private static String CONST_SCREENINGS_SELECT_QUERY = "";
  private static String CONST_SCREENINGS_INSERT_QUERY = "";
  private static String CONST_SCREENINGS_UPDATE_QUERY = "";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public ScreeningV3 findById(ScreeningIdV3 screeningId) {
    public ScreeningV3 findById (ScreeningIdV3 screeningId){
      List<InterviewV3> interviews =
          jdbcTemplate.query(CONST_INTERVIEWS_SELECT_QUERY,  // -- (d1)
              new Object[]{screeningId.stringValue()},
              (rs, rowNum) ->
                  InterviewV3.reconstruct( // -- (d2)
                      rs.getDate("interview_date").toLocalDate(),
                      rs.getInt("interview_number"),
                      ScreeningStepResult.valueOf(
                          rs.getString("screening_step_result")))
          );

      return jdbcTemplate.query(CONST_SCREENINGS_SELECT_QUERY,  // -- (d3)
          new Object[]{screeningId.stringValue()},
          (rs, rowNum) ->
              ScreeningV3.reconstruct( // -- (d4)
                  new ScreeningIdV3(rs.getString("screening_id")),
                  rs.getDate("apply_date").toLocalDate(),
                  ScreeningStatusV3.valueOf(rs.getString("screenint_status")),
                  EmailAddress.reconstruct(rs.getString("applicant_email_address")),
                  new Interviews(interviews))).get(0);
    }

    public void insert (ScreeningV3 screening){
    }

    public void update (ScreeningV3 screening){
//    jdbcTemplate.update(
//        CONST_INTERVIEWS_DELETE_QUERY, screening.getScreeningId().stringValue());
//    jdbcTemplate.update("inset into ",
//
//        )


    }
  }
