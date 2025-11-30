package app.backoffice.application;

import app.backoffice.repository.ReviewAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewSelectApp {
    private final ReviewAdminRepository reviewRepository;

    public Long countReview(LocalDateTime regDtSt, LocalDateTime regDtEd) {
        return reviewRepository.countReview(regDtSt, regDtEd);
    }
}
