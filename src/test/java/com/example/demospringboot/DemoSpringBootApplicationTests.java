package com.example.demospringboot;

import com.example.demospringboot.web.BookingController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;

@SpringBootTest
class DemoSpringBootApplicationTests {

    @Autowired
    @Lazy
    private BookingController bookingController;


    @Test
    void contextLoads() {
        Assertions.assertThat(bookingController).isNotNull();
    }

}
