package com.hendisantika.productservice.controller;

import com.hendisantika.productservice.model.request.ProductRequest;
import com.hendisantika.productservice.model.response.ProductResponse;
import com.hendisantika.productservice.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "${controller.api.create}")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productsService.create(request));
    }

    @GetMapping(value = "${controller.api.list}")
    public ResponseEntity<Page<ProductResponse>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productsService.list(pageable));
    }
}
