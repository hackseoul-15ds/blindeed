package spring.hackseoul.poll.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import spring.hackseoul.user.domain.User;

@Data(staticConstructor = "of")
@Accessors(chain = true)
@Entity
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private String title;
    private String content;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PollOption> pollOptions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Condition> conditions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "poll_user",
        joinColumns = @JoinColumn(name = "poll_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    public Poll() {
    }

    @Data(staticConstructor = "of")
    @Accessors(chain = true)
    @Entity
    public static class PollOption {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String title;
        private int count;

        public PollOption() {
        }
    }

    @Data(staticConstructor = "of")
    @Accessors(chain = true)
    @Entity
    public static class Condition {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String title;

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
        private List<ConditionValue> values;

        public Condition() {
        }
    }

    @Data(staticConstructor = "of")
    @Accessors(chain = true)
    @Entity
    public static class ConditionValue {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String value;

        public ConditionValue() {
        }
    }
}