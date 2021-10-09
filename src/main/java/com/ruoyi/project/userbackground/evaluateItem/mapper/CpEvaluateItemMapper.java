package com.ruoyi.project.userbackground.evaluateItem.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.userbackground.evaluateItem.domain.CpEvaluateItem;

/**
 * 测评表格-选项Mapper接口
 * 
 * @author ying
 * @date 2021-09-05
 */
public interface CpEvaluateItemMapper 
{
    /**
     * 查询测评表格-选项
     * 
     * @param evaluateItemId 测评表格-选项主键
     * @return 测评表格-选项
     */
    public CpEvaluateItem selectCpEvaluateItemByEvaluateItemId(Long evaluateItemId);

    /**
     * 查询测评表格-选项列表
     * 
     * @param cpEvaluateItem 测评表格-选项
     * @return 测评表格-选项集合
     */
    public List<CpEvaluateItem> selectCpEvaluateItemList(CpEvaluateItem cpEvaluateItem);

    /**
     * 新增测评表格-选项
     * 
     * @param cpEvaluateItem 测评表格-选项
     * @return 结果
     */
    public int insertCpEvaluateItem(CpEvaluateItem cpEvaluateItem);

    /**
     * 修改测评表格-选项
     * 
     * @param cpEvaluateItem 测评表格-选项
     * @return 结果
     */
    public int updateCpEvaluateItem(CpEvaluateItem cpEvaluateItem);

    /**
     * 删除测评表格-选项
     * 
     * @param evaluateItemId 测评表格-选项主键
     * @return 结果
     */
    public int deleteCpEvaluateItemByEvaluateItemId(Long evaluateItemId);

    /**
     * 批量删除测评表格-选项
     * 
     * @param evaluateItemIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCpEvaluateItemByEvaluateItemIds(String[] evaluateItemIds);
    
    /**
     * 根据sheetId删除eval_item
     * @param sheetId
     * @return
     */
    public int deleteCpEvaluateItemBySheetId(Long sheetId);
    
    /**
     * 根据sheetId查询eval_item
     * @param sheetId
     * @return
     */
    public List<CpEvaluateItem> selectCpEvaluateItemBySheetId(Long sheetId);
    
    /**
     * 根据column_data_id查询列对应项
     * @param columnDataId
     * @return
     */
    public List<CpEvaluateItem> selectCpEvaluateItemByColDataId(Long columnDataId);
    
    /**
     * 批量保存eva_item
     * @param map
     * @return
     */
    public void batchInsertEvaItem(Map<String,Object> map);
    
    /**
     * 根据批量的column_data_id批量删除选项
     * @param columnDataIds
     * @return
     */
    public int deleteCpEvaluateItemByColumnDataIds(String[] columnDataIds);
    
    /**
     * 根据column_data_id删除eva_item
     * @param columnDataId
     * @return
     */
    public int deleteCpEvaluateItemByColumnDataId(Long columnDataId);
    
    /**
     * 根据sheetId和groupTitle查询colDataId
     * @param sheetId
     * @param groupTitle
     * @return
     */
    public List<Map<String,Long>> selectColDataIdListBySheetIdGroupTitle(Long sheetId,String groupTitle);
}
