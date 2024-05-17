package com.example.internalrecruitmentapi.dto;

import java.util.UUID;

public record SubscriptionResponseDTO(UUID id, String userName, String jobTitle) {
}
