package com.sushistack.datajpa.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import java.time.LocalDateTime

@MappedSuperclass
open class JpaBaseEntity {

    @Column(updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now()
    var updatedDate: LocalDateTime = LocalDateTime.now()

    @PrePersist
    fun prePersist() {
        createdDate = LocalDateTime.now()
        updatedDate = LocalDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {
        updatedDate = LocalDateTime.now()
    }
}