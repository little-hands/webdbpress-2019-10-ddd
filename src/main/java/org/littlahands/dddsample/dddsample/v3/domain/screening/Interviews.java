package org.littlahands.dddsample.dddsample.v3.domain.screening;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Interviews {
  private List<InterviewV3> interviews;

  Interviews() {
    this.interviews = Collections.emptyList();
  }

  Interviews(List<InterviewV3> interviews) {
    this.interviews = interviews;
  }

  void addNextInterview(LocalDate interviewDate) {
    this.interviews.add(
        new InterviewV3(interviewDate, getNextInterviewNumber())
    );
  }

  private int getNextInterviewNumber() {
    // 面接次数は1からインクリメントされる
    return this.interviews.size() + 1;
  }
}
