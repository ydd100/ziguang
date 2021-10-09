package com.ruoyi.project.userbackground.result.service;

import java.util.List;
import com.ruoyi.project.userbackground.result.domain.CpEvaluateResult;

/**
 * 活动表格关联Service接口
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
public interface ICpEvaluateResultService 
{
    /**
     * 查询活动表格关联
     * 
     * @param evaluateResultId 活动表格关联主键
     * @return 活动表格关联
     */
    public CpEvaluateResult selectCpEvaluateResultByEvaluateResultId(Long evaluateResultId);

    public List<CpEvaluateResult> selectCpEvaluateResultByact(Long actid);

    /**
     * 查询活动表格关联列表
     * 
     * @param cpEvaluateResult 活动表格关联
     * @return 活动表格关联集合
     */
    public List<CpEvaluateResult> selectCpEvaluateResultList(CpEvaluateResult cpEvaluateResult);

    /**
     * 新增活动表格关联
     * 
     * @param cpEvaluateResult 活动表格关联
     * @return 结果
     */
    public int insertCpEvaluateResult(CpEvaluateResult cpEvaluateResult);

    /**
     * 修改活动表格关联
     * 
     * @param cpEvaluateResult 活动表格关联
     * @return 结果
     */
    public int updateCpEvaluateResult(CpEvaluateResult cpEvaluateResult);

    /**
     * 批量删除活动表格关联
     * 
     * @param evaluateResultIds 需要删除的活动表格关联主键集合
     * @return 结果
     */
    public int deleteCpEvaluateResultByEvaluateResultIds(String evaluateResultIds);

    public int deleteCpEvaluateResultByactId(Long actid);

    /**
     * 删除活动表格关联信息
     * 
     * @param evaluateResultId 活动表格关联主键
     * @return 结果
     */
    public int deleteCpEvaluateResultByEvaluateResultId(Long evaluateResultId);
}
