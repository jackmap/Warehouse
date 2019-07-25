package com.example.config.excel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtils {

    /**
     * @throws IOException
     *
     */
    public static void CreateExcel(HttpServletResponse response, String[] title, List<List<String>> entity)
            throws IOException {
        //	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
        // 创建excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表sheet
        HSSFSheet sheet = workbook.createSheet();
        // 创建第一行
        HSSFRow row = (HSSFRow) sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        // 插入第一行数据的表头
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        // 写入数据
        for (int i = 0; i < entity.size(); i++) {
            HSSFRow nrow = sheet.createRow(i + 1);
            for (int j = 0; j < title.length; j++) {
                HSSFCell ncell = nrow.createCell(j);
                //	System.err.println("第" + i + "行，第" + j + "个单元格");
                ncell.setCellValue(entity.get(i).get(j));
                //	System.err.println(entity.get(i).get(j));
            }
        }
        for (int i = 0; i < title.length; i++) {
            sheet.autoSizeColumn(i, true);
        }
        response.setContentType("application/msexcel;charset=utf-8");
        //	response.setHeader("Content-disposition", "attachment;filename= " + sdf.format(new Date()) + ".xls");
        workbook.write(response.getOutputStream());
    }



    public static void CreateExcelTop(HttpServletResponse response, String[] title)
            throws IOException {
        //	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
        // 创建excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表sheet
        HSSFSheet sheet = workbook.createSheet();
        // 创建第一行
        HSSFRow row = (HSSFRow) sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        // 插入第一行数据的表头
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        for (int i = 0; i < title.length; i++) {
            sheet.autoSizeColumn(i, true);
        }
        response.setContentType("application/msexcel;charset=utf-8");
        //	response.setHeader("Content-disposition", "attachment;filename= " + sdf.format(new Date()) + ".xls");
        workbook.write(response.getOutputStream());
    }

}
