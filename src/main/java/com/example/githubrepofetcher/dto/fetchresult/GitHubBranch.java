package com.example.githubrepofetcher.dto.fetchresult;

public record GitHubBranch(String name, Commit commit) {
    public record Commit(String sha) {}
}
