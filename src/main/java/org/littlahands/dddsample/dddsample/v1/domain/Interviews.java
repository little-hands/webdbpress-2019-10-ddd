package org.littlahands.dddsample.dddsample.v1.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Interviews {
  private List<InterviewV1> interviews;

  Interviews() {
    this.interviews = new ArrayList<>();
  }

  public int size() {
    return interviews.size();
  }

  public Optional<InterviewV1> get(int interviewNumber) {
    if (interviewNumber + 1 > size()) {
      return Optional.empty();
    }
    return Optional.of(interviews.get(interviewNumber));
  }

  void addNextInterview(LocalDate interviewDate) {
    // 面接次数は保存されているインタビューの数+1とする
    InterviewV1 interview = new InterviewV1(interviews.size() + 1, interviewDate);
    interviews.add(interview);
  }
}
