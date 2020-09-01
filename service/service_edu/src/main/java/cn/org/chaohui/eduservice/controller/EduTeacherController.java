package cn.org.chaohui.eduservice.controller;


import cn.org.chaohui.commonutils.Result;
import cn.org.chaohui.eduservice.entity.EduTeacher;
import cn.org.chaohui.eduservice.entity.vo.TeacherQuery;
import cn.org.chaohui.eduservice.service.EduTeacherService;
import cn.org.chaohui.servicebase.config.exceptionhandler.OnlineEduException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-04
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("eduservice/teacher")
@CrossOrigin //解决跨域
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService eduTeacherService;


    //1.查询讲师表所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")  //rest风格
    public Result findAllTeacher(){
//        try {
//            int a = 10/0;
//        }catch(Exception e) {
//            throw new OnlineEduException(201,"出现自定义异常");
//        }
        //调用service的方法实现查询所有的操作
        List<EduTeacher> eduTeachers = eduTeacherService.list(null);
//        return eduTeachers;

        //统一返回结果对象Result
        return Result.ok().data("items", eduTeachers);
    }



    //2.根据id逻辑删除讲师
    //测试方法: 1.使用postman 2.整合swagger2进行测试(重点)
    /*
     * 注意: @DeleteMapping--无法直接通过浏览器输入url进行测试
     * 整合swagger进行测试: 1.生成在线接口文档
     *                      2.方便接口测试
     */
    @ApiOperation(value = "根据ID逻辑删除讲师")
    @DeleteMapping("{id}") //id需要通过路径进行传递
    public Result removeTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true) //required==true 必须有参数传入
            @PathVariable String id){ //获取路径中的id值
        boolean flag = eduTeacherService.removeById(id);
        if(flag){
            return Result.ok();
        }
        return Result.error();
    }


    //分页查询讲师
    //current:当前页
    //limit:每页记录数
    /*
        返回数据的格式
                    {
                      "success": true,
                      "code": 20000,
                      "message": "成功",
                      "data": {
                        "total": 17,
                        "rows": [
                          {
                            "id": "1",
                            "name": "刘德华",
                            "intro": "毕业于师范大学数学系，热爱教育事业，执教数学思维6年有余"
                          }
                        ]
                      }
                    }
     */
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageTeacher/{current}/{limit}")
    public Result pageListTeacher(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable long current,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit){

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        //调用分页方法
        //调用mp分页查询过程中,底层封装(此处通过eduTeacherService来调用,底层封装的就是mp的分页查询(依赖的就是BaseMapper))
        //将分页的所有数据封装到pageTeacher对象中
        eduTeacherService.page(pageTeacher, null);
        //获取总记录数
        long total = pageTeacher.getTotal();
        //获取当前页的记录数
        List<EduTeacher> records = pageTeacher.getRecords();

        //data(map)里存放了total和row两条数据
        /*
            HashMap<Object, Object> map = new HashMap<>();
            map.put("total", total);
            map.put("rows", records);
            return Result.ok().data(map);
        */
        return Result.ok().data("total", total).data("rows", records);
    }


    //多条件组合分页查询讲师
    @ApiOperation(value = "多条件组合分页查询讲师列表")
    @PostMapping("pageQueryTeachewr/{current}/{limit}")
    public Result pageQueryTeachewr(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable long current,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit,

            /*
                使用@RequestBody获取数据,此时需要使用post提交,如果使用get提交那么会取不到数据
                同时也表示会使用json传递数据,把json数据封装到对应的对象中,
                required = false对象中的属性值可以没有
             */
            @RequestBody(required = false)
            TeacherQuery teacherQuery){  //封装了查询条件

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        //构建条件
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();

        //多条件组合查询 -- 类似于mybatis中的动态sql拼接
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name", name);
        }
        if(!StringUtils.isEmpty(level)){
            queryWrapper.eq("level", level);
        }
        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create", begin);
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_modified", end);
        }
        //根据讲师创建时间降序排列
        queryWrapper.orderByDesc("gmt_create");

        //调用分页方法,将分页的所有数据封装到pageTeacher对象
        eduTeacherService.page(pageTeacher, queryWrapper);

        //获取总记录数
        long total = pageTeacher.getTotal();
        //获取当前页的记录数
        List<EduTeacher> records = pageTeacher.getRecords();

        return Result.ok().data("total", total).data("rows", records);
    }

    //添加讲师(在测试过程中id,gmtCreate,gmtModified不需要在json格式的数据中设值,已交由mybatis plus自动填充处理)
    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher){

        //返回操作是否成功
        boolean save = eduTeacherService.save(eduTeacher);
        if(save){
            return Result.ok();
        }
        return Result.error();
    }

    //根据讲师id进行查询
    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return Result.ok().data("teacher", eduTeacher);
    }



    //根据讲师id进行修改
    //1.Post方式
    @ApiOperation(value = "根据ID修改讲师_Post")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher){
        //返回操作是否成功
        boolean update = eduTeacherService.updateById(eduTeacher);
        if(update){
            return Result.ok();
        }
        return Result.error();
    }
    //2.Put方式
    @ApiOperation(value = "根据ID修改讲师_Put")
    @PutMapping("{id}")
    public Result updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,

            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher){

        //注意: put方式需要对id进行设值--待补充
        eduTeacher.setId(id);
        //返回操作是否成功
        boolean update = eduTeacherService.updateById(eduTeacher);
        if(update){
            return Result.ok();
        }
        return Result.error();
    }

}

