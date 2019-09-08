package org.littlahands.dddsample.dddsample.v1.domain.dao;

import org.littlahands.dddsample.dddsample.v1.domain.InterviewV1;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class InterviewDao {
  public InterviewV1 findById(Long interviewId) {
    return new InterviewV1();
  }

  public void insert(InterviewV1 interview) {
  }

  public void update(InterviewV1 interview) {

  }

  public List<InterviewV1> findByScreeningId(String screeningId) {
    return Collections.emptyList();
  }
}
