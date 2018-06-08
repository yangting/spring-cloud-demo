package one.yate.spring.cloud.remote.pojo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MovieLockVo {
    private Integer movieId;
    private String movieName;
    private java.math.BigDecimal ticketPrice;
    private List<String> seatList;
}
