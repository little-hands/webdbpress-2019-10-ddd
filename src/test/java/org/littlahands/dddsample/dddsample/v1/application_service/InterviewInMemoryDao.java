package org.littlahands.dddsample.dddsample.v1.application_service;

import org.littlahands.dddsample.dddsample.v1.domain.InterviewV1;
import org.littlahands.dddsample.dddsample.v1.domain.dao.InterviewDao;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InterviewInMemoryDao implements InterviewDao {
    private Map<String, InterviewV1> data = new HashMap<>();

    @Override
    public Optional<InterviewV1> findById(String interviewId) {
        return Optional.ofNullable(data.get(interviewId));
    }


    @Override
    public List<InterviewV1> findByScreeningId(String screeningId) {
        // select文省略
        return Collections.emptyList();
    }

    @Override
    public void insert(InterviewV1 interview) {
        data.put(interview.getInterviewId(), interview);
    }

    @Override
    public void update(InterviewV1 interview) {
        data.put(interview.getInterviewId(), interview);
    }
}
