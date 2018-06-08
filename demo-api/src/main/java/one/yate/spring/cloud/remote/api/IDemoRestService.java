package one.yate.spring.cloud.remote.api;

import one.yate.spring.cloud.remote.config.FeignConfig;
import one.yate.spring.cloud.remote.hystrix.DemoRestServiceFallback;
import one.yate.spring.cloud.remote.pojo.dto.UserDto;
import one.yate.spring.cloud.remote.pojo.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(qualifier = "demo-provider", name = "demo-provider", fallbackFactory = DemoRestServiceFallback.class, configuration = {FeignConfig.class})
public interface IDemoRestService {

    @GetMapping("/user/{id}")
    public UserVo test1(@PathVariable("id") Integer id);

    @PostMapping("/user/search")
    public UserVo test2(@RequestBody UserDto dto);
}
