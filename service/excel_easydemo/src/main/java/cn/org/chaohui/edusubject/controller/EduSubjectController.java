package cn.org.chaohui.edusubject.controller;


import cn.org.chaohui.edusubject.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-22
 */
@Api(description="课程分类管理")
@RestController
@RequestMapping("/edusubject/subject")
@CrossOrigin  //解决跨域
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    //获取上传过来文件,把文件内容读取过来


}

