package com.ruoyi.project.managementbackground.activity.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.managementbackground.activity.mapper.CpActivityMapper;
import com.ruoyi.project.managementbackground.activity.domain.CpActivity;
import com.ruoyi.project.managementbackground.activity.service.ICpActivityService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 测评活动Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
@Service
public class CpActivityServiceImpl implements ICpActivityService 
{
    @Autowired
    private CpActivityMapper cpActivityMapper;

    /**
     * 查询测评活动
     * 
     * @param id 测评活动主键
     * @return 测评活动
     */
    @Override
    public CpActivity selectCpActivityById(Long id)
    {
        return cpActivityMapper.selectCpActivityById(id);
    }


    /**
     * 查询测评活动列表
     * 
     * @param cpActivity 测评活动
     * @return 测评活动
     */
    @Override
    public List<CpActivity> selectCpActivityList(CpActivity cpActivity)
    {
        return cpActivityMapper.selectCpActivityList(cpActivity);
    }

    /**
     * 新增测评活动
     * 
     * @param cpActivity 测评活动
     * @return 结果
     */
    @Override
    public int insertCpActivity(CpActivity cpActivity)
    {
        cpActivity.setCreateTime(DateUtils.getNowDate());
        return cpActivityMapper.insertCpActivity(cpActivity);
    }

    /**
     * 修改测评活动
     * 
     * @param cpActivity 测评活动
     * @return 结果
     */
    @Override
    public int updateCpActivity(CpActivity cpActivity)
    {
        return cpActivityMapper.updateCpActivity(cpActivity);
    }

    /**
     * 批量删除测评活动
     * 
     * @param ids 需要删除的测评活动主键
     * @return 结果
     */
    @Override
    public int deleteCpActivityByIds(String ids)
    {
        return cpActivityMapper.deleteCpActivityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除测评活动信息
     * 
     * @param id 测评活动主键
     * @return 结果
     */
    @Override
    public int deleteCpActivityById(Long id)
    {
        return cpActivityMapper.deleteCpActivityById(id);
    }
}
