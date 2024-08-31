package com.sushistack.datajpa.entity

import jakarta.persistence.*

@Entity
class Team (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    val id: Long = 0,
    val name: String,
    @OneToMany(mappedBy = "team")
    val members: List<Member> = emptyList(),
) {
}