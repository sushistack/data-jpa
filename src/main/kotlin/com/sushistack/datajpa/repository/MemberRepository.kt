package com.sushistack.datajpa.repository

import com.sushistack.datajpa.dto.MemberDTO
import com.sushistack.datajpa.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByUsernameAndAgeGreaterThan(username: String, age: Int): List<Member>

    @Query(name = "Member.findByUsername") // => 메소드 명으로 판단하여 없어도 되긴 함.
    fun findByUsername(@Param("username") username: String): List<Member>

    @Query("SELECT m FROM Member m WHERE m.username = :username AND m.age = :age")
    fun findUser(@Param("username") username: String, @Param("age") age: Int): List<Member>

    @Query("SELECT m.username FROM Member m")
    fun findUsernames(): List<String>

    @Query("SELECT new com.sushistack.datajpa.dto.MemberDTO(m.id, m.username, t.name) FROM Member m join m.team t")
    fun findMemberDTO(): List<MemberDTO>

    @Query("SELECT m FROM Member m WHERE m.username IN :names")
    fun findByNames(@Param("names") names: List<String>): List<Member>

    fun findListByUsername(username: String): List<Member>
    fun findMemberByUsername(username: String): Member?
}