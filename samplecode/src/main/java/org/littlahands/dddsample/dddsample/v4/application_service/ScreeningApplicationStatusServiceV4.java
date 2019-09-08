package org.littlahands.dddsample.dddsample.v4.application_service;

import org.littlahands.dddsample.dddsample.v4.domain.ApplicationException;
import org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningIdV4;
import org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningJdbcRepositoryV4;
import org.littlahands.dddsample.dddsample.v4.domain.screening.ScreeningV4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScreeningApplicationStatusServiceV4 {
  @Autowired
  private ScreeningJdbcRepositoryV4 screeningRepository;


  /**
   * 次のステップに進みます
   */
  public void stepToNext(String screeningId) throws ApplicationException {
    ScreeningV4 screening = screeningRepository.findById(new ScreeningIdV4(screeningId));
    screening.stepToNext();
    screeningRepository.update(screening);
  }


}
