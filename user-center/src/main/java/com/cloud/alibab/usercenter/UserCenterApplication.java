package com.cloud.alibab.usercenter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiongcaesar
 * @date 2020/1/5
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
    }

    @RestController
    public class UserController {

        @Value(value = "${user.type}")
        private String userType;

        @GetMapping("/user/getUser")
        public User getUser() {
            return new User(12344, "萝拉拉", 18, userType);
        }
    }

    @Setter
    @Getter
    public static class User {
        private Integer id;
        private String userName;
        private Integer age;
        private String userType;

        public User(Integer id, String userName, Integer age, String userType) {
            this.id = id;
            this.userName = userName;
            this.age = age;
            this.userType = userType;
        }
    }

}
