package com.example.githubrepofetcher.dto.response;

import com.example.githubrepofetcher.dto.Branch;

import java.util.List;

public record RepositoryResponse(String name, String login, List<Branch> branches) {
}
