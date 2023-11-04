package com.hendisantika.productservice.exception;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-gateway-k8s
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/4/23
 * Time: 09:13
 * To change this template use File | Settings | File Templates.
 */
@Data
public class ErrorFromClientException extends RuntimeException {

    private String errorStatus;

    public ErrorFromClientException(String message, String errorStatus) {
        super(message);
        this.errorStatus = errorStatus;
    }
}
