package com.ruoyi.project.userbackground.CpUnlimitCode.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 二维码对象 cp_unlimit_code
 * 
 * @author ruoyi
 * @date 2021-09-09
 */
public class CpUnlimitCode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String id;

    /** 扫码次数 */
    @Excel(name = "扫码次数")
    private Long scanningTimes;

    /** 活动id */
    @Excel(name = "活动id")
    private Long actityId;

    /** 二维码路径 */
    @Excel(name = "二维码路径")
    private String qrCodePath;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
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
    public void setQrCodePath(String qrCodePath)
    {
        this.qrCodePath = qrCodePath;
    }

    public String getQrCodePath()
    {
        return qrCodePath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("scanningTimes", getScanningTimes())
            .append("actityId", getActityId())
            .append("qrCodePath", getQrCodePath())
            .toString();
    }
}
