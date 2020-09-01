package cn.org.chaohui.easydemo.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Auther: http://www.bjsxt.com
 * @Date: 2020/6/22
 * @Description: cn.org.chaohui.easydemo.excel
 * @version: 1.0
 */
public class TestReadEasyExcel {
    public static void main(String[] args) {
        String excelFilePath1 = "D:\\workspace\\idea_workspace\\easyexcelfile\\readexcelDemo1.xlsx";
        String excelFilePath2 = "D:\\workspace\\idea_workspace\\easyexcelfile\\readexcelDemo2.xlsx";

        //写法1
        excelRead1(excelFilePath1);

        //写法2:需要关闭流
        excelRead2(excelFilePath2);

    }


    //写法1
    private static void excelRead1(String filePath) {
        // 写法1：
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(filePath, ReadExcelDemo.class, new ExcelListener()).sheet().doRead();
    }

    private static void excelRead2(String filePath) {
        // 写法2：
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(filePath));
            ExcelReader excelReader = EasyExcel.read(in, ReadExcelDemo.class, new ExcelListener()).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
            // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
            excelReader.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
