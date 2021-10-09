package com.ruoyi.project.userbackground.cpStatisResult.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.userbackground.cpStatisResult.domain.CpStatisResult;

/**
 * 测评信息结果Service接口
 * 
 * @author ying
 * @date 2021-09-12
 */
public interface ICpStatisResultService 
{
    /**
     * 查询测评信息结果
     * 
     * @param id 测评信息结果主键
     * @return 测评信息结果
     */
    public CpStatisResult selectCpStatisResultById(String id);

    /**
     * 查询测评信息结果列表
     * 
     * @param cpStatisResult 测评信息结果
     * @return 测评信息结果集合
     */
    public List<CpStatisResult> selectCpStatisResultList(CpStatisResult cpStatisResult);

    /**
     * 新增测评信息结果
     * 
     * @param cpStatisResult 测评信息结果
     * @return 结果
     */
    public int insertCpStatisResult(CpStatisResult cpStatisResult);

    /**
     * 修改测评信息结果
     * 
     * @param cpStatisResult 测评信息结果
     * @return 结果
     */
    public int updateCpStatisResult(CpStatisResult cpStatisResult);

    /**
     * 批量删除测评信息结果
     * 
     * @param ids 需要删除的测评信息结果主键集合
     * @return 结果
     */
    public int deleteCpStatisResultByIds(String ids);

    /**
     * 删除测评信息结果信息
     * 
     * @param id 测评信息结果主键
     * @return 结果
     */
    public int deleteCpStatisResultById(String id);

    /**
     * 批量保存测评信息结果
     * @param cpInfoList
     */
    public void batchInsertCpStatisResult(List<CpStatisResult> cpInfoList);

    /**
     * 获取部门测评结果
     * @param activityId
     * @param sheetId
     * @param sheetType
     * @return
     */
    public List<Map<String,Object>> selectAvgByActIdShIdShType(Long activityId,Long sheetId,Long sheetType);

    /**
     * 获取部门表格的统计数据
     * @param activityId
     * @param sheetId
     * @return
     */
    public Map<String,Object> selectDeptStatisResult(Long activityId,Long sheetId,Long isnotRank);

    /**
     * 获取个人表格的统计数据
     * @param activityId
     * @param sheetId
     * @return
     */
    public Map<String,Object> selectUserStatisResult(Long activityId,Long sheetId,Long isnotRank);
    
    /**
     * 	原始数据
     * @param activityId
     * @param sheetId
     * @param objType-----0:个人；1:部门
     * @return
     */
    public List<Map<String,Object>> selectOriDataResult(Long activityId,Long sheetId,String objType);
    
}
