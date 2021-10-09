package com.ruoyi.project.managementbackground.dept.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 部门对象 cp_dept
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
public class CpDept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    @Excel(name = "部门编号")
    private Integer id;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String name;

    /** 添加日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Excel(name = "添加日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date creatTime;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
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
    public void setCreatTime(Date creatTime)
    {
        this.creatTime = creatTime;
    }

    public Date getCreatTime()
    {
        return creatTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("creatTime", getCreatTime())
            .toString();
    }
}
