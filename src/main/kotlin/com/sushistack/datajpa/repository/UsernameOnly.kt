package com.sushistack.datajpa.repository

import org.springframework.beans.factory.annotation.Value

interface UsernameOnly {
    @Value("#{target.username + ' ' + traget.age}")
    fun getUsername(): String
}