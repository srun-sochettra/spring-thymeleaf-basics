package com.example.basicthymeleaf;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.sql.init.mode=never",
        "spring.jpa.hibernate.ddl-auto=none"
})
class BasicThymeleafApplicationTests {

    @Test
    void contextLoads() {
        // Verifies the Spring ApplicationContext starts successfully
        // with all beans wired correctly
    }
}