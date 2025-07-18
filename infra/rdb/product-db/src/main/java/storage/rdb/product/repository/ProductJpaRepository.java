package storage.rdb.product.repository;

import domain.product.domain.entity.ProductJpaEntity;
import domain.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import storage.rdb.product.repository.adapter.SpringDataProductJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class ProductJpaRepository implements ProductRepository {
    private final SpringDataProductJpaRepository repository;

    @Override
    public ProductJpaEntity findBySku(String sku) {
        return repository.findBySku(sku);
    }

    @Override
    public <S extends ProductJpaEntity> S save(S entity) {
        return repository.save(entity);
    }

    @Override
    public <S extends ProductJpaEntity> List<S> saveAll(Iterable<S> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public Optional<ProductJpaEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<ProductJpaEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductJpaEntity> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(ProductJpaEntity entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        repository.deleteAllById(ids);
    }

    @Override
    public void deleteAll(Iterable<? extends ProductJpaEntity> entities) {
        repository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<ProductJpaEntity> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<ProductJpaEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public <S extends ProductJpaEntity> Optional<S> findOne(Example<S> example) {
        return repository.findOne(example);
    }

    @Override
    public <S extends ProductJpaEntity> List<S> findAll(Example<S> example) {
        return repository.findAll(example);
    }

    @Override
    public <S extends ProductJpaEntity> List<S> findAll(Example<S> example, Sort sort) {
        return repository.findAll(example, sort);
    }

    @Override
    public <S extends ProductJpaEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return repository.findAll(example, pageable);
    }

    @Override
    public <S extends ProductJpaEntity> long count(Example<S> example) {
        return repository.count(example);
    }

    @Override
    public <S extends ProductJpaEntity> boolean exists(Example<S> example) {
        return repository.exists(example);
    }

    @Override
    public <S extends ProductJpaEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return repository.findBy(example, queryFunction);
    }

    @Override
    public void flush() {
        repository.flush();
    }

    @Override
    public <S extends ProductJpaEntity> S saveAndFlush(S entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public <S extends ProductJpaEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return repository.saveAllAndFlush(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<ProductJpaEntity> entities) {
        repository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        repository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void deleteAllInBatch() {
        repository.deleteAllInBatch();
    }

    @Override
    public ProductJpaEntity getOne(Long id) {
        return repository.getOne(id);
    }

    @Override
    public ProductJpaEntity getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public ProductJpaEntity getReferenceById(Long id) {
        return repository.getReferenceById(id);
    }
}