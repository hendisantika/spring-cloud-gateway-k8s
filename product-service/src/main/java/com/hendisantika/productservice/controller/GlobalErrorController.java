package com.hendisantika.productservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-gateway-k8s
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/4/23
 * Time: 09:23
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestControllerAdvice
public class GlobalErrorController extends ResponseEntityExceptionHandler {

}
