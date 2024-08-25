package spring.hackseoul.poll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.hackseoul.poll.db.ConditionRepository;
import spring.hackseoul.poll.db.PollRepository;
import spring.hackseoul.poll.domain.Poll;
import spring.hackseoul.poll.domain.Poll.Condition;
import spring.hackseoul.user.db.UserRepository;
import spring.hackseoul.user.domain.User;
import spring.hackseoul.user.service.UserService;

@Service
public class PollService {
    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ConditionRepository conditionRepository;

    public List<Poll> findAll() {
        return pollRepository.findAll();
    }

    public Poll findById(long pollId, long userId) {
        Poll poll = pollRepository.findById(pollId).orElse(null);

        assert poll != null;
        if (poll.getUsers().contains(userId)) {
            poll.setVoted(true);
        }

        User byId = userService.findById(userId);
        poll.setConditionConfirmed(checker(poll, byId));

        return poll;
    }

    private boolean checker(Poll poll, User user) {
        return poll.getConditions().stream().allMatch(condition ->
            condition.getTags().stream().anyMatch(user.getTags()::contains)
        );
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
    public void vote(Poll poll, long userId) {
        Poll byId = pollRepository.findById(poll.getId()).orElse(null);

        assert byId != null;

        byId.getPollOptions().forEach(
            pollOption -> {
                if (poll.getPollOptions().stream().anyMatch(pollOption1 -> pollOption1.getId() == pollOption.getId())){
                    pollOption.setCount(pollOption.getCount() + 1);
                }
            }
        );

        byId.getUsers().add(userService.findById(userId));
    }


}