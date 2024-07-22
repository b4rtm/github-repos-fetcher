package com.example.githubrepofetcher.service;

import com.example.githubrepofetcher.dto.Branch;
import com.example.githubrepofetcher.dto.fetchresult.GitHubBranch;
import com.example.githubrepofetcher.dto.fetchresult.GitHubRepository;
import com.example.githubrepofetcher.dto.Repository;
import com.example.githubrepofetcher.dto.response.RepositoriesResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitHubService {

    private final RestTemplate restTemplate;

    private static final String GITHUB_API_URL = "https://api.github.com";

    public GitHubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public RepositoriesResponse getUserRepositories(String username) {
        List<GitHubRepository> repos = fetchRepositories(username);
        return new RepositoriesResponse(mapToRepositoryResponseList(repos));
    }

    private List<Repository> mapToRepositoryResponseList(List<GitHubRepository> repos) {
        return repos.stream().filter(repo -> !repo.isFork()).map(repo -> {
            List<GitHubBranch> branches = fetchBranches(repo.owner().login(), repo.name());
            List<Branch> branchList = branches.stream().map(branch -> new Branch(branch.name(), branch.commit().sha())).collect(Collectors.toList());
            return new Repository(repo.name(), repo.owner().login(), branchList);
        }).collect(Collectors.toList());
    }

    private List<GitHubRepository> fetchRepositories(String username) {
        String url = GITHUB_API_URL + "/users/" + username + "/repos";
        GitHubRepository[] repos = restTemplate.getForObject(url, GitHubRepository[].class);
        return repos != null ? Arrays.asList(repos) : Collections.emptyList();
    }

    private List<GitHubBranch> fetchBranches(String owner, String repoName) {
        String url = GITHUB_API_URL + "/repos/" + owner + "/" + repoName + "/branches";
        GitHubBranch[] branches = restTemplate.getForObject(url, GitHubBranch[].class);
        return branches != null ? Arrays.asList(branches) : Collections.emptyList();
    }

}