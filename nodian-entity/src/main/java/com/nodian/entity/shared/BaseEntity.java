package com.nodian.entity.shared;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

import java.time.Instant;

@Data
@NoArgsConstructor
@MappedSuperclass
@Lazy(value = false)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private Long createdAt = Instant.now().toEpochMilli();

    @Column(name = "updated_at")
    private Long updatedDate = Instant.now().toEpochMilli();

    @Column(name = "is_active")
    private Boolean isActive = true;
}