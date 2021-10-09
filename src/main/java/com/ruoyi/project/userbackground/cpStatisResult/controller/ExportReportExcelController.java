package com.ruoyi.project.userbackground.cpStatisResult.controller;

import com.ruoyi.project.managementbackground.activity.domain.CpActivity;
import com.ruoyi.project.managementbackground.activity.service.ICpActivityService;
import com.ruoyi.project.userbackground.cpStatisResult.service.ICpStatisResultService;
import com.ruoyi.project.userbackground.sheet.domain.CpSheet;
import com.ruoyi.project.userbackground.sheet.service.ICpSheetService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出报表结果Controller
 *
 * @author ying
 * @date 2021-09-12
 */
@Controller
@RequestMapping("/exportReportExcel")
public class ExportReportExcelController {
    @Autowired
    private ICpStatisResultService cpStatisResultService;

    @Autowired
    private ICpActivityService cpActivityService;
    @Autowired
    private ICpSheetService cpSheetService;

    /**
     *
     * @param activityId
     * @param sheetId
     * @param isnotRank(1开启0关闭)
     * @param objType(0:个人；1:部门)
     * @param request
     * @return
     */
    @GetMapping( "/exportStatisResult")
    @ResponseBody
    public void exportStatisResult(Long activityId, Long sheetId, Long isnotRank,String objType, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();
        if ("1".equals(objType)) {
            map = cpStatisResultService.selectDeptStatisResult(activityId, sheetId, isnotRank);
        }else {
            map = cpStatisResultService.selectUserStatisResult(activityId, sheetId, isnotRank);
        }
        createExcelXls( activityId, sheetId,map, request, response);
    }

    //创建excel
    public void createExcelXls(Long activityId, Long sheetId, Map<String, Object> dataMap, HttpServletRequest request, HttpServletResponse response) {
        CpActivity activity = cpActivityService.selectCpActivityById(activityId);
        CpSheet cpSheet = cpSheetService.selectCpSheetById(sheetId);
        HSSFWorkbook hWorkbook = new HSSFWorkbook();

        try {
            HSSFCellStyle cellStyle = hWorkbook.createCellStyle();

            hWorkbook.createSheet(cpSheet.getName() + "统计结果");
            // 添加表头, 创建第一行
            HSSFSheet sheet = hWorkbook.getSheet(cpSheet.getName() + "统计结果");

            //放入数据
            addExcelDetail(sheet, cellStyle, dataMap, hWorkbook, cpSheet);

            OutputStream outputStream = response.getOutputStream();

            String filename = activity.getActName() + "-" + cpSheet.getName() + "统计结果" + ".xls";

            String userAgent = request.getHeader("USER-AGENT");

            String filename1 = "";
            if (org.apache.commons.lang.StringUtils.contains(userAgent, "Firefox")
                    || org.apache.commons.lang.StringUtils.contains(userAgent, "firefox")) {
                filename1 = new String(filename.getBytes(), "ISO8859-1");
            } else {
                filename1 = URLEncoder.encode(filename, "utf-8");
            }
            String headstr = "attachment;fileName=\"" + filename1 + "\"";
            // response.setContentType("APPLICATION-OCTET-STREAM");
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", headstr);
            hWorkbook.write(outputStream);
            outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //放入数据
    public void addExcelDetail(HSSFSheet sheet, HSSFCellStyle cellStyle, Map<String, Object> dataMap, HSSFWorkbook hWorkbook,CpSheet cpSheet){
        List<Map<String,Object>> colList = (List<Map<String, Object>>) dataMap.get("colList");
        List<Map<String,Object>> rankList = (List<Map<String, Object>>) dataMap.get("rankList");
        List<Map<String,Object>> valueMapList = (List<Map<String, Object>>) dataMap.get("valueMapList");

        //第一列编号合并3行
        CellRangeAddress region1 = new CellRangeAddress(0, 2, 0, 0);// 下标从0开始 起始行号，终止行号， 起始列号，终止列号
        sheet.addMergedRegion(region1);
        //第一列部门／个人姓名合并3行
        CellRangeAddress region2 = new CellRangeAddress(0, 2, 1, 1);// 下标从0开始 起始行号，终止行号， 起始列号，终止列号
        sheet.addMergedRegion(region2);

        //第一行
        HSSFRow newRow1 = sheet.createRow(0);// 行1
        newRow1.setHeight((short) (20 * 20));// 设置行高 基数为20
        //第一个单元格
        HSSFCell row1cell0 = newRow1.createCell(0);
        row1cell0.setCellStyle(cellStyle);
        row1cell0.setCellValue("编号");

        //第二个单元格
        HSSFCell row1cell1 = newRow1.createCell(1);
        row1cell1.setCellStyle(cellStyle);
        if("1".equals(cpSheet.getObjType())){//单位/部门
            row1cell1.setCellValue("部门");
        }else{
            row1cell1.setCellValue("姓名");
        }

        //第二行
        HSSFRow newRow2 = sheet.createRow(1);// 行2
        newRow2.setHeight((short) (20 * 20));// 设置行高 基数为20

        //第三行
        HSSFRow newRow3 = sheet.createRow(2);// 行3
        newRow3.setHeight((short) (20 * 20));// 设置行高 基数为20

        //第一行项目根据职级个数（ranklist）＊2（平均分，加权分）
        int row1FirstCol = 2;//第一行第一个合并单元个的起始列
        int hebingLen = rankList.size()*2;
        int row1LastCol = 2 + hebingLen - 1;//第一行第一个合并单元个的终止列

        //第二行职级合并2格（平均分，加权分）
        int row2FirstCol = 2;//第一行第一个合并单元个的起始列
        int row2LastCol = 2 + 2 - 1;//第一行第一个合并单元个的终止列


        for (Map<String,Object> colMap:colList) {

            String columnId = colMap.get("columnId").toString();
            CellRangeAddress regionRow1 = new CellRangeAddress(0, 0, row1FirstCol, row1LastCol);// 下标从0开始 起始行号，终止行号， 起始列号，终止列号
            sheet.addMergedRegion(regionRow1);


            //第三个单元格及之后
            HSSFCell row1celln = newRow1.createCell(row2FirstCol);
            row1celln.setCellStyle(cellStyle);
            row1celln.setCellValue(colMap.get("columnTitle").toString());

            row1FirstCol = row1LastCol + 1;
            row1LastCol = row1FirstCol + hebingLen - 1;
            //第二行职级合并2格（平均分，加权分）
            for (Map<String,Object> rankMap:rankList){
                String rankId = rankMap.get("rankId").toString();
                CellRangeAddress regionRow2 = new CellRangeAddress(1, 1, row2FirstCol, row2LastCol);// 下标从0开始 起始行号，终止行号， 起始列号，终止列号
                sheet.addMergedRegion(regionRow2);

                //第三个单元格及之后
                HSSFCell row2celln = newRow2.createCell(row2FirstCol);
                row2celln.setCellStyle(cellStyle);
                row2celln.setCellValue(rankMap.get("rankName").toString());

                //第三个单元格及单数后
                HSSFCell row3cell3n = newRow3.createCell(row2FirstCol);
                row3cell3n.setCellStyle(cellStyle);
                row3cell3n.setCellValue("平均分");
                //第4个单元格及双数后
                HSSFCell row3cell4n = newRow3.createCell(row2FirstCol+1);
                row3cell4n.setCellStyle(cellStyle);
                row3cell4n.setCellValue("加权分");

                //第四行数据之后循环
                int startHs = 3;//从行4开始
                for(Map<String,Object> valueMap :valueMapList){

                    if(row2FirstCol == 2) {
                        HSSFRow newRow = sheet.createRow(startHs);// 从第4行开始累加
                        newRow.setHeight((short) (20 * 20));// 设置行高 基数为20
                        // 第一列：编号
                        HSSFCell cell1 = newRow.createCell(0);
                        cell1.setCellStyle(cellStyle);
                        cell1.setCellValue("");

                        // 第二列：名称
                        HSSFCell cell2 = newRow.createCell(1);
                        cell2.setCellStyle(cellStyle);
                        cell2.setCellValue(valueMap.get("objUserName").toString());

                        // 第三列：平均值
                        HSSFCell cell3 = newRow.createCell(2);
                        cell3.setCellStyle(cellStyle);
                        cell3.setCellValue(valueMap.get(columnId+"_"+rankId+"_avg").toString());
                        // 第四列：加权分
                        HSSFCell cell4 = newRow.createCell(3);
                        cell4.setCellStyle(cellStyle);
                        cell4.setCellValue(valueMap.get(columnId+"_"+rankId+"_jq").toString());
                    }else {
                        HSSFRow newRow = sheet.getRow(startHs);
                        // 第三列：平均值
                        HSSFCell cell3n = newRow.createCell(row2FirstCol);
                        cell3n.setCellStyle(cellStyle);
                        cell3n.setCellValue(valueMap.get(columnId+"_"+rankId+"_avg").toString());
                        // 第四列：加权分
                        HSSFCell cell4n = newRow.createCell(row2FirstCol+1);
                        cell4n.setCellStyle(cellStyle);
                        cell4n.setCellValue(valueMap.get(columnId+"_"+rankId+"_jq").toString());

                    }
                    startHs++;
                }

                row2FirstCol = row2LastCol + 1;
                row2LastCol = row2FirstCol + 2 - 1;

            }
        }

    }
}
