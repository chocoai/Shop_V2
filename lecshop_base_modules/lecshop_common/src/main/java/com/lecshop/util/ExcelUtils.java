package com.lecshop.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 导出Excel工具类
 *
 * @author sunluyang on 2017/6/5.
 */

public class ExcelUtils {
    /**
     * 导出Excel 创建Excel表格
     *
     * @param response    响应对象
     * @param sheetName   sheet名称
     * @param widthSetMap 宽度设置
     * @param titleSetMap 标题栏名称设置
     * @param filename    文件名称设置
     * @return map
     */
    public static Map<String, Object> createExcel(HttpServletResponse response, String sheetName, Map<Integer, Integer> widthSetMap, Map<Integer, String> titleSetMap, String filename) {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        // Sheet样式
        hssfWorkbook.createCellStyle().setAlignment(CellStyle.ALIGN_CENTER);
        HSSFSheet sheet = hssfWorkbook.createSheet(sheetName);
        // 冻结
        sheet.createFreezePane(255, 1);
        // 调整excel列宽
        widthSetMap.forEach((key, value) -> sheet.setColumnWidth(key, value));
        //设置标题
        HSSFRow headRow = sheet.createRow(0);
        titleSetMap.forEach((key, value) -> headRow.createCell(key).setCellValue(value));
        // 设置下载时客户端Excel的名称
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename));
        Map<String, Object> backMap = new HashMap<>();
        backMap.put("response", response);
        backMap.put("sheet", sheet);
        backMap.put("hssfWorkbook", hssfWorkbook);
        return backMap;
    }
}
