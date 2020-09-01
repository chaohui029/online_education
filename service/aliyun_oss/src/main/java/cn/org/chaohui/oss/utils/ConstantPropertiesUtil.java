package cn.org.chaohui.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Auther: http://www.bjsxt.com
 * @Date: 2020/6/8
 * @Description: cn.org.chaohui.oss.utils
 * @version: 1.0
 */

/**
 * 常量类: 读取配置文件application.properties中的配置
 *
 *   使用@Value读取application.properties里的配置内容
 *   用spring的 InitializingBean 的 afterPropertiesSet 来初始化配置信息，这个方法将在所有的属性被初始化后调用。
 *
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    //读取配置文件的内容
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

//    private String fileHost;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    public static String END_POINT;  //地域节点
    public static String ACCESS_KEY_ID; //访问阿里云id
    public static String ACCESS_KEY_SECRET; //访问阿里云密钥
    public static String BUCKET_NAME; //存储空间名称
//    public static String FILE_HOST;

    /**
     * 该方法会在上述成员变量初始化之后执行
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
//        FILE_HOST = fileHost;
        System.out.println(END_POINT + "--" + ACCESS_KEY_ID + "--" + ACCESS_KEY_SECRET );
    }
}
