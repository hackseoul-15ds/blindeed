package spring.hackseoul.poll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.hackseoul.poll.db.PollRepository;
import spring.hackseoul.poll.domain.Poll;
import spring.hackseoul.poll.domain.Poll.Condition;

import static java.lang.Long.*;

@Service
public class PollService {
    @Autowired
    private PollRepository pollRepository;

    public List<Poll> findAll() {
        return pollRepository.findAll();
    }

    public Poll findById(long id) {
        return pollRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Poll poll) {
        pollRepository.save(poll);
    }

    @Transactional
    public void deleteById(long id) {
        pollRepository.deleteById(id);
    }

    public List<Condition> findConditionsByPollId(long pollId) {
        return pollRepository.findById(pollId)
                .map(Poll::getConditions).orElse(null);
    }

    @Transactional
    public void vote(Poll poll, String userId, String param1, String param2) {
        if (!zkPassAuthorization(param1, param2)) {
            throw new RuntimeException("Authorization failed");
        }

        Poll byId = pollRepository.findById(poll.getId()).orElse(null);

        assert byId != null;

        byId.getPoolItems().forEach(
            pollOption -> {
                if (poll.getPoolItems().stream().anyMatch(pollOption1 -> pollOption1.getId() == pollOption.getId())){
                    pollOption.setCount(pollOption.getCount() + 1);
                }
            }
        );
    }

    // TODO : zkpass authorization
    private boolean zkPassAuthorization(String param1, String param2) {
        return true;
    }
}