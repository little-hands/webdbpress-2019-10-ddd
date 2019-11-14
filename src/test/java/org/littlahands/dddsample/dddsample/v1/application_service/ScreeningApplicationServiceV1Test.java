package org.littlahands.dddsample.dddsample.v1.application_service;


import org.junit.Before;
import org.junit.Test;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;
import org.littlahands.dddsample.dddsample.v1.domain.dao.ScreeningDao;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;


public class ScreeningApplicationServiceV1Test {
    private ScreeningApplicationServiceV1 applicationService;

    @Before
    public void setup() {
        applicationService = new ScreeningApplicationServiceV1(new ScreeningImMemoryDao(), new InterviewInMemoryDao());
    }

    @Test
    public void test() {
        assertThat(applicationService, is(notNullValue()));
        assertThat(applicationService.getInterviewDao(), is(notNullValue()));
        assertThat(applicationService.getScreeningDao(), is(notNullValue()));
        ScreeningDao screeningDao = applicationService.getScreeningDao();

        ScreeningV1 screening = new ScreeningV1();
        screening.setScreeningId("aaa");
        screening.setApplicantEmailAddress("a@example.com");
        screeningDao.insert(screening);

        assertThat(screeningDao.findScreeningById("aaa").get().getApplicantEmailAddress(), is("a@example.com"));
    }

}