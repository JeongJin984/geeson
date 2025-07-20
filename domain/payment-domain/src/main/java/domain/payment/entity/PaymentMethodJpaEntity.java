package domain.payment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import support.constants.payment.PgProviderCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_methods")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PaymentMethodJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long methodId;

    private Long customerId;
    private String orderId;
    private String type; // CARD, ACCOUNT, MOBILE, etc
    private String cardCode;
    @Enumerated(EnumType.STRING)
    private PgProviderCode provider;
    private String maskedNumber;
    private LocalDate expirationDate;
    private String billingKey;
    private LocalDateTime createdAt;

    public void setPaymentResult(String orderId, String type, String cardCode, PgProviderCode provider, String maskedNumber) {
        this.orderId = orderId;
        this.type = type;
        this.cardCode = cardCode;
        this.provider = provider;
        this.maskedNumber = maskedNumber;
    }

}

