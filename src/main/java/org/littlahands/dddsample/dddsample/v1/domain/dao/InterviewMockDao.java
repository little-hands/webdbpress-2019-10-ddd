package org.littlahands.dddsample.dddsample.v1.domain.dao;

import org.littlahands.dddsample.dddsample.v1.domain.InterviewV1;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class InterviewMockDao implements InterviewDao {

    @Override
    public Optional<InterviewV1> findById(String interviewId) {
        return Optional.of(new InterviewV1());
    }

    @Override
    public List<InterviewV1> findByScreeningId(String screeningId) {
        // select文省略
        return Collections.emptyList();
    }

    @Override
    public void insert(InterviewV1 interview) {
        // insert文省略
    }

    @Override
    public void update(InterviewV1 interview) {
        // select文省略
    }
}
