package spring.hackseoul.poll.service;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.hackseoul.poll.domain.Poll;
import spring.hackseoul.poll.domain.Poll.Condition;
import spring.hackseoul.poll.domain.Poll.ConditionValue;
import spring.hackseoul.poll.domain.Poll.PollOption;
import spring.hackseoul.user.domain.User;
import spring.hackseoul.user.service.UserService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PollServiceTest {

    @Autowired
    private PollService pollService;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void before() {
        ConditionValue conditionValue1 = ConditionValue.of()
            .setId(1L)
            .setValue("20대");

        ConditionValue conditionValue2 = ConditionValue.of()
            .setId(2L)
            .setValue("30대");

        // Create Condition objects
        Condition condition1 = Condition.of()
            .setId(1L)
            .setTitle("나이 조건")
            .setValues(Arrays.asList(conditionValue1, conditionValue2));

        // Create PollOption objects
        PollOption pollOption1 = PollOption.of()
            .setId(1L)
            .setTitle("선택 옵션 1")
            .setCount(0);

        // Create PollOption objects
        PollOption pollOption2 = PollOption.of()
            .setId(2L)
            .setTitle("선택 옵션 2")
            .setCount(0);

        // Create Poll object
        Poll poll = Poll.of()
            .setId(1L)
            .setUserId(123L)
            .setTitle("Sample Poll")
            .setContent("This is a sample poll.")
            .setPollOptions(Arrays.asList(pollOption1, pollOption2))
            .setConditions(Arrays.asList(condition1));

        pollService.save(poll);

        User notValidUser = User.of()
            .setId(1L)
            .setUsername("not validUser");

        userService.save(notValidUser);

        User validUser = User.of()
            .setId(2L)
            .setUsername("validUser");

        userService.save(validUser);
    }


    @Test
    public void test() {
        Poll byId = pollService.findById(1L);
        byId.getPollOptions().forEach(pollOption -> assertThat(pollOption.getCount()).isEqualTo(0));
        // Create Condition objects
        Condition condition1 = Condition.of()
            .setId(1L)
            .setTitle("나이 조건")
            .setValues(Collections.emptyList());

        // Create PollOption objects
        PollOption pollOption2 = PollOption.of()
            .setId(2L)
            .setTitle("선택 옵션 2");

        // Create Poll object
        Poll poll = Poll.of()
            .setId(1L)
            .setTitle("Sample Poll")
            .setContent("This is a sample poll.")
            .setPollOptions(Arrays.asList(pollOption2))
            .setConditions(Arrays.asList(condition1));

        PollOption expected = pollService.findById(1L).getPollOptions().get(1);
        pollService.vote(poll, 2L, "test", "test");
        PollOption real = pollService.findById(1L).getPollOptions().get(1);

        assertThat(expected.getCount()).isLessThan(real.getCount());
        assertThat(expected.getTitle()).isEqualTo("선택 옵션 2");
    }

}