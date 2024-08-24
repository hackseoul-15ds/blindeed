package spring.hackseoul.poll.service;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.hackseoul.poll.domain.Poll;
import spring.hackseoul.poll.domain.Poll.Condition;
import spring.hackseoul.poll.domain.Poll.ConditionValue;
import spring.hackseoul.poll.domain.Poll.PollOption;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PollServiceTest {

    @Autowired
    private PollService pollService;

    @Test
    public void test() {
        // Create ConditionValue objects
        ConditionValue conditionValue1 = ConditionValue.of()
            .setId(1L)
            .setValue("Value 1")
            .setCount(10L);

        ConditionValue conditionValue2 = ConditionValue.of()
            .setId(2L)
            .setValue("Value 2")
            .setCount(20L);

        // Create Condition objects
        Condition condition1 = Condition.of()
            .setId(1L)
            .setTitle("Condition 1")
            .setValues(Arrays.asList(conditionValue1, conditionValue2));

        // Create PollOption objects
        PollOption pollOption1 = PollOption.of()
            .setId(1L)
            .setTitle("Option 1")
            .setConditions(Arrays.asList(condition1));

        // Create Poll object
        Poll poll = Poll.of()
            .setId(1L)
            .setUserId(123L)
            .setTitle("Sample Poll")
            .setContent("This is a sample poll.")
            .setPoolItems(Arrays.asList(pollOption1))
            .setConditions(Arrays.asList(condition1));

        pollService.save(poll);

        Poll byId = pollService.findById(1L, "param1", "param2");
        byId.getPoolItems().forEach(pollOption -> System.out.println(pollOption.getTitle()));
        byId.getConditions().forEach(condition -> System.out.println(condition.getTitle()));
    }

}