package org.seoultech.tableapi.user.controller;

import org.seoultech.tableapi.user.dto.JoinRequest;
import org.seoultech.tableapi.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JoinController {
    private final UserService userService;

    public JoinController(UserService memberService) {
        this.userService = memberService;
    }
    @PostMapping("/join")
    public String joinProcess(@RequestBody JoinRequest joinRequest) {
        userService.register(joinRequest);
        return "ok";
    }
}
