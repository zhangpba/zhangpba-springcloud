package com.study.user.service.impl;

import com.study.common.entity.User;
import com.study.user.StudyUserApplication;
import com.study.user.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudyUserApplication.class)
class UserServiceImplTest {

    @Autowired
    private IUserService userService;

    @Test
    void getUser() {
        User user = new User();
        assertNotNull(userService.getUser(user));
    }
}