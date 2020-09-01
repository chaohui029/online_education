package cn.org.chaohui.easydemo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Auther: http://www.bjsxt.com
 * @Date: 2020/6/17
 * @Description: cn.org.chaohui.easydemo.excel
 * @version: 1.0
 */
//设置表头和添加的数据字段
@Data
public class WriteExcelDemo {

    //设置excel表头名称
    @ExcelProperty(value = "学生编号")
    private int sno;

    //设置excel表头名称
    @ExcelProperty(value = "学生姓名")
    private String name;

    @Override
    public String toString() {
        return "WriteExcelDemo{" +
                "sno=" + sno +
                ", name='" + name + '\'' +
                '}';
    }
}
