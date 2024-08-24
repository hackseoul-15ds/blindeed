package spring.hackseoul.poll.domain;

import java.util.List;

import lombok.Data;

@Data
public class Poll {
    private long id;
    private long userId;
    private List<PollOption> poolItems;
    private List<Condition> conditions;

    @Data
    public static class PollOption {
        private long id;
        private String title;
        private int count;
    }

    @Data
    public static class Condition {
        private long id;
        private String title;
    }
}
