package cn.org.chaohui.easydemo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: http://www.bjsxt.com
 * @Date: 2020/6/22
 * @Description: cn.org.chaohui.easydemo.excel
 * @version: 1.0
 */

/**
 * 创建excel监听器
 */
public class ExcelListener extends AnalysisEventListener<ReadExcelDemo> {

    //创建list集合封装最终的数据
    List<ReadExcelDemo> list = new ArrayList<>(10);

    //一行一行读取excel中的内容(除表头以外)
    @Override
    public void invoke(ReadExcelDemo data, AnalysisContext analysisContext) {
        System.out.println("=======" + data);
        list.add(data);
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息:" + headMap);
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {}
}
