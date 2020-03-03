package com.mumulx;

import com.mumulx.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootDockerClientApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        userService.hello();
    }

}
