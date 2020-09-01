package cn.org.chaohui.oss.controller;

import cn.org.chaohui.commonutils.Result;
import cn.org.chaohui.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: http://www.bjsxt.com
 * @Date: 2020/6/10
 * @Description: cn.org.chaohui.oss.controller
 * @version: 1.0
 */
@Api(description = "阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    //上传头像
    @ApiOperation(value = "上传头像")
    @PostMapping("uploadOssFile")
    public Result uploadOssFile(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file){
        //获取上传文件
        String url = ossService.uploadFileAvatar(file);

        //返回上传到oss的路径
        return Result.ok().data("url", url);

    }
}
