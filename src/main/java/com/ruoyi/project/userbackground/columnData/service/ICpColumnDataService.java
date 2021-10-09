package com.ruoyi.project.userbackground.columnData.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.userbackground.columnData.domain.CpColumnData;

/**
 * 测评表格-列Service接口
 * 
 * @author ying
 * @date 2021-09-05
 */
public interface ICpColumnDataService 
{
    /**
     * 查询测评表格-列
     * 
     * @param columnDataId 测评表格-列主键
     * @return 测评表格-列
     */
    public CpColumnData selectCpColumnDataByColumnDataId(Long columnDataId);

    /**
     * 查询测评表格-列列表
     * 
     * @param cpColumnData 测评表格-列
     * @return 测评表格-列集合
     */
    public List<CpColumnData> selectCpColumnDataList(CpColumnData cpColumnData);

    /**
     * 新增测评表格-列
     * 
     * @param cpColumnData 测评表格-列
     * @return 结果
     */
    public int insertCpColumnData(CpColumnData cpColumnData);

    /**
     * 修改测评表格-列
     * 
     * @param cpColumnData 测评表格-列
     * @return 结果
     */
    public int updateCpColumnData(CpColumnData cpColumnData);

    /**
     * 批量删除测评表格-列
     * 
     * @param columnDataIds 需要删除的测评表格-列主键集合
     * @return 结果
     */
    public int deleteCpColumnDataByColumnDataIds(String columnDataIds);

    /**
     * 删除测评表格-列信息
     * 
     * @param columnDataId 测评表格-列主键
     * @return 结果
     */
    public int deleteCpColumnDataByColumnDataId(Long columnDataId);
    
    /**
     * 根据sheet_id获取column的最大排序的序号
     * 
     * @param sheetId
     * @return
     */
    public Long selectMaxSortGroupBySheetId(Long sheetId);
    
    /**
     * 根据sheetId删除column_data
     * @param sheetId
     * @return
     */
    public int deleteCpColumnDataBySheetId(Long sheetId);
    
    /**
     * 根据sheetId查询column_data和eva_item组合列表
     * @param sheetId
     * @return
     */
    public List<Map<String,Object>> selectCpColumnDataBySheetId(Long sheetId);
    
    /**
     * 根据sheetId查询column_data数据
     * @param sheetId
     * @return
     */
    public List<CpColumnData> selectColDataBySheetId(Long sheetId);
}
