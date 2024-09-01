package com.sushistack.datajpa

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import java.util.Optional
import java.util.UUID

@Configuration
class AuditingConfig {
    @Bean
    fun auditorProvider(): AuditorAware<String> {
        return AuditorAware { Optional.ofNullable(UUID.randomUUID().toString()) }
    }
}