package spring.hackseoul.poll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.hackseoul.poll.db.ConditionRepository;
import spring.hackseoul.poll.domain.Poll;

@Service
public class ConditionService {
    @Autowired
    private ConditionRepository conditionRepository;

    public List<Poll.Condition> findAll() {
        return conditionRepository.findAll();
    }
}
