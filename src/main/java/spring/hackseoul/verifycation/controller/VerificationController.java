package spring.hackseoul.verifycation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import spring.hackseoul.tag.domain.Tag;
import spring.hackseoul.tag.service.TagService;
import spring.hackseoul.user.domain.User;
import spring.hackseoul.user.service.UserService;
import spring.hackseoul.verifycation.domain.VerificationRequest;

@RestController
@RequestMapping("/verification")
public class VerificationController {
    private final TagService tagService;
    private final UserService userService;

    public VerificationController(TagService tagService, UserService userService) {
        this.tagService = tagService;
        this.userService = userService;
    }

    @PostMapping("/{username}")
    public ResponseEntity<Void> verificationRequest(@PathVariable String username, @RequestBody VerificationRequest verificationRequest) {
        Tag tag = tagService.findBySchemaId(verificationRequest.getSchemaId());
        if (tag == null) {
            return ResponseEntity.badRequest().build();
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://52.79.219.227:8000/verify";
        ResponseEntity<Void> result = restTemplate.postForEntity(url, verificationRequest, Void.class);

        if (result.getStatusCode().is2xxSuccessful()) {
            User user = userService.findByUsername(username);
            user.getTags().add(tag);
        }
        return ResponseEntity.status(result.getStatusCode()).build();
    }
}