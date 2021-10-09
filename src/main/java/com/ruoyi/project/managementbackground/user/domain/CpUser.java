package com.ruoyi.project.managementbackground.user.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 用户对象 cp_user
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
public class CpUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idcard;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 职级 */
    @Excel(name = "职级")
    private String rankId;

    /** 单位 */
    @Excel(name = "单位")
    private String unitName;

    /** 状态 */
//    @Excel(name = "状态")
    private String status;

    /** 用户类型 */
//    @Excel(name = "用户类型")
    private String userType;

    /** 上次登录 */
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "上次登录", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLoginTime;

    /** 部门 */
    @Excel(name = "部门")
    private String deptId;

    private String rankName;

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
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
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setIdcard(String idcard)
    {
        this.idcard = idcard;
    }

    public String getIdcard()
    {
        return idcard;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getSex()
    {
        return sex;
    }
    public void setRankId(String rankId)
    {
        this.rankId = rankId;
    }

    public String getRankId()
    {
        return rankId;
    }
    public void setUnitName(String unitName)
    {
        this.unitName = unitName;
    }

    public String getUnitName()
    {
        return unitName;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setUserType(String userType)
    {
        this.userType = userType;
    }

    public String getUserType()
    {
        return userType;
    }
    public void setLastLoginTime(Date lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLoginTime()
    {
        return lastLoginTime;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }

    public String getDeptId()
    {
        return deptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("phone", getPhone())
            .append("idcard", getIdcard())
            .append("sex", getSex())
            .append("rankId", getRankId())
            .append("unitName", getUnitName())
            .append("status", getStatus())
            .append("userType", getUserType())
            .append("createTime", getCreateTime())
            .append("lastLoginTime", getLastLoginTime())
            .append("deptId", getDeptId())
            .toString();
    }
}
