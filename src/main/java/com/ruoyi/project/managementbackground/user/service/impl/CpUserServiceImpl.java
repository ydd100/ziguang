package com.ruoyi.project.managementbackground.user.service.impl;

import java.util.List;
import java.util.UUID;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.managementbackground.user.mapper.CpUserMapper;
import com.ruoyi.project.managementbackground.user.domain.CpUser;
import com.ruoyi.project.managementbackground.user.service.ICpUserService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 用户Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
@Service
public class CpUserServiceImpl implements ICpUserService 
{
    @Autowired
    private CpUserMapper cpUserMapper;

    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    @Override
    public CpUser selectCpUserById(String id)
    {
        return cpUserMapper.selectCpUserById(id);
    }

    @Override
    public List<CpUser> selectCpUserBydeptid(String deptid) {
        return cpUserMapper.selectCpUserBydeptid(deptid);
    }

    @Override
    public List<CpUser> selectCpUserAllBydeptid(String deptid) {
        return cpUserMapper.selectCpUserAllBydeptid(deptid);
    }

    @Override
    public List<CpUser> listallbydeptidandrankid(CpUser cpUser) {
        return cpUserMapper.listallbydeptidandrankid(cpUser);
    }

    /**
     * 查询用户列表
     * 
     * @param cpUser 用户
     * @return 用户
     */
    @Override
    public List<CpUser> selectCpUserList(CpUser cpUser)
    {
        return cpUserMapper.selectCpUserList(cpUser);
    }

    @Override
    public List<CpUser> selectCpUserListAll(CpUser cpUser)
    {
        return cpUserMapper.selectCpUserListAll(cpUser);
    }

    /**
     * 新增用户
     * 
     * @param cpUser 用户
     * @return 结果
     */
    @Override
    public int insertCpUser(CpUser cpUser)
    {
        cpUser.setCreateTime(DateUtils.getNowDate());
        return cpUserMapper.insertCpUser(cpUser);
    }

    /**
     * 修改用户
     * 
     * @param cpUser 用户
     * @return 结果
     */
    @Override
    public int updateCpUser(CpUser cpUser)
    {
        return cpUserMapper.updateCpUser(cpUser);
    }

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteCpUserByIds(String ids)
    {
        return cpUserMapper.deleteCpUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户信息
     * 
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deleteCpUserById(Long id)
    {
        return cpUserMapper.deleteCpUserById(id);
    }


    @Override
    public String importcpUser(List<CpUser> userList, Boolean isUpdateSupport)
    {
        if (StringUtils.isNull(userList) || userList.size() == 0)
        {
            throw new ServiceException("导入用户数据不能为空！");
        }

        for (CpUser user : userList)
        {
            user.setId(UUID.randomUUID().toString());
            if(user.getSex().equals("男生")){
                user.setSex("1");
            }else{
                user.setSex("0");
            }
            this.insertCpUser(user);
        }
        String strmsg = "导入成功！！！";
        return strmsg;
    }

}
