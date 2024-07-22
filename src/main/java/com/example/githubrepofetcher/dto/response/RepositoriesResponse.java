package com.example.githubrepofetcher.dto.response;

import com.example.githubrepofetcher.dto.Repository;

import java.util.List;

public record RepositoriesResponse(List<Repository> repositories) {
}
