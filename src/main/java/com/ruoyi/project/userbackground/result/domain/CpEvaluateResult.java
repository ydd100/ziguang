package com.ruoyi.project.userbackground.result.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 活动表格关联对象 cp_evaluate_result
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
public class CpEvaluateResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long evaluateResultId;

    /** 活动id */
    @Excel(name = "活动id")
    private Long activityId;

    /**  */
    @Excel(name = "")
    private Long columnDataId;

    /**  */
    @Excel(name = "")
    private Long evaluateItemId;

    /** 表格id */
    @Excel(name = "表格id")
    private Long sheetId;

    public void setEvaluateResultId(Long evaluateResultId)
    {
        this.evaluateResultId = evaluateResultId;
    }

    public Long getEvaluateResultId()
    {
        return evaluateResultId;
    }
    public void setActivityId(Long activityId)
    {
        this.activityId = activityId;
    }

    public Long getActivityId()
    {
        return activityId;
    }
    public void setColumnDataId(Long columnDataId)
    {
        this.columnDataId = columnDataId;
    }

    public Long getColumnDataId()
    {
        return columnDataId;
    }
    public void setEvaluateItemId(Long evaluateItemId)
    {
        this.evaluateItemId = evaluateItemId;
    }

    public Long getEvaluateItemId()
    {
        return evaluateItemId;
    }
    public void setSheetId(Long sheetId)
    {
        this.sheetId = sheetId;
    }

    public Long getSheetId()
    {
        return sheetId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("evaluateResultId", getEvaluateResultId())
            .append("activityId", getActivityId())
            .append("columnDataId", getColumnDataId())
            .append("evaluateItemId", getEvaluateItemId())
            .append("sheetId", getSheetId())
            .toString();
    }
}
