package com.example.internalrecruitmentapi.controllers;

import com.example.internalrecruitmentapi.domain.Job;
import com.example.internalrecruitmentapi.dto.JobRequestDTO;
import com.example.internalrecruitmentapi.dto.JobResponseDTO;
import com.example.internalrecruitmentapi.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobRepository repository;

    @PostMapping("/create")
    public ResponseEntity<JobResponseDTO> createJob(@RequestBody JobRequestDTO requestDTO) {
        Job newJob = new Job();
        newJob.setTitle(requestDTO.title());
        newJob.setDescription(requestDTO.description());
        newJob.setSkills(requestDTO.skills());

        Job savedJob = repository.save(newJob);
        JobResponseDTO responseDTO = mapToResponseDTO(savedJob);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JobResponseDTO> updateJob(@PathVariable UUID id, @RequestBody JobRequestDTO requestDTO) {
        Optional<Job> optionalJob = repository.findById(id);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            job.setTitle(requestDTO.title());
            job.setDescription(requestDTO.description());
            job.setSkills(requestDTO.skills());

            Job updatedJob = repository.save(job);
            JobResponseDTO responseDTO = mapToResponseDTO(updatedJob);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable UUID id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<JobResponseDTO>> listAllJobs() {
        List<Job> jobs = repository.findAll();
        List<JobResponseDTO> responseDTOs = jobs.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<JobResponseDTO> findJobById(@PathVariable UUID id) {
        Optional<Job> optionalJob = repository.findById(id);
        return optionalJob.map(job -> ResponseEntity.ok(mapToResponseDTO(job)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private JobResponseDTO mapToResponseDTO(Job job) {
        return new JobResponseDTO(job.getId(), job.getTitle(), job.getDescription(), job.getSkills());
    }
}
