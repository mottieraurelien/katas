package com.example.h2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class H2InMemoryDbDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

}

