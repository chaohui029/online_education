package cn.org.chaohui.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: chaohui
 * @Date: 2020/4/5
 * @Description: cn.org.chaohui.eduservice
 * @version: 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"cn.org.chaohui"}) //设置包的扫描规则--不加该注解,只会扫描当前项目的配置类信息,不会扫描到依赖的项目的包下的信息
public class OnlineEducationApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineEducationApplication.class, args);
    }

}                           
