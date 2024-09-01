package com.sushistack.datajpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class DataJpaApplication

fun main(args: Array<String>) {
    runApplication<DataJpaApplication>(*args)
}
