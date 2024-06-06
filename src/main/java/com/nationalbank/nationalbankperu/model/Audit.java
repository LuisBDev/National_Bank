package com.nationalbank.nationalbankperu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class Audit {


    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @PrePersist
    public void postPersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void postUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


}
