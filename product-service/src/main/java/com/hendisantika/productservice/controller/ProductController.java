package com.hendisantika.productservice.controller;

import com.hendisantika.productservice.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-gateway-k8s
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/4/23
 * Time: 09:24
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping(value = "${spring.application.name}")
@RequiredArgsConstructor
public class ProductController {

    private final ProductsService productsService;

}
