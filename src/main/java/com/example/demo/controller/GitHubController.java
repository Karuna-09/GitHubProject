package com.example.demo.controller;

import com.example.demo.dto.GitHubUserResponse;
import com.example.demo.service.GitHubUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class GitHubController {

    private final GitHubUserService githubService;

    public GitHubController(GitHubUserService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{username}")
    public GitHubUserResponse getUser(
            @PathVariable String username) {

        return githubService.getUser(username);
    }
}
