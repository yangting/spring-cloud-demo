package one.yate.spring.cloud.remote.pojo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class UserVo {
    private Integer id;
    private String username;
    private String nickname;
    private Integer age;
    private BigDecimal balance;
}
