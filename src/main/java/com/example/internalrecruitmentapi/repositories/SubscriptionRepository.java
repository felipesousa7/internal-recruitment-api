package com.example.internalrecruitmentapi.repositories;

import com.example.internalrecruitmentapi.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    Optional<Subscription> findById(UUID id);

    Subscription save(Subscription subscription);

    void deleteById(UUID id);

    List<Subscription> findAll();
}
