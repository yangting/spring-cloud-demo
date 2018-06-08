package one.yate.spring.cloud.provider.metadata.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 本段代码由sql2java自动生成.
 * https://github.com/yangting/sql2java
 *
 * @author Yate
 */
@Data
@ToString
@EqualsAndHashCode(exclude = {"id"})
public class MovieInfo implements Serializable {
    private Integer id;
    private String movieName;
    private java.math.BigDecimal ticketPrice;
}