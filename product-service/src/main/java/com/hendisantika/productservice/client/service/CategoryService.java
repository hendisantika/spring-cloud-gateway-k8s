package com.hendisantika.productservice.client.service;

import com.alibaba.fastjson.JSONObject;
import com.hendisantika.productservice.client.feign.CategoryFeign;
import com.hendisantika.productservice.model.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-gateway-k8s
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/4/23
 * Time: 09:19
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryFeign categoryFeign;

    public Category getCategory(Long id) {
        log.info("request category by id = {} ", id);
        return JSONObject.parseObject(categoryFeign.getCategory(id).getBody(),
                Category.class);
    }
}
