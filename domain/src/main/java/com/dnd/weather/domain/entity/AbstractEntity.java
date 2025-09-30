package com.dnd.weather.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class AbstractEntity extends BaseAbstractEntity {

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime modificationDate;

    @Version
    @Column(nullable = false)
    private Long version;
}