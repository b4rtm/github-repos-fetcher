package com.example.githubrepofetcher.controller;

import com.example.githubrepofetcher.dto.response.RepositoryResponse;
import com.example.githubrepofetcher.exception.InvalidAcceptHeaderException;
import com.example.githubrepofetcher.service.GitHubService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/github")
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/users/{username}/repos")
    public ResponseEntity<List<RepositoryResponse>> getUserRepositories(@PathVariable String username, @RequestHeader(HttpHeaders.ACCEPT) String acceptHeader) {
        if (!MediaType.APPLICATION_JSON_VALUE.equals(acceptHeader)) {
            throw new InvalidAcceptHeaderException("Accept header must be application/json");
        }
        return ResponseEntity.ok(gitHubService.getUserRepositories(username));
    }
}
