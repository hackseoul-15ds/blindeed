package spring.hackseoul.poll.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.hackseoul.poll.domain.Poll;
import spring.hackseoul.poll.service.PollService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/poll")
public class PollController {
    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping()
    public ResponseEntity<List<Poll>> getAllPoll() {
        List<Poll> polls = pollService.findAll();
        if (polls.isEmpty()) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
        return ResponseEntity.ok(polls);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPollById(@PathVariable("id") int id) {
        Poll poll = pollService.findById(id);
        if (poll == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(poll);
    }

    @PostMapping("/save")
    public ResponseEntity<Poll> savePoll(@RequestBody Poll poll) {
        pollService.save(poll);
        return ResponseEntity.ok(poll);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable("id") int id) {
        pollService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}