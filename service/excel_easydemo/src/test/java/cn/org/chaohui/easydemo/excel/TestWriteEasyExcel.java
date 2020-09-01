package cn.org.chaohui.easydemo.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: http://www.bjsxt.com
 * @Date: 2020/6/17
 * @Description: cn.org.chaohui.easydemo.excel
 * @version: 1.0
 */
public class TestWriteEasyExcel {

    public static void main(String[] args) {
        //实现excel写的操作
        //1 设置写入文件夹地址和文件名称"writeexceldemo.xlsx"
        String excelFileName1 = "D:\\workspace\\idea_workspace\\easyexcelfile\\writeexceldemo1.xlsx";
        String excelFileName2 = "D:\\workspace\\idea_workspace\\easyexcelfile\\writeexceldemo2.xlsx";

        excelWrite1(excelFileName1);

        excelWrite2(excelFileName2);

    }

    //写法1
    private static void excelWrite1(String excelFileName){
        //2 调用easyexcel里面的方法实现写操作
        /*
         * excelFileName: 文件路径名称
         * WriteExcelDemo.class : 实体类class-->需要指定写用哪个class去写
         */
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(excelFileName, WriteExcelDemo.class).sheet("学生列表").doWrite(getData());
    }

    //写法2
    private static void excelWrite2(String excelFileName){
        // 方法二需要手动关闭流
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(excelFileName, WriteExcelDemo.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("写入方法二").build();
        excelWriter.write(getData(), writeSheet);
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }



    //创建构建list集合的方法
    private static List<WriteExcelDemo> getData(){
        List<WriteExcelDemo> list = new ArrayList<>();
        for(int i=0; i<10; i++){
            WriteExcelDemo excelDemo = new WriteExcelDemo();
            excelDemo.setSno(i);
            excelDemo.setName(i+"haha");
            list.add(excelDemo);
        }
        return list;
    }
}
