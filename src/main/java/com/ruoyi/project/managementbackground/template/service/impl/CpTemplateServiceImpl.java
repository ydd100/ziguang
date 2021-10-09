package com.ruoyi.project.managementbackground.template.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.managementbackground.template.mapper.CpTemplateMapper;
import com.ruoyi.project.managementbackground.template.domain.CpTemplate;
import com.ruoyi.project.managementbackground.template.service.ICpTemplateService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 测评模板Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
@Service
public class CpTemplateServiceImpl implements ICpTemplateService 
{
    @Autowired
    private CpTemplateMapper cpTemplateMapper;

    /**
     * 查询测评模板
     * 
     * @param id 测评模板主键
     * @return 测评模板
     */
    @Override
    public CpTemplate selectCpTemplateById(Long id)
    {
        return cpTemplateMapper.selectCpTemplateById(id);
    }

    /**
     * 查询测评模板列表
     * 
     * @param cpTemplate 测评模板
     * @return 测评模板
     */
    @Override
    public List<CpTemplate> selectCpTemplateList(CpTemplate cpTemplate)
    {
        return cpTemplateMapper.selectCpTemplateList(cpTemplate);
    }

    /**
     * 新增测评模板
     * 
     * @param cpTemplate 测评模板
     * @return 结果
     */
    @Override
    public int insertCpTemplate(CpTemplate cpTemplate)
    {
        cpTemplate.setCreateTime(DateUtils.getNowDate());
        return cpTemplateMapper.insertCpTemplate(cpTemplate);
    }

    /**
     * 修改测评模板
     * 
     * @param cpTemplate 测评模板
     * @return 结果
     */
    @Override
    public int updateCpTemplate(CpTemplate cpTemplate)
    {
        return cpTemplateMapper.updateCpTemplate(cpTemplate);
    }

    /**
     * 批量删除测评模板
     * 
     * @param ids 需要删除的测评模板主键
     * @return 结果
     */
    @Override
    public int deleteCpTemplateByIds(String ids)
    {
        return cpTemplateMapper.deleteCpTemplateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除测评模板信息
     * 
     * @param id 测评模板主键
     * @return 结果
     */
    @Override
    public int deleteCpTemplateById(Long id)
    {
        return cpTemplateMapper.deleteCpTemplateById(id);
    }
}
