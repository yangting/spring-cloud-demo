package one.yate.spring.cloud.remote.hystrix;

import lombok.extern.slf4j.Slf4j;
import one.yate.spring.cloud.remote.api.IMovieRestService;
import one.yate.spring.cloud.remote.pojo.ErrorResult;
import one.yate.spring.cloud.remote.pojo.vo.MovieLockVo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MovieRestServiceFallback implements IMovieRestService {
    public String findBy(Integer id) {
        return "hystrix fallback return error msg:" + id;
    }

    @Override
    public MovieLockVo lockTicket(Integer movieId, Integer nums) throws ErrorResult {
        throw new ErrorResult(-1, "-1");
    }

    public String hello(Integer id) {
        return "hystrix fallback return error msg";
    }

}
