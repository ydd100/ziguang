package com.ruoyi.project.userbackground.CpOnePersonOneYard.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 二维码对象 cp_one_person_one_yard
 * 
 * @author ruoyi
 * @date 2021-09-08
 */
public class CpOnePersonOneYard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String id;

    /** 识别码 */
    @Excel(name = "识别码")
    private String identifier;

    /** 二维码状态 */
    @Excel(name = "二维码状态")
    private String codeState;

    /** 二维码路径 */
    @Excel(name = "二维码路径")
    private String qrCodePath;

    /** 生成数量 */
    @Excel(name = "生成数量")
    private Long codeNum;

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
    public void setIdentifier(String identifier)
    {
        this.identifier = identifier;
    }

    public String getIdentifier()
    {
        return identifier;
    }
    public void setCodeState(String codeState)
    {
        this.codeState = codeState;
    }

    public String getCodeState()
    {
        return codeState;
    }
    public void setQrCodePath(String qrCodePath)
    {
        this.qrCodePath = qrCodePath;
    }

    public String getQrCodePath()
    {
        return qrCodePath;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("identifier", getIdentifier())
            .append("codeState", getCodeState())
            .append("qrCodePath", getQrCodePath())
            .append("codeNum", getCodeNum())
            .append("actityId", getActityId())
            .toString();
    }
}
