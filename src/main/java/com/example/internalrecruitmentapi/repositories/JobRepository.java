package com.example.internalrecruitmentapi.repositories;

import com.example.internalrecruitmentapi.domain.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {
    Optional<Job> findById(UUID id);

    Job save(Job job);

    void deleteById(UUID id);

    List<Job> findAll();
}
