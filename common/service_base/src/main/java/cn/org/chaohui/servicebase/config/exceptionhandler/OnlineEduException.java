package cn.org.chaohui.servicebase.config.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: http://www.bjsxt.com
 * @Date: 2020/4/9
 * @Description: cn.org.chaohui.servicebase.config.exceptionhandler
 * @version: 1.0
 */
@Data
@AllArgsConstructor //生成有参数构造方法
@NoArgsConstructor  //生成无参数构造方法
public class OnlineEduException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code; //状态码

    @ApiModelProperty(value = "异常信息")
    private String msg; //异常信息

    @Override
    public String toString() {
        return "OnlineEduException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
