package org.seoultech.tableapi.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.seoultech.tableapi.user.dto.UserInfoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/info")
@Tag(name = "User Info Controller", description = "회원 마이페이지 정보 조회 API")
public class UserInfoController {

    @GetMapping
    @Operation(summary = "회원 정보 조회")
    public UserInfoResponse info() {

        UserInfoResponse response = null;

        return response;
    }
}
