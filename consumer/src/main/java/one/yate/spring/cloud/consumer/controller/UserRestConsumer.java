package one.yate.spring.cloud.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import one.yate.spring.cloud.remote.api.IDemoRestService;
import one.yate.spring.cloud.remote.api.IMovieRestService;
import one.yate.spring.cloud.remote.pojo.dto.UserDto;
import one.yate.spring.cloud.remote.pojo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class UserRestConsumer {
    @Resource
    IMovieRestService helloRestRomte;
    @Resource
    IDemoRestService demoRestRomte;
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/user/{id}")
    public UserVo findBy(@PathVariable("id") Integer id) {
        UserVo result = demoRestRomte.test1(id);
        return result;
    }

    @PostMapping("/user/search")
    public UserVo searchUser(@RequestBody UserDto dto) {
        UserVo result = this.demoRestRomte.test2(dto);
        return result;
    }

    @GetMapping("/hello/{id}")
    public String findByHello(@PathVariable("id") Integer id) {
        String result = helloRestRomte.findBy(id);
        return result;
    }

    @GetMapping("/movie/{id}")
    public String findByMovie(@PathVariable("id") Integer id) {
        String result = helloRestRomte.findBy(id);
        return result;
    }

    @GetMapping("/log/instance/{pro}")
    public void logPrintInstance(@PathVariable("pro") String pro) {
        ServiceInstance si = this.loadBalancerClient.choose(pro);
        log.info("{}:{}:{}", si.getServiceId(), si.getHost(), si.getPort());
    }
}
