package one.yate.spring.cloud.provider.metadata.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 本段代码由sql2java自动生成.
 * https://github.com/yangting/sql2java
 *
 * @author Yate
 */
@Data
@ToString
@EqualsAndHashCode(exclude = {"id"})
public class OrderFlow implements Serializable {
    private Integer id;
    private Integer orderId;
    private Integer orderStatus;
    private Integer orderChain;
    private Date createTime;
}