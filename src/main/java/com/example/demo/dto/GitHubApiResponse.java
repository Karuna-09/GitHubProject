package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitHubApiResponse(

        String login,

        String name,

        @JsonProperty("public_repos")
        Integer publicRepos,

        Integer followers

) {}
