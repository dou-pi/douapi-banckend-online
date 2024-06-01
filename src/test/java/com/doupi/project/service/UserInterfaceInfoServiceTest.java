package com.doupi.project.service;

import org.junit.jupiter.api.Assertions;
// 自动生成的包不对，要改成这个
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserInterfaceInfoServiceTest {

    @Resource
    private UserInterfaceInfoService UserInterfaceInfoService;
    @Test
    public void invokeCount() {
        boolean b = UserInterfaceInfoService.invokeCount(1L, 1L);
        Assertions.assertTrue(b);
    }
}