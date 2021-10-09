package com.ruoyi.project.userbackground.CpDepartmentCode.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.userbackground.CpDepartmentCode.mapper.CpDepartmentCodeMapper;
import com.ruoyi.project.userbackground.CpDepartmentCode.domain.CpDepartmentCode;
import com.ruoyi.project.userbackground.CpDepartmentCode.service.ICpDepartmentCodeService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 二维码Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-08
 */
@Service
public class CpDepartmentCodeServiceImpl implements ICpDepartmentCodeService 
{
    @Autowired
    private CpDepartmentCodeMapper cpDepartmentCodeMapper;

    /**
     * 查询二维码
     * 
     * @param id 二维码主键
     * @return 二维码
     */
    @Override
    public CpDepartmentCode selectCpDepartmentCodeById(String id)
    {
        return cpDepartmentCodeMapper.selectCpDepartmentCodeById(id);
    }

    @Override
    public CpDepartmentCode selectCpDepartmentCodeBydeptId(String deptId,Long actityId) {
        return cpDepartmentCodeMapper.selectCpDepartmentCodeBydeptId(deptId,actityId);
    }

    /**
     * 查询二维码列表
     * 
     * @param cpDepartmentCode 二维码
     * @return 二维码
     */
    @Override
    public List<CpDepartmentCode> selectCpDepartmentCodeList(CpDepartmentCode cpDepartmentCode)
    {
        return cpDepartmentCodeMapper.selectCpDepartmentCodeList(cpDepartmentCode);
    }

    /**
     * 新增二维码
     * 
     * @param cpDepartmentCode 二维码
     * @return 结果
     */
    @Override
    public int insertCpDepartmentCode(CpDepartmentCode cpDepartmentCode)
    {
        return cpDepartmentCodeMapper.insertCpDepartmentCode(cpDepartmentCode);
    }

    /**
     * 修改二维码
     * 
     * @param cpDepartmentCode 二维码
     * @return 结果
     */
    @Override
    public int updateCpDepartmentCode(CpDepartmentCode cpDepartmentCode)
    {
        return cpDepartmentCodeMapper.updateCpDepartmentCode(cpDepartmentCode);
    }

    /**
     * 批量删除二维码
     * 
     * @param ids 需要删除的二维码主键
     * @return 结果
     */
    @Override
    public int deleteCpDepartmentCodeByIds(String ids)
    {
        return cpDepartmentCodeMapper.deleteCpDepartmentCodeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除二维码信息
     * 
     * @param id 二维码主键
     * @return 结果
     */
    @Override
    public int deleteCpDepartmentCodeById(String id)
    {
        return cpDepartmentCodeMapper.deleteCpDepartmentCodeById(id);
    }

    /**
     * 根据活动id统计部门生成票数
     * @param activityId
     * @return
     */
    @Override
    public Map<String, Object> selectCountCodeNumByActId(Long activityId) {
        return cpDepartmentCodeMapper.selectCountCodeNumByActId(activityId);
    }

    /**
     * 根据活动id删除部门制码
     * @param activityId
     */
    @Override
    public void deleteCpDepartmentCodeByActId(Long activityId) {
        cpDepartmentCodeMapper.deleteCpDepartmentCodeByActId(activityId);
    }
}
