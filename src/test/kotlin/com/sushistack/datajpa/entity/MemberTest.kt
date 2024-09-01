package com.sushistack.datajpa.entity

import com.sushistack.datajpa.repository.MemberRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import java.lang.Thread.sleep

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

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

    @Test
    fun jpaBaseTest() {
        // given
        val memberA = Member(username = "memberA")
        memberRepository.save(memberA) // @PrePersist

        sleep(1000)
        memberA.username = "memberB"

        entityManager.flush()
        entityManager.clear()

        // when
        val findMember = memberRepository.findById(memberA.id).get()

        // then
        Assertions.assertThat(findMember.createdDate).isNotNull
        Assertions.assertThat(findMember.lastModifiedDate).isNotNull
    }
}