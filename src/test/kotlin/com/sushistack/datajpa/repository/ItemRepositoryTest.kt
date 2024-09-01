package com.sushistack.datajpa.repository

import com.sushistack.datajpa.entity.Item
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ItemRepositoryTest {

    @Autowired lateinit var itemRepository: ItemRepository

    @Test
    fun save() {
        // persist 를 JPA 에서 GeneratedValue 를 이용하여 ID 가 생성 됨
        itemRepository.save(Item())

        // GeneratedValue 가 아니면?
        // 입력된 ID로 select 를 해서 새로운 Entity 인지 확인 한다.
        // merge 를 사용하지 않겠다고 생각하고 개발해야 함
    }
}