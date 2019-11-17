package org.littlahands.dddsample.dddsample.v1.application_service;

import org.littlahands.dddsample.dddsample.shared.ApplicationException;
import org.littlahands.dddsample.dddsample.v1.domain.EmailAddress;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;
import org.littlahands.dddsample.dddsample.v1.domain.dao.ScreeningRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ScreeningInMemoryRepository implements ScreeningRepository {
  private Map<String, ScreeningV1> data = new HashMap<>();

  @Override
  public Optional<ScreeningV1> findScreeningById(String screeningId) {
    return Optional.ofNullable(data.get(screeningId));
  }

  @Override
  public void insert(ScreeningV1 screening) {
    data.put(screening.getScreeningId(), screening);
  }

  @Override
  public Optional<ScreeningV1> findScreeningByEmailAddress(EmailAddress emailAddress) {
    return data.entrySet().stream()
        .filter(e -> e.getValue().getApplicantEmailAddress().equals(emailAddress))
        .map(Map.Entry::getValue)
        .findAny();
  }
}
