package domain.order.service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import app.order.repository.DeadLetterRepository;
import domain.order.domain.entity.DeadLetterMessageJpaEntity;

@Service
@RequiredArgsConstructor
public class DeadLetterService {
   private final DeadLetterRepository deadLetterRepository;

   public void saveDeadMessage(String topic, String key, String payload, String reason) {
       DeadLetterMessageJpaEntity entity = DeadLetterMessageJpaEntity.builder()
               .topic(topic)
               .key(key)
               .payload(payload)
               .reason(reason)
               .retryCount(0)
               .failedAt(LocalDateTime.now())
               .nextRetryAt(LocalDateTime.now().plusMinutes(10))
               .processed(false)
               .build();
       
       deadLetterRepository.save(entity);
   }
}