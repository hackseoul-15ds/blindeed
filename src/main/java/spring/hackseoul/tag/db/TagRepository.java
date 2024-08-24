package spring.hackseoul.tag.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.hackseoul.tag.domain.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findBySchemaId(String schemaId);
}