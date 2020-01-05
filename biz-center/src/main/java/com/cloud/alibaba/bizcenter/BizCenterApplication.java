package com.cloud.alibaba.bizcenter;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.alibaba.fastjson.JSONObject;
import com.netflix.loadbalancer.IRule;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author xiongcaesar
 * @date 2020/1/5
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BizCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BizCenterApplication.class, args);
    }

    @RestController
    @AllArgsConstructor
    public class Consumer {

        private final RestTemplate restTemplate;

        @GetMapping("/biz/queryData")
        public String queryData() {
            JSONObject object = restTemplate.getForObject("http://user-center:9528/user/getUser", JSONObject.class);
            assert object != null;
            return object.toJSONString();
        }

    }

    @Bean(name = "restTemplate")
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    @Bean
    public IRule rule() {
        return new NacosRule();
    }
}
