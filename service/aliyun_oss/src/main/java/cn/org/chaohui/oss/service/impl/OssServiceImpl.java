package cn.org.chaohui.oss.service.impl;

import cn.org.chaohui.oss.service.OssService;
import cn.org.chaohui.oss.utils.ConstantPropertiesUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.ServiceMode;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.DateTimeException;
import java.util.UUID;

/**
 * @Auther: http://www.bjsxt.com
 * @Date: 2020/6/10
 * @Description: cn.org.chaohui.oss.service.impl
 * @version: 1.0
 */
@Service
public class OssServiceImpl implements OssService {

    //获取阿里云存储的相关常量
    /**
     * 注意: 该常量不能作为成员变量使用.因为在初始化该service实现类的时候
     *      ConstantPropertiesUtil类还未调用afterPropertiesSet方法对类变量进行赋值
     *      所以下方取不到对应的值--null
     */
//    String endpoint = ConstantPropertiesUtil.END_POINT;
//    String keyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
//    String keySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
//    String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

    /**
     * 流式上传--上传文件流
     * @param file
     * @return
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
         *  <yourBucketName> : bucket名称
         *  <yourObjectName> : 表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
         *  inputStream : 上传文件的输入流
         */
        //获取原始名称作为<yourObjectName>
        String originalFilename = file.getOriginalFilename();

        //对文件名称进行修改,防止提交相同图片导致覆盖(添加uuid以及日期)
        //类似:https://online-teach-file.oss-cn-beijing.aliyuncs.com/2019/10/30/de47ee9b-7fec-43c5-8173-13c5f7f689b2.png
        //1. 在文件名称里添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //2. 把文件按照日期进行分类存储到oss中
            //获取当前日期(使用日期工具类) 2020/2/1
            String datePath = new DateTime().toString("yyyy/MM/dd");

        originalFilename = datePath + "/" +uuid + originalFilename;

        //上传文件
        ossClient.putObject(bucketName, originalFilename, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        //手动拼接上传到oss的文件地址url并返回
        //https://chonlineedu-0000.oss-cn-hangzhou.aliyuncs.com/db243fd17963767a512cc1b1e56ed8f9-375.jpg
        String uploadFileUrl = "https://"+bucketName+"."+endpoint+"/"+originalFilename;
        System.out.println(uploadFileUrl);

        return uploadFileUrl;
    }

}
