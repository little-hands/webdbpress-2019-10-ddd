package org.littlahands.dddsample.dddsample.v1.domain.dao;

import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ScreeningMockDao implements ScreeningDao {

    @Override
    public Optional<ScreeningV1> findScreeningById(String screeningId) {
        // select文省略
        return Optional.ofNullable(new ScreeningV1());
    }

    @Override
    public void insert(ScreeningV1 screening) {
        // insert文省略
    }

    @Override
    public Optional<ScreeningV1> findScreeningByEmailAddress(String emailAddress) {
        return Optional.empty();
    }
}