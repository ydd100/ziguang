package com.ruoyi.project.managementbackground.activity.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 测评活动对象 cp_activity
 *
 * @author ruoyi
 * @date 2021-09-05
 */
public class CpActivity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String actName;

    /** 模板id */
    @Excel(name = "模板id")
    private Long templateId;

    /** 发布人id */
    @Excel(name = "发布人id")
    private Long releaseId;

    /** 发布人 */
    @Excel(name = "发布人")
    private String releaseName;

    /** 发布单位 */
    @Excel(name = "发布单位")
    private String releaseUnit;

    /** 活动开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date activityBeginTime;

    /** 活动结束 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动结束", width = 30, dateFormat = "yyyy-MM-dd")
    private Date activityEndTime;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 活动说明 */
    @Excel(name = "活动说明")
    private String activityIllustrate;

    /** 用户职级(1开启0关闭) */
    @Excel(name = "用户职级", readConverterExp = "0=关闭,1=开启")
    private Long isnotRank;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setActName(String actName)
    {
        this.actName = actName;
    }

    public String getActName()
    {
        return actName;
    }
    public void setTemplateId(Long templateId)
    {
        this.templateId = templateId;
    }

    public Long getTemplateId()
    {
        return templateId;
    }
    public void setReleaseId(Long releaseId)
    {
        this.releaseId = releaseId;
    }

    public Long getReleaseId()
    {
        return releaseId;
    }
    public void setReleaseName(String releaseName)
    {
        this.releaseName = releaseName;
    }

    public String getReleaseName()
    {
        return releaseName;
    }
    public void setReleaseUnit(String releaseUnit)
    {
        this.releaseUnit = releaseUnit;
    }

    public String getReleaseUnit()
    {
        return releaseUnit;
    }
    public void setActivityBeginTime(Date activityBeginTime)
    {
        this.activityBeginTime = activityBeginTime;
    }

    public Date getActivityBeginTime()
    {
        return activityBeginTime;
    }
    public void setActivityEndTime(Date activityEndTime)
    {
        this.activityEndTime = activityEndTime;
    }

    public Date getActivityEndTime()
    {
        return activityEndTime;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setActivityIllustrate(String activityIllustrate)
    {
        this.activityIllustrate = activityIllustrate;
    }

    public String getActivityIllustrate()
    {
        return activityIllustrate;
    }
    public void setIsnotRank(Long isnotRank)
    {
        this.isnotRank = isnotRank;
    }

    public Long getIsnotRank()
    {
        return isnotRank;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("actName", getActName())
                .append("templateId", getTemplateId())
                .append("releaseId", getReleaseId())
                .append("releaseName", getReleaseName())
                .append("releaseUnit", getReleaseUnit())
                .append("activityBeginTime", getActivityBeginTime())
                .append("activityEndTime", getActivityEndTime())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .append("activityIllustrate", getActivityIllustrate())
                .append("isnotRank", getIsnotRank())
                .toString();
    }
}