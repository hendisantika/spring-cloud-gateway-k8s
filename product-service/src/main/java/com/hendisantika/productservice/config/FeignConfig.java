package com.hendisantika.productservice.config;

import com.hendisantika.productservice.exception.FeignCustomException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-gateway-k8s
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/4/23
 * Time: 09:17
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Configuration
public class FeignConfig {

    @Bean
    ErrorDecoder errorDecoder() {
        log.info("initialize bean error decoder feign client..");
        return new FeignCustomException();
    }
}
