package spring.hackseoul.poll.domain;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors(chain = true)
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private List<PollOption> poolItems;
    private List<Condition> conditions;

    @Data(staticConstructor = "of")
    @Accessors(chain = true)
    public static class PollOption {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String title;
        private int count;
    }

    @Data(staticConstructor = "of")
    @Accessors(chain = true)
    public static class Condition {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String title;
    }
}
