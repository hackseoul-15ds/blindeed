package spring.hackseoul.poll.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.hackseoul.poll.domain.Poll;

import static spring.hackseoul.poll.domain.Poll.*;

@Repository
public interface ConditionValueRepository extends JpaRepository<ConditionValue, Long> {
}
