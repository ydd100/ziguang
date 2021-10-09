package com.ruoyi.project.managementbackground.dept.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.managementbackground.dept.mapper.CpDeptMapper;
import com.ruoyi.project.managementbackground.dept.domain.CpDept;
import com.ruoyi.project.managementbackground.dept.service.ICpDeptService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 部门Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
@Service
public class CpDeptServiceImpl implements ICpDeptService 
{
    @Autowired
    private CpDeptMapper cpDeptMapper;

    /**
     * 查询部门
     * 
     * @param id 部门主键
     * @return 部门
     */
    @Override
    public CpDept selectCpDeptById(String id)
    {
        return cpDeptMapper.selectCpDeptById(id);
    }

    /**
     * 查询部门列表
     * 
     * @param cpDept 部门
     * @return 部门
     */
    @Override
    public List<CpDept> selectCpDeptList(CpDept cpDept)
    {
        return cpDeptMapper.selectCpDeptList(cpDept);
    }

    /**
     * 新增部门
     * 
     * @param cpDept 部门
     * @return 结果
     */
    @Override
    public int insertCpDept(CpDept cpDept)
    {
        return cpDeptMapper.insertCpDept(cpDept);
    }

    /**
     * 修改部门
     * 
     * @param cpDept 部门
     * @return 结果
     */
    @Override
    public int updateCpDept(CpDept cpDept)
    {
        return cpDeptMapper.updateCpDept(cpDept);
    }

    /**
     * 批量删除部门
     * 
     * @param ids 需要删除的部门主键
     * @return 结果
     */
    @Override
    public int deleteCpDeptByIds(String ids)
    {
        return cpDeptMapper.deleteCpDeptByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除部门信息
     * 
     * @param id 部门主键
     * @return 结果
     */
    @Override
    public int deleteCpDeptById(String id)
    {
        return cpDeptMapper.deleteCpDeptById(id);
    }

    /**
     * 根据部门名称只能查出一条数据
     * @param cpDept
     * @return
     */
    @Override
    public CpDept checkOneByDeptName(CpDept cpDept) {
        return cpDeptMapper.checkOneByDeptName(cpDept);
    }
}
