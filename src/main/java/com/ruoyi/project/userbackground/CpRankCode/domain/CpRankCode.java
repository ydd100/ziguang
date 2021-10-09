package com.ruoyi.project.userbackground.CpRankCode.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 二维码对象 cp_rank_code
 * 
 * @author ruoyi
 * @date 2021-09-08
 */
public class CpRankCode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String id;

    /** 部门id */
    @Excel(name = "部门id")
    private String deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 组名称 */
    @Excel(name = "组名称")
    private String groupName;
    private String rankId;

    /** 识别码位数 */
    @Excel(name = "识别码位数")
    private Long identifierNum;

    /** 生成数量 */
    @Excel(name = "生成数量")
    private Long codeNum;

    /** 活动id */
    @Excel(name = "活动id")
    private Long actityId;

    /** 扫码次数 */
    @Excel(name = "扫码次数")
    private Long scanningTimes = 0L;

    public String getRankId() {
        return rankId;
    }

    public void setRankId(String rankId) {
        this.rankId = rankId;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }

    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getDeptName()
    {
        return deptName;
    }
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public String getGroupName()
    {
        return groupName;
    }
    public void setIdentifierNum(Long identifierNum)
    {
        this.identifierNum = identifierNum;
    }

    public Long getIdentifierNum()
    {
        return identifierNum;
    }
    public void setCodeNum(Long codeNum)
    {
        this.codeNum = codeNum;
    }

    public Long getCodeNum()
    {
        return codeNum;
    }
    public void setActityId(Long actityId)
    {
        this.actityId = actityId;
    }

    public Long getActityId()
    {
        return actityId;
    }

    public Long getScanningTimes() {
        return scanningTimes;
    }

    public void setScanningTimes(Long scanningTimes) {
        this.scanningTimes = scanningTimes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("groupName", getGroupName())
            .append("identifierNum", getIdentifierNum())
            .append("codeNum", getCodeNum())
            .append("actityId", getActityId())
            .append("scanningTimes", getScanningTimes())
            .toString();
    }


}
