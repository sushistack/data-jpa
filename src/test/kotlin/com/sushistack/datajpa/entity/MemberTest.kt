package com.sushistack.datajpa.entity

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Test
    fun testEntity() {
        val teamA = Team(name = "teamA").also { entityManager.persist(it) }
        val teamB = Team(name = "teamB").also { entityManager.persist(it) }

        val member1 = Member(username = "member1", age = 10, team = teamA).also { entityManager.persist(it) }
        val member2 = Member(username = "member1", age = 20, team = teamA).also { entityManager.persist(it) }
        val member3 = Member(username = "member1", age = 30, team = teamB).also { entityManager.persist(it) }
        val member4 = Member(username = "member1", age = 40, team = teamB).also { entityManager.persist(it) }

        entityManager.flush()
        entityManager.clear()

        entityManager.createQuery("SELECT m FROM Member m ", Member::class.java).resultList.forEach {
            println("member := $it")
            println("member.team := ${it.team}")
        }

    }
}