package one.yate.spring.cloud.provider.matedata.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 本段代码由sql2java自动生成.
 * https://github.com/yangting/sql2java
 *
 * @author Yate
 */
@ToString
@EqualsAndHashCode(exclude = {"id"})
@Accessors(fluent = true)
@Data
public class ExUser implements Serializable {
    private Integer id;
    private String username;
    private String nickname;
    private Integer age;
    private java.math.BigDecimal balance;
}