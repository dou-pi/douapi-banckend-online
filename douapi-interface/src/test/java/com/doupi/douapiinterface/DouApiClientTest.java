/*
package com.doupi.douapiinterface;

import com.doupi.douapiclientsdk.client.DouApiClient;
import com.doupi.douapiclientsdk.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.Resource;

*/
/*@SpringBootTest
public class DouapiInterfaceApplicationTest {*//*


*/
/*
    @Test
    void contextLoads() {

        String result = douApiClient.getNameByGet("doupi");
        User user = new User();
        user.setUsername("doupi");
        String usernameByPost = douApiClient.getUsernameByPost(user);
        System.out.println(result);
        System.out.println(usernameByPost);
    }*//*



    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class DouApiClientTest {
        @Resource
        private DouApiClient douApiClient;
        private static final Logger logger = LoggerFactory.getLogger(DouApiClientTest.class);
        private static final String TEST_USERNAME = "doupi"; // 使用常量代替硬编码

        @Test
        public void testGetNameByGet() {
            try {
                String result = douApiClient.getNameByGet(TEST_USERNAME);
                // 使用日志记录代替System.out.println，同时考虑了敏感信息的处理
                logger.info("Result from getNameByGet: {}", result);
                // 添加对返回值的空检查
                if (result == null || result.isEmpty()) {
                    logger.warn("getNameByGet returned null or empty");
                }
            } catch (Exception e) {
                logger.error("Exception caught during getNameByGet", e);
            }
        }

        @Test
        public void testGetUsernameByPost() {
            User user = new User();
            user.setUsername(TEST_USERNAME);
            try {
                String usernameByPost = douApiClient.getUsernameByPost(user);
                logger.info("Result from getUsernameByPost: {}", usernameByPost);
                // 添加对返回值的空检查
                if (usernameByPost == null || usernameByPost.isEmpty()) {
                    logger.warn("getUsernameByPost returned null or empty");
                }
            } catch (Exception e) {
                logger.error("Exception caught during getUsernameByPost", e);
            }
        }
    }


*/
