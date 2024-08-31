package com.sushistack.datajpa.repository

import com.sushistack.datajpa.entity.Member
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional


@Transactional
@SpringBootTest
@Rollback(false)
class MemberJpaRepositoryTest {

    @Autowired lateinit var memberJpaRepository: MemberJpaRepository

    @Test
    @Disabled
    fun testMember() {
        val member = Member(username = "memberA")
        memberJpaRepository.save(member)
        val findMember = memberJpaRepository.find(member.id)

        Assertions.assertThat(findMember?.id).isEqualTo(member.id)
        Assertions.assertThat(findMember?.username).isEqualTo(member.username)
        Assertions.assertThat(findMember).isEqualTo(member)
    }

    @Test
    fun testCRUD() {
        val memberA = Member(username = "memberA")
        val memberB = Member(username = "memberB")
        memberJpaRepository.save(memberA)
        memberJpaRepository.save(memberB)
        val findMemberA = memberJpaRepository.find(memberA.id)
        val findMemberB = memberJpaRepository.find(memberB.id)

        Assertions.assertThat(findMemberA).isEqualTo(memberA)
        Assertions.assertThat(findMemberB).isEqualTo(memberB)

        Assertions.assertThat(memberJpaRepository.findAll().size).isEqualTo(2)
        Assertions.assertThat(memberJpaRepository.count()).isEqualTo(2)

        memberJpaRepository.delete(memberA)
        memberJpaRepository.delete(memberB)
        Assertions.assertThat(memberJpaRepository.count()).isEqualTo(0)
    }

    @Test
    fun findByUsernameAndAgeGreaterThan() {
        val memberA = Member(username = "memberA", age = 10)
        val memberB = Member(username = "memberB", age = 20)
        memberJpaRepository.save(memberA)
        memberJpaRepository.save(memberB)

        val result = memberJpaRepository.findByUsernameAndAgeGreaterThan(username = memberB.username, age = 15)
        Assertions.assertThat(result?.get(0)?.username).isEqualTo(memberB.username)
        Assertions.assertThat(result?.get(0)?.age).isEqualTo(memberB.age)
        Assertions.assertThat(result?.size).isEqualTo(1)
    }

    @Test
    fun testNameQuery() {
        val memberA = Member(username = "memberA", age = 10)
        val memberB = Member(username = "memberB", age = 20)
        memberJpaRepository.save(memberA)
        memberJpaRepository.save(memberB)

        val findMember = memberJpaRepository.findByUsername(memberB.username)
        Assertions.assertThat(findMember?.get(0)?.username).isEqualTo(memberB.username)
    }
}