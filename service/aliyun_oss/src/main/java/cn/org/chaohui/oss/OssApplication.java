package cn.org.chaohui.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: chaohui
 * @Date: 2020/4/5
 * @Description: cn.org.chaohui.eduservice
 * @version: 1.0
 */
@ComponentScan(basePackages = {"cn.org.chaohui"}) //设置包的扫描规则--不加该注解,只会扫描当前项目的配置类信息,不会扫描到依赖的项目的包下的信息
/**
 * 报错:
 *      Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.
 *      Reason: Failed to determine a suitable driver class
 * 原因:该子项目只需操作上传到OSS,不需要操作数据库
 *
 * 解决:在@SpringBootApplication()添加exclude= {DataSourceAutoConfiguration.class}
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class OssApplication {

    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }

}                           
