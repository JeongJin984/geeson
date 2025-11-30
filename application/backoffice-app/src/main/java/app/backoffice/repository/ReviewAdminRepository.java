package app.backoffice.repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReviewAdminRepository {
    Long countReview(LocalDateTime regDtSt, LocalDateTime regDtEd);
}
