package spring.hackseoul.poll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.hackseoul.poll.db.PollRepository;
import spring.hackseoul.poll.domain.Poll;
import spring.hackseoul.poll.domain.Poll.Condition;

@Service
public class PollService {
    @Autowired
    private PollRepository pollRepository;

    public List<Poll> findAll() {
        return pollRepository.findAll();
    }

    public List<Condition> findConditionsByPollId(long pollId) {
        return pollRepository.findById(pollId)
            .map(Poll::getConditions).orElse(null);
    }

    public Poll findById(long id, String param1, String param2) {
        return pollRepository.findById(id).orElse(null);
    }

    // TODO : zkpass authorization
    private boolean zkPassAuthorization(String param1, String param2) {
        return true;
    }

    @Transactional
    public void save(Poll poll) {
        pollRepository.save(poll);
    }
}