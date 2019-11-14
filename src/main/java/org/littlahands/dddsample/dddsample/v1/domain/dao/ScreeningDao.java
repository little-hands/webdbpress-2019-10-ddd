package org.littlahands.dddsample.dddsample.v1.domain.dao;

import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;
import org.springframework.stereotype.Component;

import java.util.Optional;

public interface ScreeningDao {
    Optional<ScreeningV1> findScreeningById(String screeningId);

    void insert(ScreeningV1 screening);
}
