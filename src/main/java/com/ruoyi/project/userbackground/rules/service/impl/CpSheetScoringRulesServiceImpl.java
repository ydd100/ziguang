package com.ruoyi.project.userbackground.rules.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.userbackground.rules.mapper.CpSheetScoringRulesMapper;
import com.ruoyi.project.userbackground.rules.domain.CpSheetScoringRules;
import com.ruoyi.project.userbackground.rules.service.ICpSheetScoringRulesService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 计分规则Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-06
 */
@Service
public class CpSheetScoringRulesServiceImpl implements ICpSheetScoringRulesService {
	@Autowired
	private CpSheetScoringRulesMapper cpSheetScoringRulesMapper;

	/**
	 * 查询计分规则
	 * 
	 * @param id 计分规则主键
	 * @return 计分规则
	 */
	@Override
	public CpSheetScoringRules selectCpSheetScoringRulesById(String id) {
		return cpSheetScoringRulesMapper.selectCpSheetScoringRulesById(id);
	}

	/**
	 * 查询计分规则列表
	 * 
	 * @param cpSheetScoringRules 计分规则
	 * @return 计分规则
	 */
	@Override
	public List<CpSheetScoringRules> selectCpSheetScoringRulesList(CpSheetScoringRules cpSheetScoringRules) {
		return cpSheetScoringRulesMapper.selectCpSheetScoringRulesList(cpSheetScoringRules);
	}

	/**
	 * 新增计分规则
	 * 
	 * @param cpSheetScoringRules 计分规则
	 * @return 结果
	 */
	@Override
	public int insertCpSheetScoringRules(CpSheetScoringRules cpSheetScoringRules) {
		cpSheetScoringRules.setCreateTime(DateUtils.getNowDate());
		return cpSheetScoringRulesMapper.insertCpSheetScoringRules(cpSheetScoringRules);
	}

	/**
	 * 修改计分规则
	 * 
	 * @param cpSheetScoringRules 计分规则
	 * @return 结果
	 */
	@Override
	public int updateCpSheetScoringRules(CpSheetScoringRules cpSheetScoringRules) {
		return cpSheetScoringRulesMapper.updateCpSheetScoringRules(cpSheetScoringRules);
	}

	/**
	 * 批量删除计分规则
	 * 
	 * @param ids 需要删除的计分规则主键
	 * @return 结果
	 */
	@Override
	public int deleteCpSheetScoringRulesByIds(String ids) {
		return cpSheetScoringRulesMapper.deleteCpSheetScoringRulesByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除计分规则信息
	 * 
	 * @param id 计分规则主键
	 * @return 结果
	 */
	@Override
	public int deleteCpSheetScoringRulesById(String id) {
		return cpSheetScoringRulesMapper.deleteCpSheetScoringRulesById(id);
	}

	/**
	 * 查询计分规则列表
	 * 
	 * @param sheetId
	 * @param sheetType
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectSheetScoreRulesBySheetIdTypeActId(Long sheetId, Long sheetType,
			Long activityId) {
		return cpSheetScoringRulesMapper.selectSheetScoreRulesBySheetIdTypeActId(sheetId, sheetType, activityId);
	}

	/**
     * 根据多条件删除计分规则
     * @param cpSheetScoringRules
     */
	@Override
	public void delSheetScoreRulesByMore(CpSheetScoringRules cpSheetScoringRules) {
		cpSheetScoringRulesMapper.delSheetScoreRulesByMore(cpSheetScoringRules);
	}

	@Override
	public List<CpSheetScoringRules> selectrankbydeptidandactid(Long sheetId, String deptId, Long activityId) {
		return cpSheetScoringRulesMapper.selectrankbydeptidandactid(sheetId,deptId,activityId);
	}

	/**
     * 	计分规则又改了
     * @param cpSheetScoringRules
     * @return
     */
	@Override
	public List<Map<String, Object>> selectRankScoreRulesByActIdSid(CpSheetScoringRules cpSheetScoringRules) {
		return cpSheetScoringRulesMapper.selectRankScoreRulesByActIdSid(cpSheetScoringRules);
	}
}
