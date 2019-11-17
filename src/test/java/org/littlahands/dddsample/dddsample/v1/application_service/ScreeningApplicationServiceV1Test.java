package org.littlahands.dddsample.dddsample.v1.application_service;


import org.junit.Before;
import org.junit.Test;
import org.littlahands.dddsample.dddsample.shared.ApplicationException;
import org.littlahands.dddsample.dddsample.v1.domain.EmailAddress;
import org.littlahands.dddsample.dddsample.v1.domain.InterviewV1;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningStatusV1;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;
import org.littlahands.dddsample.dddsample.v1.domain.dao.ScreeningRepository;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


public class ScreeningApplicationServiceV1Test {
  private ScreeningApplicationServiceV1 applicationService;
  private ScreeningRepository screeningRepository;

  @Before
  public void setup() {
    screeningRepository = new ScreeningInMemoryRepository();
    applicationService = new ScreeningApplicationServiceV1(screeningRepository);
  }

  // startFromPreInterview

  @Test
  public void startFromPreInterview_success() throws ApplicationException {
    // when: 正しいメールアドレスで登録すると
    String emailAddress = "a@example.com";
    applicationService.startFromPreInterview(emailAddress);

    // then: 採用進捗が保存される
    ScreeningV1 savedScreening = screeningRepository.findScreeningByEmailAddress(emailAddress).get();
    assertThat(savedScreening.getScreeningId(), is(notNullValue())); // idは推測できないので、ひとまずnullではないこと
    assertThat(savedScreening.getStatus(), is(ScreeningStatusV1.NotApplied)); // 面談からの場合はステータス「未応募」で登録
    assertThat(savedScreening.getApplyDate(), is(nullValue())); // 未応募なので応募日はnull
    assertThat(savedScreening.getApplicantEmailAddress(), is(new EmailAddress(emailAddress)));
  }

  @Test(expected = ApplicationException.class)
  public void startFromPreInterview_fail_emailAddress_invalid() throws ApplicationException {
    // when: 正しくない書式のメールアドレスで登録すると
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
    assertThat(savedScreening.getStatus(), is(ScreeningStatusV1.Interview)); // 面接からの場合はステータス「面接」で登録
    assertThat(savedScreening.getApplyDate(), is(notNullValue())); // 応募日は操作日付を使用(一旦nullでないことを確認)
    assertThat(savedScreening.getApplicantEmailAddress(), is(new EmailAddress(emailAddress)));
  }


  @Test(expected = ApplicationException.class)
  public void apply_fail_emailAddress_invalid() throws ApplicationException {
    // when: 正しくない書式のメールアドレスで登録すると
    String emailAddress = "aexample.com";

    // then: 例外が投げられる
    applicationService.apply(emailAddress);
  }


  // addNextInterview

  @Test
  public void addNextInterview_success() throws ApplicationException {
    // given: 面談からの採用選考が登録されている
    String emailAddress = "a@example.com";
    String screeningId = applicationService.apply(emailAddress);

    // when: 登録済み採用選考IDを指定して、面接日を登録すると
    LocalDate interviewDate = LocalDate.of(2020, 1, 1);
    applicationService.addNextInterview(screeningId, interviewDate);

    // then:
//    List<InterviewV1> interviews = interviewRepository.findByScreeningId(screeningId);
    ScreeningV1 savedScreening = screeningRepository.findScreeningByEmailAddress(emailAddress).get();
    List<InterviewV1> interviews = savedScreening.getInterviews();
    assertThat(interviews.size(), is(1));

    InterviewV1 interview = interviews.get(0);
    assertThat(interview.getScreeningDate(), is(interviewDate)); // 面接日が登録時に指定した日付であること
    assertThat(interview.getInterviewNumber(), is(1)); // 面接次数が1であること
  }


  @Test(expected = ApplicationException.class)
  public void addNextInterview_fail_by_status() throws ApplicationException {
    // given: 面談からの採用選考が登録されている
    String emailAddress = "a@example.com";
    String screeningId = applicationService.startFromPreInterview(emailAddress);

    // when: まだ面談状態の採用選考IDを指定して、面接日を登録すると
    LocalDate interviewDate = LocalDate.of(2020, 1, 1);

    // then: 例外が投げられる
    applicationService.addNextInterview(screeningId, interviewDate);

  }

}