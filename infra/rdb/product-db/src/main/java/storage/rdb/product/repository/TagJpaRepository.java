package storage.rdb.product.repository;

import app.product.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.rdb.product.repository.adapter.SpringDataTagJpaRepository;

@Repository
@RequiredArgsConstructor
public class TagJpaRepository implements TagRepository {
    private final SpringDataTagJpaRepository repository;
}
