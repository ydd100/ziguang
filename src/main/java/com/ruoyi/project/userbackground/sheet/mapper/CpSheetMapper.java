package com.ruoyi.project.userbackground.sheet.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.userbackground.sheet.domain.CpSheet;
import org.apache.ibatis.annotations.Param;

/**
 * 测评表格Mapper接口
 * 
 * @author ying
 * @date 2021-09-02
 */
public interface CpSheetMapper 
{
    /**
     * 查询测评表格
     * 
     * @param id 测评表格主键
     * @return 测评表格
     */
    public CpSheet selectCpSheetById(Long id);

    /**
     * 查询测评表格列表
     * 
     * @param cpSheet 测评表格
     * @return 测评表格集合
     */
    public List<CpSheet> selectCpSheetList(CpSheet cpSheet);

    /**
     * 新增测评表格
     * 
     * @param cpSheet 测评表格
     * @return 结果
     */
    public int insertCpSheet(CpSheet cpSheet);

    /**
     * 修改测评表格
     * 
     * @param cpSheet 测评表格
     * @return 结果
     */
    public int updateCpSheet(CpSheet cpSheet);

    /**
     * 删除测评表格
     * 
     * @param id 测评表格主键
     * @return 结果
     */
    public int deleteCpSheetById(Long id);

    /**
     * 批量删除测评表格
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCpSheetByIds(String[] ids);
    
    /**
     * 根据活动activityId查询测评表格sheet
     * @param activityId
     * @return
     */
    public List<Map<String,Object>> selectSheetListByActivityId(Long activityId);
    public List<Map<String,Object>> selectSheetListByActivityId1(@Param("activityId") Long activityId, @Param("sIds") Long[] sIds);

}
