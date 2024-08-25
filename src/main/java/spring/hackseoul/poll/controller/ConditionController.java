package spring.hackseoul.poll.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import spring.hackseoul.poll.domain.Poll;
import spring.hackseoul.poll.service.ConditionService;
import spring.hackseoul.poll.service.PollService;
import spring.hackseoul.user.domain.User;
import spring.hackseoul.user.service.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
@RequestMapping("/condition")
@CrossOrigin(origins = "*", methods = {GET, POST, DELETE})
public class ConditionController {
    @Autowired
    private ConditionService conditionService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public Map<String, Object> getAllCondition(@RequestParam("userId") int userId) {
        Map<String, Object> map = new HashMap<>();
        List<Poll.Condition> conditions = conditionService.findAll();
        map.put("conditions", conditions);

        User byId = userService.findById(userId);
        map.put("user", byId);
        return map;
    }
}
