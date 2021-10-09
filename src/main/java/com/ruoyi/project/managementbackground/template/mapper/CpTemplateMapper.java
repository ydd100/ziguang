package com.ruoyi.project.managementbackground.template.mapper;

import java.util.List;
import com.ruoyi.project.managementbackground.template.domain.CpTemplate;

/**
 * 测评模板Mapper接口
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
public interface CpTemplateMapper 
{
    /**
     * 查询测评模板
     * 
     * @param id 测评模板主键
     * @return 测评模板
     */
    public CpTemplate selectCpTemplateById(Long id);

    /**
     * 查询测评模板列表
     * 
     * @param cpTemplate 测评模板
     * @return 测评模板集合
     */
    public List<CpTemplate> selectCpTemplateList(CpTemplate cpTemplate);

    /**
     * 新增测评模板
     * 
     * @param cpTemplate 测评模板
     * @return 结果
     */
    public int insertCpTemplate(CpTemplate cpTemplate);

    /**
     * 修改测评模板
     * 
     * @param cpTemplate 测评模板
     * @return 结果
     */
    public int updateCpTemplate(CpTemplate cpTemplate);

    /**
     * 删除测评模板
     * 
     * @param id 测评模板主键
     * @return 结果
     */
    public int deleteCpTemplateById(Long id);

    /**
     * 批量删除测评模板
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCpTemplateByIds(String[] ids);
}
