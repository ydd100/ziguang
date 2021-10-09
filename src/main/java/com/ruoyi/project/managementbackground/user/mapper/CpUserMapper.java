package com.ruoyi.project.managementbackground.user.mapper;

import java.util.List;
import com.ruoyi.project.managementbackground.user.domain.CpUser;

/**
 * 用户Mapper接口
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
public interface CpUserMapper 
{
    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    public CpUser selectCpUserById(String id);
    public  List<CpUser> selectCpUserBydeptid(String deptid);
    public  List<CpUser> selectCpUserAllBydeptid(String deptid);
    public List<CpUser> listallbydeptidandrankid(CpUser cpUser);

    /**
     * 查询用户列表
     * 
     * @param cpUser 用户
     * @return 用户集合
     */
    public List<CpUser> selectCpUserList(CpUser cpUser);
    public List<CpUser> selectCpUserListAll(CpUser cpUser);

    /**
     * 新增用户
     * 
     * @param cpUser 用户
     * @return 结果
     */
    public int insertCpUser(CpUser cpUser);

    /**
     * 修改用户
     * 
     * @param cpUser 用户
     * @return 结果
     */
    public int updateCpUser(CpUser cpUser);

    /**
     * 删除用户
     * 
     * @param id 用户主键
     * @return 结果
     */
    public int deleteCpUserById(Long id);

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCpUserByIds(String[] ids);
}
