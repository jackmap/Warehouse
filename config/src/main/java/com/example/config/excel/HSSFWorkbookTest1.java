package com.example.config.excel;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ClassName: SXSSFTest
 * @Description: TODO
 * @author qiaoshuai
 */
public class HSSFWorkbookTest1 {

    public static void main(String[] args) throws IOException {
        String  title="报关数据导入表";
        String[]  header={"主单号","订单号","商品名称","运单号","收货人名称","收货人地址","收货人国家",
                "申报品名","商品数量","成交价格","币制","毛重","净重","包装","发货人名称","批次号",
                "指运港","单位","指运港国检","抵运国海关","抵运国国检"};

        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet(title);
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row=sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        // 插入第一行数据的表头
        for (int i = 0; i < header.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }
        // 写入文件中
        FileOutputStream out = new FileOutputStream("F://报关数据导入表.xls");
        wb.write(out);
        // 关闭文件流对象
        out.close();
        System.out.println("基于流写入执行完毕!");
    }

}