package com.sushistack.datajpa.controller

import com.sushistack.datajpa.dto.MemberDTO
import com.sushistack.datajpa.entity.Member
import com.sushistack.datajpa.repository.MemberRepository
import jakarta.annotation.PostConstruct
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(private val memberRepository: MemberRepository) {

    @PostConstruct
    fun init() {
        (1..100).forEach {
            Member(username = "member${it}", age = it).let { m -> memberRepository.save(m) }
        }

    }

    @GetMapping("/members/{id}")
    fun findMember(@PathVariable("id") id: Long) = memberRepository.findById(id).get().username

    @GetMapping("/members2/{id}")
    fun findMember2(@PathVariable("id") member: Member) = member.username

    // ?page=0&size=10&sort=username,desc&sort=id,asc
    @GetMapping("/members")
    fun findMembers(@PageableDefault(size = 5, sort = ["username"]) pageable: Pageable): Page<MemberDTO> =
        memberRepository.findAll(pageable)
            .map { MemberDTO(it.id, it.username, it.team?.name ?: "") }
}