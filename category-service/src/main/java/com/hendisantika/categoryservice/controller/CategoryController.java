package com.hendisantika.categoryservice.controller;

import com.hendisantika.categoryservice.entity.Category;
import com.hendisantika.categoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
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
 * Time: 08:50
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping(value = "${spring.application.name}")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(value = "${controller.api.create}")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.save(category));
    }

    @PostMapping(value = "${controller.api.delete}/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.delete(id));
    }
}
