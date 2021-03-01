package com.arino;

import com.arino.pojo.User;
import com.arino.service.PermissionService;
import com.arino.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
@Configuration
@Slf4j
class ShopApplicationTests {

    @Autowired
    PermissionService permissionService;

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        for (User user : userService.getUsersLimit(0, 2, "")) {
            System.out.println(user);
        }
    }

}
