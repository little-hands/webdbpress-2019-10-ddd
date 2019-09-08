package org.littlahands.dddsample.dddsample.v1.application_service;

import org.littlahands.dddsample.dddsample.v1.domain.ApplicationException;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningStatusV1;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;
import org.littlahands.dddsample.dddsample.v1.domain.dao.InterviewDao;
import org.littlahands.dddsample.dddsample.v1.domain.dao.ScreeningDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScreeningInterviewStatusApplicationServiceV1 {
  @Autowired
  private ScreeningDao screeningDao;
  @Autowired
  private InterviewDao interviewDao;

  /**
   * 面接ステータスを更新します。
   */
  public void updateStatus(String screeningId, ScreeningStatusV1 screeningStatus)
      throws ApplicationException {
    ScreeningV1 screening = screeningDao.findScreeningById(screeningId);
    if (!isPermittedStatus(screening.getStatus(), screeningStatus)) {
      throw new ApplicationException("Invalid status.");
    }

    screening.setStatus(screeningStatus);
    screeningDao.insert(screening);
  }

  private boolean isPermittedStatus(ScreeningStatusV1 beforeStatus, ScreeningStatusV1 afterStatus) {
    return this.permittedStatuses.get(beforeStatus).contains(afterStatus);
  }

  private Map<ScreeningStatusV1, List<ScreeningStatusV1>> permittedStatuses;

//  public ScreeningInterviewStatusApplicationServiceV1() {
//    this.permittedStatuses = new HashMap<>();
//    this.permittedStatuses.put(
//        NotApplied, Arrays.asList(DocumentScreening));
//    this.permittedStatuses.put(
//        DocumentScreening, Arrays.asList(Interview, DocumentScreeningRejected, DocumentScreeningDeclined));
//    this.permittedStatuses.put(
//        Interview, Arrays.asList(DocumentScreening, Passed, InterviewRejected, InterviewDeclined)); // Passedに変わった
//    this.permittedStatuses.put(
//        Passed, Arrays.asList(Interview, Offered)); // これが増えた
//    // :
//  }


}
