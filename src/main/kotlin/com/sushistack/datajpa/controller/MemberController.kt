package com.sushistack.datajpa.controller

import com.sushistack.datajpa.entity.Member
import com.sushistack.datajpa.repository.MemberRepository
import jakarta.annotation.PostConstruct
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(private val memberRepository: MemberRepository) {

    @PostConstruct
    fun init() {
        Member(username = "memberA", age = 10).let { memberRepository.save(it) }
    }

    @GetMapping("/members/{id}")
    fun findMember(@PathVariable("id") id: Long) = memberRepository.findById(id).get().username

    @GetMapping("/members2/{id}")
    fun findMember2(@PathVariable("id") member: Member) = member.username
}