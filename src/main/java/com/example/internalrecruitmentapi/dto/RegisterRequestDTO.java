package com.example.internalrecruitmentapi.dto;

public record RegisterRequestDTO (String name, String email, Boolean admin, String password) {
}
