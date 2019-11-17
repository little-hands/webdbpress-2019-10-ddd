package org.littlahands.dddsample.dddsample.v1.domain.dao;

import org.littlahands.dddsample.dddsample.v1.domain.EmailAddress;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningId;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;

import java.util.Optional;

public interface ScreeningRepository {
    Optional<ScreeningV1> findScreeningById(ScreeningId screeningId);

    void insert(ScreeningV1 screening);

    Optional<ScreeningV1> findScreeningByEmailAddress(EmailAddress emailAddress);
}
