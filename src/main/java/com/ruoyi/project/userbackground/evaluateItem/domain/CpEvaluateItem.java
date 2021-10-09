package com.ruoyi.project.userbackground.evaluateItem.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 测评表格-选项对象 cp_evaluate_item
 * 
 * @author ying
 * @date 2021-09-05
 */
public class CpEvaluateItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long evaluateItemId;

    /**  */
    @Excel(name = "")
    private Long sheetId;

    /** 项参数 */
    @Excel(name = "项参数")
    private String evaluateTitle;

    /** 项名称 */
    @Excel(name = "项名称")
    private String evaluateValue;

    /** 项数据 */
    @Excel(name = "项数据")
    private String evaluateData;

    /** 排序 */
    @Excel(name = "排序")
    private String evaluateSort;

    /** 分组名称 */
    @Excel(name = "分组名称")
    private String groupTitle;
    
    @Excel(name = "columnDataId")
    private Long columnDataId;

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
    public void setEvaluateTitle(String evaluateTitle)
    {
        this.evaluateTitle = evaluateTitle;
    }

    public String getEvaluateTitle()
    {
        return evaluateTitle;
    }
    public void setEvaluateValue(String evaluateValue)
    {
        this.evaluateValue = evaluateValue;
    }

    public String getEvaluateValue()
    {
        return evaluateValue;
    }
    public void setEvaluateData(String evaluateData)
    {
        this.evaluateData = evaluateData;
    }

    public String getEvaluateData()
    {
        return evaluateData;
    }
    public void setEvaluateSort(String evaluateSort)
    {
        this.evaluateSort = evaluateSort;
    }

    public String getEvaluateSort()
    {
        return evaluateSort;
    }
    public void setGroupTitle(String groupTitle)
    {
        this.groupTitle = groupTitle;
    }

    public String getGroupTitle()
    {
        return groupTitle;
    }

    public Long getColumnDataId() {
		return columnDataId;
	}

	public void setColumnDataId(Long columnDataId) {
		this.columnDataId = columnDataId;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("evaluateItemId", getEvaluateItemId())
            .append("sheetId", getSheetId())
            .append("evaluateTitle", getEvaluateTitle())
            .append("evaluateValue", getEvaluateValue())
            .append("evaluateData", getEvaluateData())
            .append("evaluateSort", getEvaluateSort())
            .append("groupTitle", getGroupTitle())
            .append("columnDataId", getColumnDataId())
            .toString();
    }

	public CpEvaluateItem(Long evaluateItemId, Long sheetId, String evaluateTitle, String evaluateValue,
			String evaluateData, String evaluateSort, String groupTitle, Long columnDataId) {
		super();
		this.evaluateItemId = evaluateItemId;
		this.sheetId = sheetId;
		this.evaluateTitle = evaluateTitle;
		this.evaluateValue = evaluateValue;
		this.evaluateData = evaluateData;
		this.evaluateSort = evaluateSort;
		this.groupTitle = groupTitle;
		this.columnDataId = columnDataId;
	}

	public CpEvaluateItem() {
		super();
	}
	
	public CpEvaluateItem(CpEvaluateItem cpEvaluateItem) {
		super();
		this.evaluateValue = cpEvaluateItem.getEvaluateValue();
		this.evaluateData = cpEvaluateItem.getEvaluateData();
		this.evaluateSort = cpEvaluateItem.getEvaluateSort();
	}
	
	
}
