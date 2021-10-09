package com.ruoyi.project.userbackground.CpRankCodeSon.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 二维码对象 cp_rank_code_son
 * 
 * @author ruoyi
 * @date 2021-09-08
 */
public class CpRankCodeSon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String id;

    /**  */
    @Excel(name = "")
    private String rankCodeId;

    /**  */
    @Excel(name = "")
    private String rankId;

    /** 识别码 */
    @Excel(name = "识别码")
    private String identifier;

    /** 二维码状态 */
    @Excel(name = "二维码状态")
    private String codeState;

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
    public void setRankCodeId(String rankCodeId)
    {
        this.rankCodeId = rankCodeId;
    }

    public String getRankCodeId()
    {
        return rankCodeId;
    }
    public void setRankId(String rankId)
    {
        this.rankId = rankId;
    }

    public String getRankId()
    {
        return rankId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("rankCodeId", getRankCodeId())
            .append("rankId", getRankId())
            .append("identifier", getIdentifier())
            .append("codeState", getCodeState())
            .append("qrCodePath", getQrCodePath())
            .toString();
    }
}
