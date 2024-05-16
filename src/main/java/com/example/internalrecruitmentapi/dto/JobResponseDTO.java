package com.example.internalrecruitmentapi.dto;

import java.util.UUID;

public record JobResponseDTO(UUID id, String title, String description, String skills) {
}
