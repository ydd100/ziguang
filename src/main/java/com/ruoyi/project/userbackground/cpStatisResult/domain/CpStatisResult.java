package com.ruoyi.project.userbackground.cpStatisResult.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 测评信息结果对象 cp_statis_result
 * 
 * @author ying
 * @date 2021-09-12
 */
public class CpStatisResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 活动id */
    @Excel(name = "活动id")
    private Long activityId;

    /** 表格id */
    @Excel(name = "表格id")
    private Long sheetId;

    /** 项id */
    @Excel(name = "项id")
    private Long columnId;

    /** 项名称 */
    @Excel(name = "项名称")
    private String columnTitle;

    /** 项值 */
    @Excel(name = "项值")
    private String columnValue;
    
    /** 项顺序 */
    @Excel(name = "项顺序")
    private String columnSort;

    /** 职级id */
    @Excel(name = "职级id")
    private String rankId;

    /** 被测部门id */
    @Excel(name = "被测部门id")
    private String objDeptId;

    /** 被测对象id */
    @Excel(name = "被测对象id")
    private String objUserId;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setActivityId(Long activityId)
    {
        this.activityId = activityId;
    }

    public Long getActivityId()
    {
        return activityId;
    }
    public void setSheetId(Long sheetId)
    {
        this.sheetId = sheetId;
    }

    public Long getSheetId()
    {
        return sheetId;
    }
    public void setColumnId(Long columnId)
    {
        this.columnId = columnId;
    }

    public Long getColumnId()
    {
        return columnId;
    }
    public void setColumnTitle(String columnTitle)
    {
        this.columnTitle = columnTitle;
    }

    public String getColumnTitle()
    {
        return columnTitle;
    }
    public void setColumnValue(String columnValue)
    {
        this.columnValue = columnValue;
    }

    public String getColumnValue()
    {
        return columnValue;
    }
    
    public String getColumnSort() {
		return columnSort;
	}

	public void setColumnSort(String columnSort) {
		this.columnSort = columnSort;
	}

	public void setRankId(String rankId)
    {
        this.rankId = rankId;
    }

    public String getRankId()
    {
        return rankId;
    }
    public void setObjDeptId(String objDeptId)
    {
        this.objDeptId = objDeptId;
    }

    public String getObjDeptId()
    {
        return objDeptId;
    }
    public void setObjUserId(String objUserId)
    {
        this.objUserId = objUserId;
    }

    public String getObjUserId()
    {
        return objUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("activityId", getActivityId())
            .append("sheetId", getSheetId())
            .append("columnId", getColumnId())
            .append("columnTitle", getColumnTitle())
            .append("columnValue", getColumnValue())
            .append("columnSort", getColumnSort())
            .append("rankId", getRankId())
            .append("objDeptId", getObjDeptId())
            .append("objUserId", getObjUserId())
            .toString();
    }
}
