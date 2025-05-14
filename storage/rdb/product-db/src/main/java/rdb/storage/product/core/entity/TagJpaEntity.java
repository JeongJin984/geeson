package rdb.storage.product.core.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tags")
public class TagJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    private String name;
}
