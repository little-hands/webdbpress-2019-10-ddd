package org.littlahands.dddsample.dddsample.v1.domain;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class InterviewsTest {
  @Test
  public void getOptional() {

    // when: 初期状態でGetすると
    Interviews interviews = new Interviews();
    Optional<InterviewV1> interviewV1 = interviews.get(0);
    // then: 0番を指定してEmptyになる
    assertThat(interviewV1, is(Optional.empty()));

    // when: 1つ追加してからgetするとすると
    interviews.addNextInterview(LocalDate.now());
    // then: 値が取れる
    assertThat(interviews.get(0).isPresent(), is(true));
  }
}