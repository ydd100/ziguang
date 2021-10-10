package com.ruoyi.project.userbackground.cpStatisResult.controller;

import com.ruoyi.project.managementbackground.activity.domain.CpActivity;
import com.ruoyi.project.managementbackground.activity.service.ICpActivityService;
import com.ruoyi.project.userbackground.cpStatisResult.service.ICpStatisResultService;
import com.ruoyi.project.userbackground.sheet.domain.CpSheet;
import com.ruoyi.project.userbackground.sheet.service.ICpSheetService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
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
        XSSFWorkbook hWorkbook = new XSSFWorkbook();

        try {
            XSSFCellStyle cellStyle = hWorkbook.createCellStyle();

            hWorkbook.createSheet(cpSheet.getName() + "统计结果");
            // 添加表头, 创建第一行
            XSSFSheet sheet = hWorkbook.getSheet(cpSheet.getName() + "统计结果");

            //放入数据
            addExcelDetail(sheet, cellStyle, dataMap, hWorkbook, cpSheet);

            OutputStream outputStream = response.getOutputStream();

            String filename = activity.getActName() + "-" + cpSheet.getName() + "统计结果" + ".xlsx";

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
    public void addExcelDetail(XSSFSheet sheet, XSSFCellStyle cellStyle, Map<String, Object> dataMap, XSSFWorkbook hWorkbook,CpSheet cpSheet){
        List<Map<String,Object>> colList = (List<Map<String, Object>>) dataMap.get("colList");
        List<Map<String,Object>> rankList = (List<Map<String, Object>>) dataMap.get("rankList");
        List<Map<String,Object>> valueMapList = (List<Map<String, Object>>) dataMap.get("valueMapList");

        //第一列编号合并2行
        CellRangeAddress region1 = new CellRangeAddress(0, 1, 0, 0);// 下标从0开始 起始行号，终止行号， 起始列号，终止列号
        sheet.addMergedRegion(region1);
        //第一列部门／个人姓名合并2行
        CellRangeAddress region2 = new CellRangeAddress(0, 1, 1, 1);// 下标从0开始 起始行号，终止行号， 起始列号，终止列号
        sheet.addMergedRegion(region2);

        //第一行
        XSSFRow newRow1 = sheet.createRow(0);// 行1
        newRow1.setHeight((short) (20 * 20));// 设置行高 基数为20
        //第一个单元格
        XSSFCell row1cell0 = newRow1.createCell(0);
        row1cell0.setCellStyle(cellStyle);
        row1cell0.setCellValue("编号");

        //第二个单元格
        XSSFCell row1cell1 = newRow1.createCell(1);
        row1cell1.setCellStyle(cellStyle);
        if("1".equals(cpSheet.getObjType())){//单位/部门
            row1cell1.setCellValue("部门");
        }else{
            row1cell1.setCellValue("姓名");
        }

        //第二行
        XSSFRow newRow2 = sheet.createRow(1);// 行2
        newRow2.setHeight((short) (20 * 20));// 设置行高 基数为20

        //第一行项目根据职级个数（ranklist（加权分）
        int row1FirstCol = 2;//第一行第一个合并单元个的起始列
        int hebingLen = rankList.size();
        int row1LastCol = 2 + hebingLen - 1;//第一行第一个合并单元个的终止列

        //第二行职级（加权分）
        int row2FirstCol = 2;//第一行单元格的起始列

        for (int j = 0;j<colList.size();j++) {
            Map<String,Object> colMap = colList.get(j);
            String columnId = colMap.get("columnId").toString();
            if(hebingLen>1){
                CellRangeAddress regionRow1 = new CellRangeAddress(0, 0, row1FirstCol, row1LastCol);// 下标从0开始 起始行号，终止行号， 起始列号，终止列号
                sheet.addMergedRegion(regionRow1);
            }

            row1FirstCol = row1LastCol + 1;
            row1LastCol = row1FirstCol + hebingLen - 1;

            //第三个单元格及之后
            XSSFCell row1celln = newRow1.createCell(row2FirstCol);
            row1celln.setCellStyle(cellStyle);
            row1celln.setCellValue(colMap.get("columnTitle").toString()+"("+colMap.get("allV").toString()+")");

            sheet.setColumnWidth(row2FirstCol,(colMap.get("columnTitle").toString()+"("+colMap.get("allV").toString()+")").getBytes().length*2*256);

            //最后两列（成绩总计，名次总计）
            if(j == colList.size()-1){
                //成绩总计并2行
                CellRangeAddress region3 = new CellRangeAddress(0, 1, row1FirstCol, row1FirstCol+1);// 下标从0开始 起始行号，终止行号， 起始列号，终止列号
                sheet.addMergedRegion(region3);
                //名次总计合并2行
                CellRangeAddress region4 = new CellRangeAddress(0, 1, row1FirstCol+2, row1FirstCol+3);// 下标从0开始 起始行号，终止行号， 起始列号，终止列号
                sheet.addMergedRegion(region4);

                //成绩总计
                XSSFCell row1CjCell = newRow1.createCell(row2FirstCol+1);
                row1CjCell.setCellStyle(cellStyle);
                row1CjCell.setCellValue("成绩总计");

                //名次总计
                XSSFCell row1McCell = newRow1.createCell(row2FirstCol+2);
                row1McCell.setCellStyle(cellStyle);
                row1McCell.setCellValue("名次总计");

            }
            //第二行职级（加权分）
            for (Map<String,Object> rankMap:rankList){
                String rankId = rankMap.get("rankId").toString();

                //第三个单元格及之后
                XSSFCell row2celln = newRow2.createCell(row2FirstCol);
                row2celln.setCellStyle(cellStyle);
                row2celln.setCellValue(rankMap.get("rankName").toString());

                //第3行数据之后循环
                int startHs = 2;//从行3开始
                for(int i = 0;i<valueMapList.size();i++){
                    Map<String,Object> valueMap = valueMapList.get(i);
                    if(row2FirstCol == 2) {
                        XSSFRow newRow = sheet.createRow(startHs);// 从第3行开始累加
                        newRow.setHeight((short) (20 * 20));// 设置行高 基数为20
                        // 第一列：编号
                        XSSFCell cell1 = newRow.createCell(0);
                        cell1.setCellStyle(cellStyle);
                        cell1.setCellValue(valueMap.get("objUserId").toString());

                        // 第二列：名称
                        XSSFCell cell2 = newRow.createCell(1);
                        cell2.setCellStyle(cellStyle);
                        cell2.setCellValue(valueMap.get("objUserName").toString());

                        // 第三列：加权分
                        XSSFCell cell3 = newRow.createCell(2);
                        cell3.setCellStyle(cellStyle);
                        cell3.setCellValue(valueMap.get(columnId+"_"+rankId+"_jq").toString());
                    }else {
                        XSSFRow newRow = sheet.getRow(startHs);
                        // 第三列：加权分
                        XSSFCell cell3n = newRow.createCell(row2FirstCol);
                        cell3n.setCellStyle(cellStyle);
                        cell3n.setCellValue(valueMap.get(columnId+"_"+rankId+"_jq").toString());

                        //最后两列（成绩总计，名次总计）
                        if(j == colList.size()-1){

                            // 成绩总计列
                            XSSFCell cellCj = newRow.createCell(row2FirstCol+1);
                            cellCj.setCellStyle(cellStyle);
                            cellCj.setCellValue(valueMap.get("jqzj").toString());

                            // 名次总计列
                            XSSFCell cellMc = newRow.createCell(row2FirstCol+2);
                            cellMc.setCellStyle(cellStyle);
                            cellMc.setCellValue("0");
                        }

                    }

                    startHs++;
                }

                row2FirstCol++;

            }
        }

    }

    public void setCellStyle(XSSFWorkbook hWorkbook){
        XSSFCellStyle cellStyle = hWorkbook.createCellStyle();
//        cellStyle.setAlignment(CellStyle.class.);//左右居中
//        cellStyle.setVerticalAlignment(sxjz);//上下居中
//        cellStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
//        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
//        cellStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框
//        cellStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框
//        cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(156, 195, 230)));//设置背景色
//        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//填充模式



    }
}
