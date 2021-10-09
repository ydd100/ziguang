package com.ruoyi.project.userbackground.rules.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.userbackground.rules.domain.CpSheetScoringRules;

/**
 * 计分规则Mapper接口
 * 
 * @author ruoyi
 * @date 2021-09-06
 */
public interface CpSheetScoringRulesMapper 
{
    /**
     * 查询计分规则
     * 
     * @param id 计分规则主键
     * @return 计分规则
     */
    public CpSheetScoringRules selectCpSheetScoringRulesById(String id);

    /**
     * 查询计分规则列表
     * 
     * @param cpSheetScoringRules 计分规则
     * @return 计分规则集合
     */
    public List<CpSheetScoringRules> selectCpSheetScoringRulesList(CpSheetScoringRules cpSheetScoringRules);

    /**
     * 新增计分规则
     * 
     * @param cpSheetScoringRules 计分规则
     * @return 结果
     */
    public int insertCpSheetScoringRules(CpSheetScoringRules cpSheetScoringRules);

    /**
     * 修改计分规则
     * 
     * @param cpSheetScoringRules 计分规则
     * @return 结果
     */
    public int updateCpSheetScoringRules(CpSheetScoringRules cpSheetScoringRules);

    /**
     * 删除计分规则
     * 
     * @param id 计分规则主键
     * @return 结果
     */
    public int deleteCpSheetScoringRulesById(String id);

    /**
     * 批量删除计分规则
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCpSheetScoringRulesByIds(String[] ids);
    
    /**
     * 查询计分规则列表
     * @param sheetId
     * @param sheetType
     * @return
     */
    public List<Map<String,Object>> selectSheetScoreRulesBySheetIdTypeActId(@Param("sheetId")Long sheetId,@Param("sheetType")Long sheetType,@Param("activityId")Long activityId);
    
    /**
     * 根据多条件删除计分规则
     * @param cpSheetScoringRules
     */
    public void delSheetScoreRulesByMore(CpSheetScoringRules cpSheetScoringRules);
    public List<CpSheetScoringRules> selectrankbydeptidandactid(@Param("sheetId") Long sheetId, @Param("deptId") String deptId, @Param("activityId") Long activityId);
    
    /**
     * 	计分规则又改了
     * @param cpSheetScoringRules
     * @return
     */
    public List<Map<String,Object>> selectRankScoreRulesByActIdSid(CpSheetScoringRules cpSheetScoringRules);

}
