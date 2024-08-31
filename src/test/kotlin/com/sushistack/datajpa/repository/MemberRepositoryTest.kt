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
}