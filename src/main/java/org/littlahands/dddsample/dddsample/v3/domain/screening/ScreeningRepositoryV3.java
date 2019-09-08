package org.littlahands.dddsample.dddsample.v3.domain.screening;

public interface ScreeningRepositoryV3 {
  ScreeningV3 findById(ScreeningId screeningId);
  void insert(ScreeningV3 screening);
  void update(ScreeningV3 screening);
}
