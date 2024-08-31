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

    @Test
    fun testPaging() {
        listOf(
            Member(username = "member1", age = 10),
            Member(username = "member2", age = 10),
            Member(username = "member3", age = 10),
            Member(username = "member4", age = 10),
            Member(username = "member5", age = 10),
            Member(username = "member6", age = 10),
            Member(username = "member7", age = 10)
        ).forEach { memberJpaRepository.save(it) }

        val age = 10
        val offset = 0
        val limit = 3

        val totalCount = memberJpaRepository.totalCount(age)
        val members = memberJpaRepository.findByPage(age, offset, limit)

        Assertions.assertThat(members.size).isEqualTo(3)
        Assertions.assertThat(totalCount).isEqualTo(7)

    }

    @Test
    fun bulkUpdate() {
        listOf(
            Member(username = "member1", age = 10),
            Member(username = "member2", age = 20),
            Member(username = "member3", age = 30),
            Member(username = "member4", age = 40),
            Member(username = "member5", age = 50),
            Member(username = "member6", age = 60),
            Member(username = "member7", age = 70)
        ).forEach { memberJpaRepository.save(it) }

        val updatedCount = memberJpaRepository.bulkPlusAge(30)
        Assertions.assertThat(updatedCount).isEqualTo(5)
    }
}