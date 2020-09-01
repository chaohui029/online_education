package cn.org.chaohui.eduservice.controller;

import cn.org.chaohui.commonutils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: http://www.bjsxt.com
 * @Date: 2020/4/15
 * @Description: cn.org.chaohui.eduservice.controller
 * @version: 1.0
 */
@Api(description="登录管理")
@RestController
@RequestMapping("eduservice/user")
@CrossOrigin //解决跨域
public class EduLoginController {

    //login
    @ApiOperation(value = "登录")
    @PostMapping("login")
    public Result login(){
        return Result
                .ok()
                .data("token", "admin");
    }

    //info
    @ApiOperation(value = "详细信息")
    @GetMapping("info")
    public Result info(){
        return Result
                .ok()
                .data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
