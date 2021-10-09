package com.ruoyi.project.userbackground.cpStatisResult.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.userbackground.cpStatisResult.domain.CpStatisResult;
import org.apache.ibatis.annotations.Param;

/**
 * 测评信息结果Mapper接口
 * 
 * @author ying
 * @date 2021-09-12
 */
public interface CpStatisResultMapper 
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
     * 删除测评信息结果
     * 
     * @param id 测评信息结果主键
     * @return 结果
     */
    public int deleteCpStatisResultById(String id);

    /**
     * 批量删除测评信息结果
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCpStatisResultByIds(String[] ids);

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
    public List<Map<String,Object>> selectAvgByActIdShIdShType(@Param("activityId")Long activityId,@Param("sheetId")Long sheetId,@Param("sheetType")Long sheetType);

    /*************统计分步骤做start***********************/
    /**
     * 1.查询表格项
     * @param map
     * @return
     */
    public List<Map<String,Object>> selectColListByActIdShId(Map<String,Object> map);

    /**
     * 2.(活动开启职级)查询测评过的职级
     * @param map
     * @return
     */
    public List<Map<String,Object>> selectRankListByActIdShId(Map<String,Object> map);

    /**
     * 3.查询测评对象是部门的均值和加权分数(按部门排序，是为了将相同部门放到一起，便于处理数据)
     * @param map
     * @return
     */
    public List<Map<String,Object>> selectDeptResultByActIdShId(Map<String,Object> map);

    /**
     * 3.查询测评对象是个人的均值和加权分数(按人员排序，是为了将相同部门放到一起，便于处理数据)
     * @param map
     * @return
     */
    public List<Map<String,Object>> selectUserResultByActIdShId(Map<String,Object> map);

    /**
     * 3.(不开职级)查询测评对象是部门的均值和加权分数(按部门排序，是为了将相同部门放到一起，便于处理数据)
     * @param map
     * @return
     */
    public List<Map<String,Object>> selectDeptResultByActIdShIdNoRank(Map<String,Object> map);

    /**
     * 3.(不开职级)查询测评对象是个人的均值和加权分数(按人员排序，是为了将相同部门放到一起，便于处理数据)
     * @param map
     * @return
     */
    public List<Map<String,Object>> selectUserResultByActIdShIdNoRank(Map<String,Object> map);

    /*************统计分步骤做end***********************/
    
    /**
     *	 原始数据===部门表格
     * @param map
     * @return
     */
    public List<Map<String,Object>> selectOriDeptResult(Map<String,Object> map);
    
    /**
     * 原始数据===个人表格
     * @param map
     * @return
     */
    public List<Map<String,Object>> selectOriUserResult(Map<String,Object> map);

}
