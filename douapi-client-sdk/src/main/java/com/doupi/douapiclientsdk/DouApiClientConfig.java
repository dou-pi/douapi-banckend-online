package com.doupi.douapiclientsdk;

import com.doupi.douapiclientsdk.client.DouApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * DouApi 客户端配置
 *
 * @author <a href="https://github.com/lidoupi">程序员鱼皮</a>
 * @from <a href="https://doupi.icu">编程导航知识星球</a>
 */
@Configuration
@ConfigurationProperties("douapi.client")
@Data
@ComponentScan
public class DouApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public DouApiClient douApiClient() {
        return new DouApiClient(accessKey, secretKey);
    }

}
