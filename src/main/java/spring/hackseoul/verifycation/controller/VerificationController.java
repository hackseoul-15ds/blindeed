package spring.hackseoul.verifycation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import spring.hackseoul.verifycation.domain.VerificationRequest;

@RestController
@RequestMapping("/verification")
public class VerificationController {

    @PostMapping("/{userId}")
    public ResponseEntity<Void> verificationRequest(@PathVariable String userId, @RequestBody VerificationRequest verificationRequest) {
        //TODO: check if tag and schemaId are mapped.
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://52.79.219.227:8000/verify";
        ResponseEntity<Void> result = restTemplate.postForEntity(url, verificationRequest, Void.class);
        return ResponseEntity.status(result.getStatusCode()).build();
    }
}