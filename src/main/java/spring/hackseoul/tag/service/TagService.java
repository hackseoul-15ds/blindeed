package spring.hackseoul.tag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.hackseoul.tag.db.TagRepository;
import spring.hackseoul.tag.domain.Tag;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public Tag findBySchemaId(String schemaId) {
        return tagRepository.findBySchemaId(schemaId);
    }

}