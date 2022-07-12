package com.ling.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ling.manager.customer.mapper")
public class CustomerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServerApplication.class, args);
    }

}
