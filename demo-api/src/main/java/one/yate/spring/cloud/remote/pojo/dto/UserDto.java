package one.yate.spring.cloud.remote.pojo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDto implements Serializable {
    private Integer Id;
    private String username;
}
