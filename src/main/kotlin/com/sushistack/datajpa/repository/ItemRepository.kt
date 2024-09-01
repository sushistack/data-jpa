package com.sushistack.datajpa.repository

import com.sushistack.datajpa.entity.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, Long> {
}