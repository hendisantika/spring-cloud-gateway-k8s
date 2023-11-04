package com.hendisantika.productservice.service;

import com.alibaba.fastjson.JSON;
import com.hendisantika.productservice.client.service.CategoryService;
import com.hendisantika.productservice.entity.Products;
import com.hendisantika.productservice.exception.MessageNotfoundException;
import com.hendisantika.productservice.model.request.ProductRequest;
import com.hendisantika.productservice.model.response.ProductResponse;
import com.hendisantika.productservice.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-gateway-k8s
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/4/23
 * Time: 09:10
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;

    private final CategoryService categoryService;

    public ProductResponse create(ProductRequest request) {
        log.info("request product = {} ", JSON.toJSON(request));
        categoryService.getCategory(request.getCategoryId());
        Products saveProduct = productsRepository.save(entityProduct(request));
        return modelProduct(saveProduct);
    }

    public Page<ProductResponse> list(Pageable pageable) {
        log.info("request product = {} ", JSON.toJSON(pageable));
        return productsRepository.findAll(pageable)
                .map(data -> ProductResponse.builder()
                        .id(data.getId())
                        .name(data.getName())
                        .category(this.categoryService.getCategory(data.getCategoryId()))
                        .image(data.getImage())
                        .qty(data.getQty())
                        .createdAt(data.getCreatedAt()
                                .format(DateTimeFormatter.ISO_LOCAL_DATE))
                        .price(data.getPrice())
                        .build());
    }

    public ProductResponse find(Long id) {
        log.info("request product find = {} ", id);
        return productsRepository.findById(id)
                .map(data -> modelProduct(data))
                .orElseThrow(() -> new MessageNotfoundException("product id not found"));
    }

    private Products entityProduct(ProductRequest request) {
        return Products.builder()
                .name(request.getName())
                .categoryId(request.getCategoryId())
                .createdAt(LocalDateTime.now())
                .price(request.getPrice())
                .image(request.getImage())
                .qty(request.getQty())
                .build();
    }
}
