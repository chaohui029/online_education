package cn.org.chaohui.oss.service;

import net.sf.jsqlparser.schema.MultiPartName;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: http://www.bjsxt.com
 * @Date: 2020/6/10
 * @Description: cn.org.chaohui.oss.service
 * @version: 1.0
 */
public interface OssService {

    /**
     * 文件上传至阿里云
     * @param file
     * @return
     */
    String uploadFileAvatar(MultipartFile file);

}
