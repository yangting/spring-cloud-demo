package one.yate.spring.cloud.provider.controller;

import lombok.extern.slf4j.Slf4j;
import one.yate.spring.cloud.provider.matedata.entity.ExUser;
import one.yate.spring.cloud.provider.service.ExUserService;
import one.yate.spring.cloud.remote.pojo.dto.UserDto;
import one.yate.spring.cloud.remote.pojo.vo.UserVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author yangting
 * @date 2018/4/10
 * TODO
 */
@Slf4j
@RestController
public class UserRestProvider {
    @Resource
    private ExUserService exUserService;

    @RequestMapping(value = "/user/{id}")
    public UserVo findBy(@PathVariable("id") Integer id, HttpServletRequest req, HttpServletResponse res) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            UserDetails gu = (UserDetails) principal;
//            Collection<? extends GrantedAuthority> auths = gu.getAuthorities();
//            for (GrantedAuthority r : auths) {
//                log.info("curruser = {}, role = {}", gu.getUsername(), r.getAuthority());
//            }
//        } else {
//            log.info("current user not login!!!!!");
//        }

        log.info("user.findBy paramter : id = {}", id);
        ExUser e = exUserService.findBy(id);
        UserVo r = new UserVo();
        r.setId(e.id());
        r.setAge(e.age());
        r.setBalance(e.balance());
        r.setNickname(e.nickname());
        r.setUsername(e.username());
        return r;
    }

    @PostMapping(value = "/user/search")
    public UserVo search(@RequestBody UserDto dto) {
        log.info("user.findBy paramter = {}", dto.toString());
        ExUser e = exUserService.findBy(dto.getId());
        UserVo r = new UserVo();
        r.setId(e.id());
        r.setAge(e.age());
        r.setBalance(e.balance());
        r.setNickname(e.nickname());
        r.setUsername(e.username());
        return r;
    }
}
