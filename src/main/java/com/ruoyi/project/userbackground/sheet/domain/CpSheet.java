package com.ruoyi.project.userbackground.sheet.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 测评表格对象 cp_sheet
 * 
 * @author ying
 * @date 2021-09-05
 */
public class CpSheet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 填表说明 */
    @Excel(name = "填表说明")
    private String tableExplain;

    /** 对象类型(0:个人 1：单位/部门) */
    @Excel(name = "对象类型",  readConverterExp = "0=个人,1=单位/部门")
    private String objType;

    /** 评价类型（0：正向评价，1：反向评价） */
    @Excel(name = "评价类型", readConverterExp = "0=正向评价,1=反向评价")
    private String evaType;

    /** 封面图片 */
    @Excel(name = "封面图片")
    private String coverPath;

    /** 背景图片 */
    @Excel(name = "背景图片")
    private String backPath;

    /** 主题（不确定，预留） */
    @Excel(name = "主题")
    private String subjectPath;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long operateId;
	
	/** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String operateName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setTableExplain(String tableExplain)
    {
        this.tableExplain = tableExplain;
    }

    public String getTableExplain()
    {
        return tableExplain;
    }
    public void setObjType(String objType)
    {
        this.objType = objType;
    }

    public String getObjType()
    {
        return objType;
    }
    public void setEvaType(String evaType)
    {
        this.evaType = evaType;
    }

    public String getEvaType()
    {
        return evaType;
    }
    public void setCoverPath(String coverPath)
    {
        this.coverPath = coverPath;
    }

    public String getCoverPath()
    {
        return coverPath;
    }
    public void setBackPath(String backPath)
    {
        this.backPath = backPath;
    }

    public String getBackPath()
    {
        return backPath;
    }
    public void setSubjectPath(String subjectPath)
    {
        this.subjectPath = subjectPath;
    }

    public String getSubjectPath()
    {
        return subjectPath;
    }
    public void setOperateId(Long operateId)
    {
        this.operateId = operateId;
    }

    public Long getOperateId()
    {
        return operateId;
    }

    public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("tableExplain", getTableExplain())
            .append("objType", getObjType())
            .append("evaType", getEvaType())
            .append("coverPath", getCoverPath())
            .append("backPath", getBackPath())
            .append("subjectPath", getSubjectPath())
            .append("operateId", getOperateId())
            .append("operateName", getOperateName())
            .append("createTime", getCreateTime())
            .toString();
    }

	public CpSheet(Long id, String name, String tableExplain, String objType, String evaType, String coverPath,
			String backPath, String subjectPath, Long operateId, String operateName) {
		super();
		this.id = id;
		this.name = name;
		this.tableExplain = tableExplain;
		this.objType = objType;
		this.evaType = evaType;
		this.coverPath = coverPath;
		this.backPath = backPath;
		this.subjectPath = subjectPath;
		this.operateId = operateId;
		this.operateName = operateName;
	}

	public CpSheet() {
		super();
	}
	
	public CpSheet(CpSheet cpSheet) {
		super();
		this.name = cpSheet.getName();
		this.tableExplain = cpSheet.getTableExplain();
		this.objType = cpSheet.getObjType();
		this.evaType = cpSheet.getEvaType();
		this.coverPath = cpSheet.getCoverPath();
		this.backPath = cpSheet.getBackPath();
		this.subjectPath = cpSheet.getSubjectPath();
		this.operateId = cpSheet.getOperateId();
	}
	
	
}
