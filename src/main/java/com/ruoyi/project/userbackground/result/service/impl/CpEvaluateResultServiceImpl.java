package com.ruoyi.project.userbackground.result.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.userbackground.result.mapper.CpEvaluateResultMapper;
import com.ruoyi.project.userbackground.result.domain.CpEvaluateResult;
import com.ruoyi.project.userbackground.result.service.ICpEvaluateResultService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 活动表格关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
@Service
public class CpEvaluateResultServiceImpl implements ICpEvaluateResultService 
{
    @Autowired
    private CpEvaluateResultMapper cpEvaluateResultMapper;

    /**
     * 查询活动表格关联
     * 
     * @param evaluateResultId 活动表格关联主键
     * @return 活动表格关联
     */
    @Override
    public CpEvaluateResult selectCpEvaluateResultByEvaluateResultId(Long evaluateResultId)
    {
        return cpEvaluateResultMapper.selectCpEvaluateResultByEvaluateResultId(evaluateResultId);
    }

    @Override
    public List<CpEvaluateResult> selectCpEvaluateResultByact(Long actid) {
        return cpEvaluateResultMapper.selectCpEvaluateResultByact(actid);
    }

    /**
     * 查询活动表格关联列表
     * 
     * @param cpEvaluateResult 活动表格关联
     * @return 活动表格关联
     */
    @Override
    public List<CpEvaluateResult> selectCpEvaluateResultList(CpEvaluateResult cpEvaluateResult)
    {
        return cpEvaluateResultMapper.selectCpEvaluateResultList(cpEvaluateResult);
    }

    /**
     * 新增活动表格关联
     * 
     * @param cpEvaluateResult 活动表格关联
     * @return 结果
     */
    @Override
    public int insertCpEvaluateResult(CpEvaluateResult cpEvaluateResult)
    {
        return cpEvaluateResultMapper.insertCpEvaluateResult(cpEvaluateResult);
    }

    /**
     * 修改活动表格关联
     * 
     * @param cpEvaluateResult 活动表格关联
     * @return 结果
     */
    @Override
    public int updateCpEvaluateResult(CpEvaluateResult cpEvaluateResult)
    {
        return cpEvaluateResultMapper.updateCpEvaluateResult(cpEvaluateResult);
    }

    /**
     * 批量删除活动表格关联
     * 
     * @param evaluateResultIds 需要删除的活动表格关联主键
     * @return 结果
     */
    @Override
    public int deleteCpEvaluateResultByEvaluateResultIds(String evaluateResultIds)
    {
        return cpEvaluateResultMapper.deleteCpEvaluateResultByEvaluateResultIds(Convert.toStrArray(evaluateResultIds));
    }

    @Override
    public int deleteCpEvaluateResultByactId(Long actid) {
        return cpEvaluateResultMapper.deleteCpEvaluateResultByactId(actid);
    }

    /**
     * 删除活动表格关联信息
     * 
     * @param evaluateResultId 活动表格关联主键
     * @return 结果
     */
    @Override
    public int deleteCpEvaluateResultByEvaluateResultId(Long evaluateResultId)
    {
        return cpEvaluateResultMapper.deleteCpEvaluateResultByEvaluateResultId(evaluateResultId);
    }
}
