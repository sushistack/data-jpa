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

    fun findByUsernameAndAgeGreaterThan(username: String, age: Int): List<Member>? =
        em.createQuery("SELECT m FROM Member m WHERE username = :username AND m.age > :age")
            .setParameter("username", username)
            .setParameter("age", age)
            .resultList as List<Member>?

    fun findByUsername(username: String) = em.createNamedQuery("Member.findByUsername", Member::class.java)
        .setParameter("username", username)
        .resultList as List<Member>?

    fun findByPage(age: Int, offset: Int, limit: Int): List<Member> =
        em.createQuery("SELECT m FROM Member m WHERE m.age = :age ORDER BY m.username DESC")
            .setParameter("age", age)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .resultList as List<Member>

    fun totalCount(age: Int) =
        em.createQuery("SELECT COUNT(m) FROM Member m WHERE m.age = :age", Long::class.java)
            .setParameter("age", age)
            .singleResult
}