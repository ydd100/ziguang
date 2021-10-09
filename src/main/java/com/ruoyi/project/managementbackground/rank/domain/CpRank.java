package com.ruoyi.project.managementbackground.rank.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 职业级别对象 cp_rank
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
public class CpRank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    @Excel(name = "职级编号")
    private Integer id;

    /** 名称 */
    @Excel(name = "职级名称")
    private String name;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("createTime", getCreateTime())
            .toString();
    }
}
