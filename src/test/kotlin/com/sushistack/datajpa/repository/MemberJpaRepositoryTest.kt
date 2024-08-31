package com.sushistack.datajpa.repository

import com.sushistack.datajpa.entity.Member
import org.assertj.core.api.Assertions
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
    fun testMember() {
        val member = Member("memberA")
        memberJpaRepository.save(member)
        val findMember = memberJpaRepository.find(member.id)

        Assertions.assertThat(findMember?.id).isEqualTo(member.id)
        Assertions.assertThat(findMember?.username).isEqualTo(member.username)
        Assertions.assertThat(findMember).isEqualTo(member)
    }

}