package com.doupi.douapiinterface;

import com.doupi.douapiclientsdk.client.DouApiClient;
import com.doupi.douapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class DouapiInterfaceApplicationTest {
    @Resource
    private DouApiClient douApiClient;

    @Test
    void contextLoads() {
        String result = douApiClient.getNameByGet("youpi");
        User user = new User();
        user.setUsername("作_者 【程序员_鱼皮】 https://space.bilibili.com/12890453/");
        String usernameByPost = douApiClient.getUsernameByPost(user);
        System.out.println(result);
        System.out.println(usernameByPost);
    }
}
