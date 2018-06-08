package one.yate.spring.cloud.provider.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import one.yate.spring.cloud.provider.metadata.entity.MovieInfo;
import one.yate.spring.cloud.provider.service.IMovieInfoService;
import one.yate.spring.cloud.remote.api.IMovieRestService;
import one.yate.spring.cloud.remote.pojo.ErrorResult;
import one.yate.spring.cloud.remote.pojo.vo.MovieLockVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangting
 * @date 2018/4/10
 * TODO
 */
@Slf4j
@RestController
public class HelloRestProvider implements IMovieRestService {

    @Resource
    IMovieInfoService movieInfoService;

    @RequestMapping(value = "/hello/{id}")
    public String hello(@PathVariable("id") Integer id) {
        log.info("user.findBy paramter : id = {}", id);
        return "hello " + id;
    }

    @RequestMapping(value = "/movie/{id}")
    public String findBy(@PathVariable("id") Integer id) {
        log.info("user.findBy paramter : id = {}", id);
        MovieInfo e = movieInfoService.getEntity(id);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(e);
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
            return "error";
        }
    }

    @Override
    @GetMapping(value = "/lock/ticket/{movieId}/{nums}")
    public MovieLockVo lockTicket(@PathVariable("movieId") Integer movieId, @PathVariable("nums") Integer nums) throws ErrorResult {
        MovieInfo e = movieInfoService.getEntity(movieId);

        MovieLockVo vo = new MovieLockVo();
        vo.setMovieId(e.getId());
        vo.setMovieName(e.getMovieName());
        vo.setTicketPrice(e.getTicketPrice());
        List<String> seats = new ArrayList<String>();
        seats.add("1f1s");
        seats.add("1f2s");
        vo.setSeatList(seats);

        return vo;
    }
}
