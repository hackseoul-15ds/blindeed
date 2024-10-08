package spring.hackseoul.verifycation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import spring.hackseoul.tag.domain.Tag;
import spring.hackseoul.tag.service.TagService;
import spring.hackseoul.verifycation.domain.VerificationRequest;
import spring.hackseoul.verifycation.service.VerificationService;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/verification")
@CrossOrigin(origins = "*", methods = {GET, POST, DELETE})
public class VerificationController {
    @Autowired
    private VerificationService verificationService;

    @Autowired
    private TagService tagService;

    @PostMapping("/{userId}")
    @CrossOrigin(origins = "*", methods = {GET, POST, DELETE})
    public ResponseEntity<Void> verificationRequest(@PathVariable long userId, @RequestBody VerificationRequest verificationRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://52.79.219.227:8000/verify";
        ResponseEntity<Void> result = restTemplate.postForEntity(url, verificationRequest, Void.class);

        if (result.getStatusCode().is2xxSuccessful()) {
            Tag tag = tagService.findBySchemaId(verificationRequest.getSchemaId());
            if (tag == null) {
                return ResponseEntity.badRequest().build();
            }

            this.verificationService.addTagToUser(userId, tag);
        }
        return ResponseEntity.status(result.getStatusCode()).build();
    }
}