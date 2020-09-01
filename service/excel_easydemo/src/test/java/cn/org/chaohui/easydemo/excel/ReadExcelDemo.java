package cn.org.chaohui.easydemo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Auther: http://www.bjsxt.com
 * @Date: 2020/6/21
 * @Description: cn.org.chaohui.easydemo.excel
 * @version: 1.0
 */
@Data
public class ReadExcelDemo {

    //设置列对应的属性
    @ExcelProperty(index = 0)
    private String course;

    //设置列对应的属性
    @ExcelProperty(index = 1)
    private String coursedetail;

    @Override
    public String toString() {
        return "ReadExcelDemo{" +
                "course=" + course +
                ", coursedetail='" + coursedetail + '\'' +
                '}';
    }
}
