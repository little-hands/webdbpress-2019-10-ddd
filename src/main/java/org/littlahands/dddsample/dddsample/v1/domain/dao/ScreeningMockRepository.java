package org.littlahands.dddsample.dddsample.v1.domain.dao;

import org.littlahands.dddsample.dddsample.v1.domain.EmailAddress;
import org.littlahands.dddsample.dddsample.v1.domain.ScreeningV1;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ScreeningMockRepository implements ScreeningRepository {

    @Override
    public Optional<ScreeningV1> findScreeningById(String screeningId) {
        // select文省略
        return Optional.empty();
    }

    @Override
    public void insert(ScreeningV1 screening) {
        // insert文省略
    }

    @Override
    public Optional<ScreeningV1> findScreeningByEmailAddress(EmailAddress emailAddress) {
        return Optional.empty();
    }
}
