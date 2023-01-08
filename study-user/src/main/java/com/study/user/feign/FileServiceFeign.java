package com.study.user.feign;

import com.study.common.entity.User;
import com.study.user.hystrix.FileServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "study-file", fallback = FileServiceFallBack.class)
public interface FileServiceFeign {

    @RequestMapping(value = "/getFileHost", method = RequestMethod.GET)
    public String getFileHost(@RequestParam("name") String name);

    @RequestMapping(value = "/postFileHost", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String postFileHost(@RequestBody User user);
}
