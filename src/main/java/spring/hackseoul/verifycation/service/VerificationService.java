package spring.hackseoul.verifycation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.hackseoul.tag.domain.Tag;
import spring.hackseoul.tag.service.TagService;
import spring.hackseoul.user.domain.User;
import spring.hackseoul.user.service.UserService;

@Service
public class VerificationService {

    @Autowired
    private UserService userService;

    @Transactional
    public void addTagToUser(long userId, Tag tag) {
        User user = userService.findById(userId);
        user.getTags().add(tag);
    }
}
