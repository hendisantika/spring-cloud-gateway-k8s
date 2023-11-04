package com.hendisantika.productservice.repository;

import com.hendisantika.productservice.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-gateway-k8s
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/4/23
 * Time: 09:09
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>,
        JpaSpecificationExecutor<Products> {
}
