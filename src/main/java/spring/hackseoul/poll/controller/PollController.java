package spring.hackseoul.poll.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.hackseoul.poll.domain.Poll;
import spring.hackseoul.poll.service.PollService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/poll")
@CrossOrigin(origins = "*", methods = {GET, POST, DELETE})
public class PollController {
    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping()
    @CrossOrigin(origins = "*", methods = {GET, POST, DELETE})
    public ResponseEntity<List<Poll>> getAllPoll(@RequestParam("userId") int userId) {
        List<Poll> polls = pollService.findAll(userId);
        if (polls.isEmpty()) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
        return ResponseEntity.ok(polls);
    }

    @GetMapping("/{pollId}")
    @CrossOrigin(origins = "*", methods = {GET, POST, DELETE})
    public ResponseEntity<Poll> getPollById(@PathVariable("pollId") int pollId,
                                            @RequestParam("userId") int userId) {
        Poll poll = pollService.findById(pollId, userId);
        if (poll == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(poll);
    }

    @PostMapping()
    @CrossOrigin(origins = "*", methods = {GET, POST, DELETE})
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        pollService.save(poll);
        return ResponseEntity.ok(poll);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", methods = {GET, POST, DELETE})
    public ResponseEntity<Void> deletePoll(@PathVariable("id") int id) {
        pollService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/vote")
    @CrossOrigin(origins = "*", methods = {GET, POST, DELETE})
    public ResponseEntity<Poll> votePoll(@RequestBody Poll poll, @RequestParam("userId") int userId) {
        pollService.vote(poll, userId);
        return ResponseEntity.ok(poll);
    }
}