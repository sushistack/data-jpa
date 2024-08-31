package com.sushistack.datajpa.repository

import com.sushistack.datajpa.entity.Team
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, Long>