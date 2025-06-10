package domain.order.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "dead_letter_queue")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) // builder패턴을 위해
public class DeadLetterMessageJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    @Column(name = "`key`")
    private String key;

    @Lob
    private String payload;

    @Column(length = 1000)
    private String reason;

    private int retryCount;

    private LocalDateTime failedAt;

    private LocalDateTime nextRetryAt;

    private boolean processed;
}