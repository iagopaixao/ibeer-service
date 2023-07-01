package com.ipaixao.ibeer.domain.common;

import lombok.Getter;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;

@Getter
@Embeddable
public class DateAudit {

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public DateAudit() {
        beforePersist();
        afterPersist();
    }

    @PrePersist
    private void beforePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PostUpdate
    private void afterPersist() {
        this.updatedAt = LocalDateTime.now();
    }
}