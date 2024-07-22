package com.example.githubrepofetcher.dto.fetchresult;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitHubRepository(String name, Owner owner, @JsonProperty("fork") boolean isFork) {
    public record Owner(String login) {}
}
