package com.example.demo.service;

import com.example.demo.dto.GitHubApiResponse;
import com.example.demo.dto.GitHubUserResponse;
import com.example.demo.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class GitHubUserService {

    private final RestClient restClient;

    public GitHubUserService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.github.com")
                .build();
    }

    public GitHubUserResponse getUser(String username) {

        log.info("Fetching GitHub user: {}", username);

        GitHubApiResponse githubResponse =
                restClient.get()
                        .uri("/users/{username}", username)
                        .retrieve()
                        .onStatus(
                                HttpStatusCode::is4xxClientError,
                                (request, response) -> {
                                    throw new UserNotFoundException(
                                            "GitHub user not found: " + username
                                    );
                                }
                        )
                        .body(GitHubApiResponse.class);

        return new GitHubUserResponse(
                githubResponse.login(),
                githubResponse.name(),
                githubResponse.publicRepos(),
                githubResponse.followers()
        );
    }
}
