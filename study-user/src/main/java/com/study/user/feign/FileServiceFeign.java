package com.study.user.feign;

import com.study.vo.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "study-file")
public interface FileServiceFeign {

    @RequestMapping(value = "/getFileHost",method = RequestMethod.GET)
    public String getFileHost(@RequestParam("name") String name);

    @RequestMapping(value = "/postFileHost",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String postFileHost(@RequestBody User user);
}
