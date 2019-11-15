package org.littlahands.dddsample.dddsample.v1.domain.dao;

import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;

import java.util.Optional;

public interface ScreeningDao {
    Optional<ScreeningV1> findScreeningById(String screeningId);

    void insert(ScreeningV1 screening);

    Optional<ScreeningV1> findScreeningByEmailAddress(String emailAddress);
}
