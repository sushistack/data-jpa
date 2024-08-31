package com.sushistack.datajpa.repository

import com.sushistack.datajpa.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>