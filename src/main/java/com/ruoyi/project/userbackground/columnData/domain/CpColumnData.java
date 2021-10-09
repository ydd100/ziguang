package com.ruoyi.project.userbackground.columnData.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 测评表格-列对象 cp_column_data
 * 
 * @author ying
 * @date 2021-09-05
 */
public class CpColumnData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long columnDataId;

    /** 测评表格id */
    @Excel(name = "测评表格id")
    private Long sheetId;

    /** 列标题 */
    @Excel(name = "列标题")
    private String columnTitle;

    /** 列值 */
    @Excel(name = "列值")
    private String columnValue;

    /** 排序 */
    @Excel(name = "排序")
    private String columnSort;

    public void setColumnDataId(Long columnDataId)
    {
        this.columnDataId = columnDataId;
    }

    public Long getColumnDataId()
    {
        return columnDataId;
    }
    public void setSheetId(Long sheetId)
    {
        this.sheetId = sheetId;
    }

    public Long getSheetId()
    {
        return sheetId;
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
    public void setColumnSort(String columnSort)
    {
        this.columnSort = columnSort;
    }

    public String getColumnSort()
    {
        return columnSort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("columnDataId", getColumnDataId())
            .append("sheetId", getSheetId())
            .append("columnTitle", getColumnTitle())
            .append("columnValue", getColumnValue())
            .append("columnSort", getColumnSort())
            .append("createTime", getCreateTime())
            .toString();
    }

	public CpColumnData() {
		super();
	}

	public CpColumnData(CpColumnData cpColumnData) {
		super();
		this.columnTitle = cpColumnData.getColumnTitle();
		this.columnValue = cpColumnData.getColumnValue();
		this.columnSort = cpColumnData.getColumnSort();
	}
    
    
}
