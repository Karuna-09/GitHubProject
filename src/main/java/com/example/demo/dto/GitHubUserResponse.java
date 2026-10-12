package com.example.demo.dto;

public record GitHubUserResponse(
        String username,
        String name,
        Integer publicRepos,
        Integer followers
) {
}