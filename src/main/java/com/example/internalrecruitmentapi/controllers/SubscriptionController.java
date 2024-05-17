package com.example.internalrecruitmentapi.controllers;

import com.example.internalrecruitmentapi.domain.Subscription;
import com.example.internalrecruitmentapi.dto.SubscriptionRequestDTO;
import com.example.internalrecruitmentapi.dto.SubscriptionResponseDTO;
import com.example.internalrecruitmentapi.repositories.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionRepository repository;

    @PostMapping("/create")
    public ResponseEntity<SubscriptionResponseDTO> createSubscription(@RequestBody SubscriptionRequestDTO requestDTO) {
        Subscription newSubscription = new Subscription();
        newSubscription.setUserName(requestDTO.userName());
        newSubscription.setJobTitle(requestDTO.jobTitle());

        Subscription savedSubscription = repository.save(newSubscription);
        SubscriptionResponseDTO responseDTO = mapToResponseDTO(savedSubscription);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SubscriptionResponseDTO> updateSubscription(@PathVariable UUID id, @RequestBody SubscriptionRequestDTO requestDTO) {
        Optional<Subscription> optionalSubscription = repository.findById(id);
        if (optionalSubscription.isPresent()) {
            Subscription subscription = optionalSubscription.get();
            subscription.setUserName(requestDTO.userName());
            subscription.setJobTitle(requestDTO.jobTitle());

            Subscription updatedSubscription = repository.save(subscription);
            SubscriptionResponseDTO responseDTO = mapToResponseDTO(updatedSubscription);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable UUID id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<SubscriptionResponseDTO>> listAllSubscriptions() {
        List<Subscription> subscriptions = repository.findAll();
        List<SubscriptionResponseDTO> responseDTOs = subscriptions.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<SubscriptionResponseDTO> findSubscriptionById(@PathVariable UUID id) {
        Optional<Subscription> optionalSubscription = repository.findById(id);
        return optionalSubscription.map(subscription -> ResponseEntity.ok(mapToResponseDTO(subscription)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private SubscriptionResponseDTO mapToResponseDTO(Subscription subscription) {
        return new SubscriptionResponseDTO(subscription.getId(), subscription.getUserName(), subscription.getJobTitle());
    }
}
