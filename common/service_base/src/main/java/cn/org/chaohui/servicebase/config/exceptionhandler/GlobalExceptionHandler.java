package cn.org.chaohui.servicebase.config.exceptionhandler;

import cn.org.chaohui.commonutils.ExceptionUtil;
import cn.org.chaohui.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: http://www.bjsxt.com
 * @Date: 2020/4/9
 * @Description: cn.org.chaohui.servicebase.config.exceptionhandler
 * @version: 1.0
 */
@ControllerAdvice
@Slf4j  //添加此注解,当出现异常的时候,logback就会将异常信息记录到相应的文件中(还需添加 log.error(e.getMessage());语句)
public class GlobalExceptionHandler {

    //指定出现什么异常执行此方法--全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody  //在此处异常类中添加了该注解,同样可以返回数据
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error().message("执行了全局异常处理");
    }


    //处理特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.error().message("执行了ArithmeticException特定异常");
    }

    //处理自定义异常
    @ExceptionHandler(OnlineEduException.class)
    @ResponseBody
    public Result error(OnlineEduException e){
        /*
            在文件中显示的异常:(并不明确出现什么异常)   e.getMessage() 为null
            2020-04-09 20:09:05.454 [http-nio-8001-exec-1] ERROR c.o.c.s.c.exceptionhandler.GlobalExceptionHandler - null
            优化:可以重新起一个类OnlineEduExceptionHandler,专门处理自定义异常
         */
//        log.error(e.getMessage());  //添加注解后对应的变量--private static final Logger log = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class)
        System.out.println(e.getMessage() + "=====" + e.getMsg() + "=====" + e.getCode()); //null=====出现自定义异常=====201
        //改进:使用工具类更加明确异常的信息,即将所有的异常信息，包括堆栈信息输入到文件中
        log.error(ExceptionUtil.getMessage(e));

        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());

    }
}
