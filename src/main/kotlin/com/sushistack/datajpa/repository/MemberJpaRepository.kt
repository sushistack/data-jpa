package com.sushistack.datajpa.repository

import com.sushistack.datajpa.entity.Member
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class MemberJpaRepository {

    @PersistenceContext
    lateinit var em: EntityManager

    fun save(member: Member) = member.also { em.persist(it) }

    fun find(id: Long): Member? = em.find(Member::class.java, id)

    fun delete(member: Member) = em.remove(member)

    fun findAll() = em.createQuery("SELECT m FROM Member m", Member::class.java).resultList

    fun count() = em.createQuery("SELECT COUNT(m) FROM Member m", Long::class.java).singleResult

}