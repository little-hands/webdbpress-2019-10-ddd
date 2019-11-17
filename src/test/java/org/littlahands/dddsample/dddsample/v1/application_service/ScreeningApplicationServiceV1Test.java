package org.littlahands.dddsample.dddsample.v1.application_service;


import org.junit.Before;
import org.junit.Test;
import org.littlahands.dddsample.dddsample.shared.ApplicationException;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningStatusV1;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;
import org.littlahands.dddsample.dddsample.v1.domain.dao.InterviewRepository;
import org.littlahands.dddsample.dddsample.v1.domain.dao.ScreeningRepository;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


public class ScreeningApplicationServiceV1Test {
  private ScreeningApplicationServiceV1 applicationService;
  private ScreeningRepository screeningRepository;
  private InterviewRepository interviewRepository;

  @Before
  public void setup() {
    screeningRepository = new ScreeningInMemoryRepository();
    interviewRepository = new InterviewInMemoryRepository();
    applicationService = new ScreeningApplicationServiceV1(screeningRepository, interviewRepository);
  }

  // startFromPreInterview

  @Test
  public void startFromPreInterview_success() throws ApplicationException {
    // when: 正しいメールアドレスで登録すると
    String emailAddress = "a@example.com";
    applicationService.startFromPreInterview(emailAddress);

    // then: 採用進捗が保存される
    ScreeningV1 savedScreening = screeningRepository.findScreeningByEmailAddress(emailAddress).get();
    assertThat(savedScreening.getScreeningId(), is(notNullValue()));
    assertThat(savedScreening.getStatus(), is(ScreeningStatusV1.NotApplied));
    assertThat(savedScreening.getApplyDate(), is(nullValue()));
  }

  @Test(expected = ApplicationException.class)
  public void startFromPreInterview_fail_emailAddress_invalid() throws ApplicationException {
    // when: 正しくないメールアドレスで登録すると
    String emailAddress = "aexample.com";

    // then: 例外が投げられる
    applicationService.startFromPreInterview(emailAddress);
  }


  @Test(expected = ApplicationException.class)
  public void startFromPreInterview_fail_emailAddress_blank() throws ApplicationException {
    // when: 空のメールアドレスで登録すると
    String emailAddress = "";

    // then: 例外が投げられる
    applicationService.startFromPreInterview(emailAddress);
  }


  // apply

  @Test
  public void apply_success() throws ApplicationException {
    // when: 正しいメールアドレスで登録すると
    String emailAddress = "a@example.com";
    applicationService.apply(emailAddress);

    // then: 採用進捗が保存される
    ScreeningV1 savedScreening = screeningRepository.findScreeningByEmailAddress(emailAddress).get();
    assertThat(savedScreening.getScreeningId(), is(notNullValue()));
    assertThat(savedScreening.getStatus(), is(ScreeningStatusV1.Interview));
    assertThat(savedScreening.getApplyDate(), is(notNullValue()));
  }

}