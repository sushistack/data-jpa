package com.sushistack.datajpa.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.domain.Persistable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
class Item : Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @CreatedDate
    @Column(updatable = false)
    var createdDate: LocalDateTime? = null

    override fun getId(): Long = this.id

    override fun isNew(): Boolean = createdDate == null
}