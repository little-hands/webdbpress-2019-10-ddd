package org.littlahands.dddsample.dddsample.v2.domain;


public interface ScreeningRepository {
  ScreeningV2 findById(String screeningId);
  void insert(ScreeningV2 screening);
  void update(ScreeningV2 screening);
}
