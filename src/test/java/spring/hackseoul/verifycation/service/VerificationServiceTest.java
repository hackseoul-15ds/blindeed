package spring.hackseoul.verifycation.service;

import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.hackseoul.poll.domain.Poll;
import spring.hackseoul.poll.domain.Poll.PollOption;
import spring.hackseoul.poll.service.PollService;
import spring.hackseoul.tag.domain.Tag;
import spring.hackseoul.tag.service.TagService;
import spring.hackseoul.user.domain.User;
import spring.hackseoul.user.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static spring.hackseoul.poll.domain.Poll.*;

@SpringBootTest
class VerificationServiceTest {
    @Autowired
    private VerificationService verificationService;
    @Autowired
    private PollService pollService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;


    @Test
    public void test() {
        // Age Condition Tags
        Tag ageTag1 = Tag.of().setId(1L).setValue("20s").setDisplayText("20s");
        Tag ageTag2 = Tag.of().setId(2L).setValue("30s").setDisplayText("30s");
        Tag ageTag3 = Tag.of().setId(3L).setValue("40s").setDisplayText("40s");
        Tag ageTag4 = Tag.of().setId(4L).setValue("50s").setDisplayText("50s");

        Condition ageCondition = Condition.of()
            .setId(1L)
            .setTitle("Age Condition")
            .setTags(Arrays.asList(ageTag1, ageTag2, ageTag3, ageTag4));

        // Gender Condition Tags
        Tag genderTag1 = Tag.of().setId(5L).setValue("male").setDisplayText("male");
        Tag genderTag2 = Tag.of().setId(6L).setValue("female").setDisplayText("female");

        Condition genderCondition = Condition.of()
            .setId(2L)
            .setTitle("Gender Condition")
            .setTags(Arrays.asList(genderTag1, genderTag2));

        // Salary Condition Tags
        List<Tag> salaryTags = Arrays.asList(
            Tag.of().setId(7L).setValue("1000").setDisplayText("1000"),
            Tag.of().setId(8L).setValue("2000").setDisplayText("2000"),
            Tag.of().setId(9L).setValue("3000").setDisplayText("3000"),
            Tag.of().setId(10L).setValue("4000").setDisplayText("4000"),
            Tag.of().setId(11L).setValue("5000").setDisplayText("5000"),
            Tag.of().setId(12L).setValue("6000").setDisplayText("6000"),
            Tag.of().setId(13L).setValue("7000").setDisplayText("7000"),
            Tag.of().setId(14L).setValue("8000").setDisplayText("8000"),
            Tag.of().setId(15L).setValue("9000").setDisplayText("9000")
        );

        Condition salaryCondition = Condition.of()
            .setId(3L)
            .setTitle("Salary Condition")
            .setTags(salaryTags);

        // Role Condition Tags
        Tag roleTag1 = Tag.of().setId(16L).setValue("dev").setDisplayText("dev");
        Tag roleTag2 = Tag.of().setId(17L).setValue("pm").setDisplayText("pm");
        Tag roleTag3 = Tag.of().setId(18L).setValue("design").setDisplayText("design");

        Condition roleCondition = Condition.of()
            .setId(4L)
            .setTitle("Role Condition")
            .setTags(Arrays.asList(roleTag1, roleTag2, roleTag3));

        // Work Years Condition Tags
        List<Tag> workYearsTags = Arrays.asList(
            Tag.of().setId(19L).setValue("0").setDisplayText("0 years"),
            Tag.of().setId(20L).setValue("1").setDisplayText("1 year"),
            Tag.of().setId(21L).setValue("2").setDisplayText("2 years"),
            Tag.of().setId(22L).setValue("3").setDisplayText("3 years"),
            Tag.of().setId(23L).setValue("4").setDisplayText("4 years"),
            Tag.of().setId(24L).setValue("5").setDisplayText("5 years"),
            Tag.of().setId(25L).setValue("6").setDisplayText("6 years"),
            Tag.of().setId(26L).setValue("7").setDisplayText("7 years"),
            Tag.of().setId(27L).setValue("8").setDisplayText("8 years"),
            Tag.of().setId(28L).setValue("9").setDisplayText("9 years"),
            Tag.of().setId(29L).setValue("10").setDisplayText("10 years"),
            Tag.of().setId(30L).setValue("over_10").setDisplayText("Over 10 years")
        );

        Condition workYearsCondition = Condition.of()
            .setId(5L)
            .setTitle("Work Years Condition")
            .setTags(workYearsTags);

        PollOption pollOption1 = PollOption.of()
            .setId(1L)
            .setTitle("선택 옵션 1")
            .setCount(0);

        Poll poll = Poll.of()
            .setId(2L)
            .setUserId(123L)
            .setTitle("Sample Poll")
            .setContent("This is a sample poll.")
            .setPollOptions(Arrays.asList(pollOption1))
            .setConditions(Arrays.asList(ageCondition, genderCondition, salaryCondition, roleCondition, workYearsCondition));
        pollService.save(poll);

        User tagUser = User.of()
            .setId(1L)
            .setUsername("태그 추가할 인원");
        userService.save(tagUser);

        verificationService.addTagToUser(tagUser.getId(), ageTag1);
    }

}