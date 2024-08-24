package spring.hackseoul.poll.service;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.hackseoul.poll.domain.Poll;
import spring.hackseoul.poll.domain.Poll.Condition;
import spring.hackseoul.poll.domain.Poll.ConditionValue;
import spring.hackseoul.poll.domain.Poll.PollOption;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PollServiceTest {

    @Autowired
    private PollService pollService;

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
            .setPoolItems(Arrays.asList(pollOption1, pollOption2))
            .setConditions(Arrays.asList(condition1));

        pollService.save(poll);
    }


    @Test
    public void test() {
        pollService.findById(1L);
    }

}