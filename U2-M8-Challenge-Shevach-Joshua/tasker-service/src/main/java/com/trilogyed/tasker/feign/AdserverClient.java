package com.trilogyed.tasker.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "adserver-service")
public interface AdserverClient {

    @GetMapping("/ad")
    String getAd();

}
