package com.example.githubrepofetcher.exception;

public class InvalidAcceptHeaderException extends RuntimeException{
    public InvalidAcceptHeaderException(String message) {
        super(message);
    }
}
