package com.ruoyi.project.managementbackground.dept.service;

import java.util.List;
import com.ruoyi.project.managementbackground.dept.domain.CpDept;

/**
 * 部门Service接口
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
public interface ICpDeptService 
{
    /**
     * 查询部门
     * 
     * @param id 部门主键
     * @return 部门
     */
    public CpDept selectCpDeptById(String id);

    /**
     * 查询部门列表
     * 
     * @param cpDept 部门
     * @return 部门集合
     */
    public List<CpDept> selectCpDeptList(CpDept cpDept);

    /**
     * 新增部门
     * 
     * @param cpDept 部门
     * @return 结果
     */
    public int insertCpDept(CpDept cpDept);

    /**
     * 修改部门
     * 
     * @param cpDept 部门
     * @return 结果
     */
    public int updateCpDept(CpDept cpDept);

    /**
     * 批量删除部门
     * 
     * @param ids 需要删除的部门主键集合
     * @return 结果
     */
    public int deleteCpDeptByIds(String ids);

    /**
     * 删除部门信息
     * 
     * @param id 部门主键
     * @return 结果
     */
    public int deleteCpDeptById(String id);

    /**
     * 根据部门名称只能查出一条数据
     * @param cpDept
     * @return
     */
    public CpDept checkOneByDeptName(CpDept cpDept);
}
