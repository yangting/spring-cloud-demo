package one.yate.spring.cloud.remote.hystrix;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import one.yate.spring.cloud.remote.api.IDemoRestService;
import one.yate.spring.cloud.remote.pojo.dto.UserDto;
import one.yate.spring.cloud.remote.pojo.vo.UserVo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoRestServiceFallback implements FallbackFactory<IDemoRestService> {
    @Override
    public IDemoRestService create(Throwable cause) {
        log.error(cause.getMessage());

        return new IDemoRestService() {
            public UserVo test1(Integer id) {
                return new UserVo();
            }

            public UserVo test2(UserDto dto) {
                return new UserVo();
            }
        };
    }
}
