package com.ruoyi.project.userbackground.CpDepartmentCode.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 二维码对象 cp_department_code
 * 
 * @author ruoyi
 * @date 2021-09-08
 */
public class CpDepartmentCode extends BaseEntity
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

    /** 二维码路径 */
    @Excel(name = "二维码路径")
    private String qrCodePath;

    /** 扫码次数 */
    @Excel(name = "扫码次数")
    private Long scanningTimes = 0L;

    /** 活动id */
    @Excel(name = "活动id")
    private Long actityId;

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
    public void setQrCodePath(String qrCodePath)
    {
        this.qrCodePath = qrCodePath;
    }

    public String getQrCodePath()
    {
        return qrCodePath;
    }
    public void setScanningTimes(Long scanningTimes)
    {
        this.scanningTimes = scanningTimes;
    }

    public Long getScanningTimes()
    {
        return scanningTimes;
    }
    public void setActityId(Long actityId)
    {
        this.actityId = actityId;
    }

    public Long getActityId()
    {
        return actityId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("qrCodePath", getQrCodePath())
            .append("scanningTimes", getScanningTimes())
            .append("actityId", getActityId())
            .toString();
    }
}
