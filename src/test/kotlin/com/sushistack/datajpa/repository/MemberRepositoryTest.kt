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
class MemberRepositoryTest {

    @Autowired lateinit var memberRepository: MemberRepository

    @Test
    @Disabled
    fun testMember() {
        val member = Member(username = "memberA")
        val savedMember = memberRepository.save(member)

        val findMember = memberRepository.findById(savedMember.id!!).get()
        Assertions.assertThat(findMember.id).isEqualTo(member.id)
        Assertions.assertThat(findMember.username).isEqualTo(member.username)
        Assertions.assertThat(findMember).isEqualTo(member)
    }

    @Test
    fun testCRUD() {
        val memberA = Member(username = "memberA")
        val memberB = Member(username = "memberB")
        memberRepository.save(memberA)
        memberRepository.save(memberB)
        val findMemberA = memberRepository.findById(memberA.id).get()
        val findMemberB = memberRepository.findById(memberB.id).get()

        Assertions.assertThat(findMemberA).isEqualTo(memberA)
        Assertions.assertThat(findMemberB).isEqualTo(memberB)

        Assertions.assertThat(memberRepository.findAll().size).isEqualTo(2)
        Assertions.assertThat(memberRepository.count()).isEqualTo(2)

        memberRepository.delete(memberA)
        memberRepository.delete(memberB)
        Assertions.assertThat(memberRepository.count()).isEqualTo(0)
    }

    @Test
    fun findByUsernameAndAgeGreaterThan() {
        val memberA = Member(username = "memberA", age = 10)
        val memberB = Member(username = "memberB", age = 20)
        memberRepository.save(memberA)
        memberRepository.save(memberB)

        val result = memberRepository.findByUsernameAndAgeGreaterThan(username = memberB.username, age = 15)
        Assertions.assertThat(result.get(0).username).isEqualTo(memberB.username)
        Assertions.assertThat(result.get(0).age).isEqualTo(memberB.age)
        Assertions.assertThat(result.size).isEqualTo(1)
    }

    @Test
    fun testNameQuery() {
        val memberA = Member(username = "memberA", age = 10)
        val memberB = Member(username = "memberB", age = 20)
        memberRepository.save(memberA)
        memberRepository.save(memberB)

        val findMember = memberRepository.findByUsername(memberB.username)
        Assertions.assertThat(findMember.get(0).username).isEqualTo(memberB.username)
    }

    @Test
    fun testQuery() {
        val memberA = Member(username = "memberA", age = 10)
        val memberB = Member(username = "memberB", age = 20)
        memberRepository.save(memberA)
        memberRepository.save(memberB)

        val findMember = memberRepository.findUser(memberB.username, memberB.age)
        Assertions.assertThat(findMember.get(0).username).isEqualTo(memberB.username)
        Assertions.assertThat(findMember.get(0).age).isEqualTo(memberB.age)
    }
}