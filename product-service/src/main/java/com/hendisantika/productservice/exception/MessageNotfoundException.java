package com.hendisantika.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

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
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MessageNotfoundException extends RuntimeException {

    public MessageNotfoundException(String message) {
        super(message);
    }
}
