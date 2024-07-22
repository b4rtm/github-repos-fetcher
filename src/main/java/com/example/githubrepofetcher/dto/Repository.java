package com.example.githubrepofetcher.dto;

import java.util.List;

public record Repository(String name, String login, List<Branch> branches) {
}
