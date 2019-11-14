package org.littlahands.dddsample.dddsample.v1.domain.dao;

import org.littlahands.dddsample.dddsample.v1.domain.InterviewV1;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface InterviewDao {
    Optional<InterviewV1> findById(String interviewId);

    List<InterviewV1> findByScreeningId(String screeningId);

    void insert(InterviewV1 interview);

    void update(InterviewV1 interview);
}
