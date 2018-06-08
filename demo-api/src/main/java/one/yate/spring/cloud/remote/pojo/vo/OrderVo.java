package one.yate.spring.cloud.remote.pojo.vo;

import lombok.Data;

@Data
public class OrderVo {
    private Integer userId;
    private String userName;
    private Integer movieId;
    private String movieName;
    private String lockSeats;
    private Integer ticketNums;
    private java.math.BigDecimal ticketPrice;
}
