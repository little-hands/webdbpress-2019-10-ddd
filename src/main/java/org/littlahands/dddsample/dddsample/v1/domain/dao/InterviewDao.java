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

  public List<InterviewV1> findByScreeningId(String screeningId) {
    // select文省略
    return Collections.emptyList();
  }

  public void insert(InterviewV1 interview) {
    // insert文省略
  }

  public void update(InterviewV1 interview) {
    // select文省略
  }
}
