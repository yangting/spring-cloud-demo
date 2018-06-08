package one.yate.spring.cloud.remote.api;

import one.yate.spring.cloud.remote.config.FeignConfig;
import one.yate.spring.cloud.remote.hystrix.MovieRestServiceFallback;
import one.yate.spring.cloud.remote.pojo.ErrorResult;
import one.yate.spring.cloud.remote.pojo.vo.MovieLockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(qualifier = "movie-provider", name = "movie-provider", fallback = MovieRestServiceFallback.class, configuration = {FeignConfig.class})
public interface IMovieRestService {
    @GetMapping("/hello/{id}")
    String hello(@PathVariable("id") Integer id);

    @GetMapping(value = "/movie/{id}")
    String findBy(@PathVariable("id") Integer id);

    @GetMapping(value = "/lock/ticket/{movieId}/{nums}")
    MovieLockVo lockTicket(@PathVariable("movieId") Integer movieId, @PathVariable("nums") Integer nums) throws ErrorResult;

}
