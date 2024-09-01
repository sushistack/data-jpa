package com.sushistack.datajpa.repository

import com.sushistack.datajpa.entity.Member

interface MemberRepositoryCustom {
    fun findMemberCustom(): List<Member>
}