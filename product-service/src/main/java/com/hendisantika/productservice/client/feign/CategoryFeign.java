package com.hendisantika.productservice.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-gateway-k8s
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/4/23
 * Time: 09:18
 * To change this template use File | Settings | File Templates.
 */
@FeignClient(
        url = "${feignClient.category.host}",
        name = "${feignClient.category.host}",
        contextId = "${feignClient.category.context}"
)
public interface CategoryFeign {

    @GetMapping(value = "${feignClient.category.endpoint}")
    ResponseEntity<String> getCategory(@PathVariable("id") Long id);

}
