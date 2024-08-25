package spring.hackseoul.poll.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.hackseoul.poll.domain.Poll;
import spring.hackseoul.poll.domain.Poll.PollOption;
import spring.hackseoul.tag.domain.Tag;
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
        // Age Condition Tags
        Tag ageTag1 = Tag.of().setId(1L).setValue("20s").setSchemaId("5408834bba27466aa3d0fa0c874fd513").setDisplayText("20s");
        Tag ageTag2 = Tag.of().setId(2L).setValue("30s").setSchemaId("04987d0ee03540b381407a7f1747074a").setDisplayText("30s");
        Tag ageTag3 = Tag.of().setId(3L).setValue("40s").setSchemaId("4fcf37cc80fb4c10bf3c743395aaad98").setDisplayText("40s");
        Tag ageTag4 = Tag.of().setId(4L).setValue("50s").setSchemaId("e19fe41b042248f4b0eeb67a7b1494bd").setDisplayText("50s");

        Poll.Condition ageCondition = Poll.Condition.of()
            .setId(1L)
            .setTitle("Age Condition")
            .setTags(Arrays.asList(ageTag1, ageTag2, ageTag3, ageTag4));

        // Gender Condition Tags
        Tag genderTag1 = Tag.of().setId(5L).setValue("male").setSchemaId("502d9a650a824cc7a793375ecdb070fb").setDisplayText("male");
        Tag genderTag2 = Tag.of().setId(6L).setValue("female").setSchemaId("5df9d795560241c9a4b3de5a303e965e").setDisplayText("female");

        Poll.Condition genderCondition = Poll.Condition.of()
            .setId(2L)
            .setTitle("Gender Condition")
            .setTags(Arrays.asList(genderTag1, genderTag2));

        // Salary Condition Tags
        List<Tag> salaryTags = Arrays.asList(
            Tag.of().setId(7L).setValue("1000").setSchemaId("076e6b42b82a4cc5af0c0ced2d7b3b17").setDisplayText("1000"),
            Tag.of().setId(8L).setValue("2000").setSchemaId("57b3c7f548b34a20b3b499e5008bc0f2").setDisplayText("2000"),
            Tag.of().setId(9L).setValue("3000").setSchemaId("8315ea7484df47688fc12f6a251d2947").setDisplayText("3000"),
            Tag.of().setId(10L).setValue("4000").setSchemaId("f21f8a9fb3a54343a58de549942953e4").setDisplayText("4000"),
            Tag.of().setId(11L).setValue("5000").setSchemaId("17b4e9417a0140fbbbfd9b143cb9eb02").setDisplayText("5000"),
            Tag.of().setId(12L).setValue("6000").setSchemaId("8f103754e72f4c138ed180edb98b49ea").setDisplayText("6000"),
            Tag.of().setId(13L).setValue("7000").setSchemaId("eb894d8769804901ba54b3c4247a3502").setDisplayText("7000"),
            Tag.of().setId(14L).setValue("8000").setSchemaId("87046cca72f94459aa4cd5f33f1e6ef3").setDisplayText("8000"),
            Tag.of().setId(15L).setValue("9000").setSchemaId("8cca600037d449ed8242ab7c4af7e65e").setDisplayText("9000")
        );

        Poll.Condition salaryCondition = Poll.Condition.of()
            .setId(3L)
            .setTitle("Salary Condition")
            .setTags(salaryTags);

        // Role Condition Tags
        Tag roleTag1 = Tag.of().setId(16L).setValue("dev").setSchemaId("6061e2b549a6484e998342ce5a88a08d").setDisplayText("dev");
        Tag roleTag2 = Tag.of().setId(17L).setValue("pm").setSchemaId("ba45fbbc91b24bfa85ade8fc05798383").setDisplayText("pm");
        Tag roleTag3 = Tag.of().setId(18L).setValue("design").setSchemaId("ed9e3ba51bcb47ff89b18df2c99634ff").setDisplayText("design");

        Poll.Condition roleCondition = Poll.Condition.of()
            .setId(4L)
            .setTitle("Role Condition")
            .setTags(Arrays.asList(roleTag1, roleTag2, roleTag3));

        // Work Years Condition Tags
        List<Tag> workYearsTags = Arrays.asList(
            Tag.of().setId(19L).setValue("0").setSchemaId("57811469ea6e433590acbfc9a09cfa3d").setDisplayText("0 years"),
            Tag.of().setId(20L).setValue("1").setSchemaId("13d3cb71c9ea498fbbe693cb6969f88f").setDisplayText("1 year"),
            Tag.of().setId(21L).setValue("2").setSchemaId("27cccc7f36a24a33bc043b09aab371f8").setDisplayText("2 years"),
            Tag.of().setId(22L).setValue("3").setSchemaId("a0ac371ea8404420ba56ca5eb87a4654").setDisplayText("3 years"),
            Tag.of().setId(23L).setValue("4").setSchemaId("ecf931137cdc448dae300b3f2533e4d5").setDisplayText("4 years"),
            Tag.of().setId(24L).setValue("5").setSchemaId("7b8f918f1da64aa797c1153c099a5440").setDisplayText("5 years"),
            Tag.of().setId(25L).setValue("6").setSchemaId("56307842c97e4303acd313c099f31bd6").setDisplayText("6 years"),
            Tag.of().setId(26L).setValue("7").setSchemaId("af446894363d412880dd83c323633488").setDisplayText("7 years"),
            Tag.of().setId(27L).setValue("8").setSchemaId("1019a7937d754dec9c3ba0a89a4a7488").setDisplayText("8 years"),
            Tag.of().setId(28L).setValue("9").setSchemaId("03d29517ff2f4fbd87ab551fd44fe4e7").setDisplayText("9 years"),
            Tag.of().setId(29L).setValue("10").setSchemaId("832735b5cf2b4e98a4cf75b939481c33").setDisplayText("10 years"),
            Tag.of().setId(30L).setValue("over_10").setSchemaId("d4ba353fce7046b7a2fc780c070bd24c").setDisplayText("Over 10 years")
        );

        Poll.Condition workYearsCondition = Poll.Condition.of()
            .setId(5L)
            .setTitle("Work Years Condition")
            .setTags(workYearsTags);

        PollOption pollOption1 = PollOption.of()
            .setId(1L)
            .setTitle("쇼팽")
            .setCount(0);

        PollOption pollOption2 = PollOption.of()
            .setId(2L)
            .setTitle("리스트")
            .setCount(0);

        Poll poll = Poll.of()
            .setId(1L)
            .setUserId(123L)
            .setTitle("쇼팽 vs 리스트 누가 더 피아노 잘치니??")
            .setContent("제곧내인 부분이고요.")
            .setPollOptions(Arrays.asList(pollOption1, pollOption2))
            .setConditions(Arrays.asList(ageCondition, genderCondition, salaryCondition, roleCondition, workYearsCondition));
        pollService.save(poll);

        User notValidUser = User.of()
            .setId(1L)
            .setUsername("User One")
            .setTags(Arrays.asList(ageTag1, salaryTags.get(1), genderTag1, roleTag1, workYearsTags.get(0)));

        userService.save(notValidUser);

        User validUser = User.of()
            .setId(2L)
            .setUsername("User Two")
            .setTags(Arrays.asList(ageTag2, genderTag2));

        userService.save(validUser);
    }


    @Test
    public void test() {
    }

}