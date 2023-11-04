package com.hendisantika.productservice.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-gateway-k8s
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/4/23
 * Time: 09:14
 * To change this template use File | Settings | File Templates.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("qty")
    private Integer qty;

    @JsonProperty("image")
    private String image;
}
