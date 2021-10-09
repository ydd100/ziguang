package com.ruoyi.project.managementbackground.activity.mapper;

import java.util.List;
import com.ruoyi.project.managementbackground.activity.domain.CpActivity;

/**
 * 测评活动Mapper接口
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
public interface CpActivityMapper 
{
    /**
     * 查询测评活动
     * 
     * @param id 测评活动主键
     * @return 测评活动
     */
    public CpActivity selectCpActivityById(Long id);

    /**
     * 查询测评活动列表
     * 
     * @param cpActivity 测评活动
     * @return 测评活动集合
     */
    public List<CpActivity> selectCpActivityList(CpActivity cpActivity);

    /**
     * 新增测评活动
     * 
     * @param cpActivity 测评活动
     * @return 结果
     */
    public int insertCpActivity(CpActivity cpActivity);

    /**
     * 修改测评活动
     * 
     * @param cpActivity 测评活动
     * @return 结果
     */
    public int updateCpActivity(CpActivity cpActivity);

    /**
     * 删除测评活动
     * 
     * @param id 测评活动主键
     * @return 结果
     */
    public int deleteCpActivityById(Long id);

    /**
     * 批量删除测评活动
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCpActivityByIds(String[] ids);
}
