package com.sushistack.datajpa.repository

import com.sushistack.datajpa.entity.Team
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class TeamJpaRepository {
    @PersistenceContext
    lateinit var em: EntityManager

    fun save(team: Team) = team.also { em.persist(team) }

    fun delete(team: Team) = em.remove(team)

    fun findAll() = em.createQuery("SELECT t FROM Team t", Team::class.java).resultList

    fun find(id: Long) = em.find(Team::class.java, id)

    fun count() = em.createQuery("SELECT COUNT(t) FROM Team t", Long::class.java).singleResult
}