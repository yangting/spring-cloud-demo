package one.yate.spring.cloud.provider.service.impl;

import one.yate.spring.cloud.provider.metadata.dao.IBaseMapperDao;
import one.yate.spring.cloud.provider.metadata.dao.mapper.MovieInfoMapper;
import one.yate.spring.cloud.provider.metadata.entity.MovieInfo;
import one.yate.spring.cloud.provider.service.IMovieInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 本段代码由sql2java自动生成.
 * https://github.com/yangting/sql2java
 *
 * @author Yate
 */
@Service
public class MovieInfoServiceImpl extends BaseServiceImpl<MovieInfo, Integer> implements IMovieInfoService {
    @Resource
    private MovieInfoMapper mapper;

    protected IBaseMapperDao<MovieInfo, Integer> getMapperDao() {
        return mapper;
    }
}