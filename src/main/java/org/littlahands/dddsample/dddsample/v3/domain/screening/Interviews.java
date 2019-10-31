package org.littlahands.dddsample.dddsample.v3.domain.screening;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Interviews {
  // Interviewの集合を内包する
  private List<InterviewV3> interviews;

  Interviews() {
    // コンストラクタの中でListの初期化を行う
    this.interviews = new ArrayList<>();
  }

  /**
   * 次の面接を追加する
   */
  void addNextInterview(LocalDate interviewDate) {
    this.interviews.add(
        new InterviewV3(interviewDate, getNextInterviewNumber()));
  }

  private int getNextInterviewNumber() {
    // 面接次数は1からインクリメントされる
    return this.interviews.size() + 1;
  }

  /**
   * 再構成用コンストラクタ
   */
  private Interviews(List<InterviewV3> interviews) {
    this.interviews = interviews;
  }

  /**
   * 再構成用メソッド
   */
  static Interviews reconstruct(List<InterviewV3> interviews) {
    return new Interviews(interviews);
  }
}
