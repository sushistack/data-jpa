package com.sushistack.datajpa.repository

import com.sushistack.datajpa.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByUsernameAndAgeGreaterThan(username: String, age: Int): List<Member>

    @Query(name = "Member.findByUsername") // => 메소드 명으로 판단하여 없어도 되긴 함.
    fun findByUsername(@Param("username") username: String): List<Member>
}