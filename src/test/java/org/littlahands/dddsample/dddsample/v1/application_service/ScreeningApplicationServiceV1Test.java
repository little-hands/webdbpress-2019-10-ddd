package org.littlahands.dddsample.dddsample.v1.application_service;


import org.junit.Before;
import org.junit.Test;
import org.littlahands.dddsample.dddsample.shared.ApplicationException;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningStatusV1;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;
import org.littlahands.dddsample.dddsample.v1.domain.dao.ScreeningDao;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;


public class ScreeningApplicationServiceV1Test {
  private ScreeningApplicationServiceV1 applicationService;
  private ScreeningImMemoryDao screeningDao;
  private InterviewInMemoryDao interviewDao;

  @Before
  public void setup() {
    screeningDao = new ScreeningImMemoryDao();
    interviewDao = new InterviewInMemoryDao();
    applicationService = new ScreeningApplicationServiceV1(screeningDao, interviewDao);
  }

  @Test
  public void test() throws ApplicationException {
    // when: 正しいメールアドレスで登録すると
    String emailAddress = "a@example.com";
    applicationService.startFromPreInterview(emailAddress);

    // then: 採用進捗が保存される
    ScreeningV1 savedScreening = screeningDao.findScreeningByEmailAddress(emailAddress).get();
    assertThat(savedScreening.getScreeningId(), is(notNullValue()));
    assertThat(savedScreening.getStatus(), is(ScreeningStatusV1.NotApplied));
  }

}