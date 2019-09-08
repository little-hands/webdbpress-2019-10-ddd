package org.littlahands.dddsample.dddsample.v1.application_service;

import org.littlahands.dddsample.dddsample.v1.domain.InterviewV1;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningStatusV1;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;
import org.littlahands.dddsample.dddsample.v1.domain.dao.InterviewDao;
import org.littlahands.dddsample.dddsample.v1.domain.dao.ScreeningDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ScreeningPreInterviewApplicationServiceV1 {
  @Autowired
  private ScreeningDao screeningDao;
  @Autowired
  private InterviewDao interviewDao;

  public void startFromPreInterviewIllegally(String applicantEmailAddress) {
    String screeningId = UUID.randomUUID().toString();

    ScreeningV1 screening = new ScreeningV1();
    screening.setScreeningId(screeningId);
    screening.setStatus(ScreeningStatusV1.NotApplied);
    screening.setApplyDate(LocalDate.now());
    screening.setApplicantEmailAddress(applicantEmailAddress);
    screeningDao.insert(screening);

    InterviewV1 interview = new InterviewV1();
    interview.setInterviewId(UUID.randomUUID().toString());
    interview.setScreeningId(screeningId);
    interview.setInterviewNumber(1);
    interview.setScreeningDate(LocalDate.now());
    interviewDao.insert(interview);
  }
}
