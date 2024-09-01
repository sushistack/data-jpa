package com.sushistack.datajpa.repository

import com.sushistack.datajpa.dto.MemberDTO
import com.sushistack.datajpa.dto.UsernameOnlyDTO
import com.sushistack.datajpa.entity.Member
import jakarta.persistence.LockModeType
import jakarta.persistence.QueryHint
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.QueryHints
import org.springframework.data.repository.query.Param

interface MemberRepository : JpaRepository<Member, Long>, MemberRepositoryCustom {
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

    // 카운트 쿼리 분리 가능
    // @Query("SELECT m FROM Member m WHERE m.username = :username", countQuery = "SELECT COUNT(m) FROM Member m")
    fun findByAge(age: Int, pageable: Pageable): Page<Member>

    fun findSliceByAge(age: Int, pageable: Pageable): Slice<Member>

    @Modifying(clearAutomatically = true) // => executeUpdate() 를 실행 한다.
    @Query("UPDATE Member m SET m.age = m.age + 1 WHERE m.age >= :age")
    fun bulkPlusAge(@Param("age") age: Int): Int

    @Query("SELECT m FROM Member m join fetch m.team")
    fun findMemberFetchJoin(): List<Member>

    @EntityGraph(attributePaths = ["team"])
    // @EntityGraph("Member.all") => Named
    fun findAllWithEntityGraphBy(): List<Member>

    @QueryHints(QueryHint(name = "org.hibernate.readOnly", value = "true"))
    fun findMemberReadOnlyByUsername(username: String): List<Member>

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findLockByUsername(username: String): List<Member>

    fun findProjectionByUsername(@Param("username") username: String): List<UsernameOnly>

    fun findProjectionByUsername2(@Param("username") username: String): List<UsernameOnlyDTO>
}