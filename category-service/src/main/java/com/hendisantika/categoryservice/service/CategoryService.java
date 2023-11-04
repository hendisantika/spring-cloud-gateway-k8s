package com.hendisantika.categoryservice.service;

import com.hendisantika.categoryservice.entity.Category;
import com.hendisantika.categoryservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-gateway-k8s
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/4/23
 * Time: 08:45
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category save(Category category) {
        log.info("request category = {} ", category);
        category.setDeleted(0);
        category.setCreatedAt(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    public Category get(Long id) {
        log.info("request get category by id = {}", id);
        return categoryRepository.findById(id)
                .map(data -> Category.builder()
                        .id(data.getId())
                        .name(data.getName())
                        .createdAt(data.getCreatedAt())
                        .updatedAt(data.getUpdatedAt())
                        .deleted(data.getDeleted())
                        .build())
                .orElseThrow(() -> new MessageNotFoundException("category id not found"));
    }

    public Category delete(Long id) {
        log.info("request delete category = {}", id);
        return categoryRepository.findById(id)
                .map(data -> {
                    data.setDeleted(1);
                    return save(data);
                }).orElseThrow(() -> new MessageNotFoundException("failed delete because id not found"));
    }

}
