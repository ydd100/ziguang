package com.ruoyi.project.userbackground.participant.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 表格测评对象 cp_sheet_obj_participant
 * 
 * @author ruoyi
 * @date 2021-09-06
 */
public class CpSheetObjParticipant extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 表格id */
    @Excel(name = "表格id")
    private Long sheetId;

    /** 测评对象 */
    @Excel(name = "测评对象")
    private String objUserId;

    /** 参与者id */
    @Excel(name = "参与者id")
    private String participantUserId;

    /** 部门id */
    @Excel(name = "部门id")
    private String deptId;

    /** 职级id */
    @Excel(name = "职级id")
    private String rankId;

    /** 类型 */
    @Excel(name = "类型")
    private Long sheetType;
    
    /** 活动id */
    @Excel(name = "活动id")
    private Long activityId;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setSheetId(Long sheetId)
    {
        this.sheetId = sheetId;
    }

    public Long getSheetId()
    {
        return sheetId;
    }
    public void setObjUserId(String objUserId)
    {
        this.objUserId = objUserId;
    }

    public String getObjUserId()
    {
        return objUserId;
    }
    public void setParticipantUserId(String participantUserId)
    {
        this.participantUserId = participantUserId;
    }

    public String getParticipantUserId()
    {
        return participantUserId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }

    public String getDeptId()
    {
        return deptId;
    }
    public void setRankId(String rankId)
    {
        this.rankId = rankId;
    }

    public String getRankId()
    {
        return rankId;
    }
    public void setSheetType(Long sheetType)
    {
        this.sheetType = sheetType;
    }

    public Long getSheetType()
    {
        return sheetType;
    }

    public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sheetId", getSheetId())
            .append("objUserId", getObjUserId())
            .append("participantUserId", getParticipantUserId())
            .append("deptId", getDeptId())
            .append("rankId", getRankId())
            .append("sheetType", getSheetType())
            .append("activityId", getActivityId())
            .toString();
    }

	public CpSheetObjParticipant() {
		super();
	}

	public CpSheetObjParticipant(Long sheetId, String deptId, Long sheetType, Long activityId) {
		super();
		this.sheetId = sheetId;
		this.deptId = deptId;
		this.sheetType = sheetType;
		this.activityId = activityId;
	}
	
	
}
