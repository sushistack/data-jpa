package com.sushistack.datajpa.repository

import com.sushistack.datajpa.entity.Member
import jakarta.persistence.EntityManager

// 여기 이름은 무조건 {EntityName}Repository + Impl 임
class MemberRepositoryImpl(private val entityManager: EntityManager) : MemberRepositoryCustom {
    override fun findMemberCustom() =
        entityManager.createQuery("SELECT m FROM Member m", Member::class.java)
            .resultList

}