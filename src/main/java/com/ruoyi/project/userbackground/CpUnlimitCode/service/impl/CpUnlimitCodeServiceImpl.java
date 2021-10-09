package com.ruoyi.project.userbackground.CpUnlimitCode.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.userbackground.CpUnlimitCode.mapper.CpUnlimitCodeMapper;
import com.ruoyi.project.userbackground.CpUnlimitCode.domain.CpUnlimitCode;
import com.ruoyi.project.userbackground.CpUnlimitCode.service.ICpUnlimitCodeService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 二维码Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-09
 */
@Service
public class CpUnlimitCodeServiceImpl implements ICpUnlimitCodeService 
{
    @Autowired
    private CpUnlimitCodeMapper cpUnlimitCodeMapper;

    /**
     * 查询二维码
     * 
     * @param id 二维码主键
     * @return 二维码
     */
    @Override
    public CpUnlimitCode selectCpUnlimitCodeById(String id)
    {
        return cpUnlimitCodeMapper.selectCpUnlimitCodeById(id);
    }

    @Override
    public CpUnlimitCode selectCpUnlimitCodeByactityid(String activityId) {
        return cpUnlimitCodeMapper.selectCpUnlimitCodeByactityid(activityId);
    }

    /**
     * 查询二维码列表
     * 
     * @param cpUnlimitCode 二维码
     * @return 二维码
     */
    @Override
    public List<CpUnlimitCode> selectCpUnlimitCodeList(CpUnlimitCode cpUnlimitCode)
    {
        return cpUnlimitCodeMapper.selectCpUnlimitCodeList(cpUnlimitCode);
    }

    /**
     * 新增二维码
     * 
     * @param cpUnlimitCode 二维码
     * @return 结果
     */
    @Override
    public int insertCpUnlimitCode(CpUnlimitCode cpUnlimitCode)
    {
        return cpUnlimitCodeMapper.insertCpUnlimitCode(cpUnlimitCode);
    }

    /**
     * 修改二维码
     * 
     * @param cpUnlimitCode 二维码
     * @return 结果
     */
    @Override
    public int updateCpUnlimitCode(CpUnlimitCode cpUnlimitCode)
    {
        return cpUnlimitCodeMapper.updateCpUnlimitCode(cpUnlimitCode);
    }

    /**
     * 批量删除二维码
     * 
     * @param ids 需要删除的二维码主键
     * @return 结果
     */
    @Override
    public int deleteCpUnlimitCodeByIds(String ids)
    {
        return cpUnlimitCodeMapper.deleteCpUnlimitCodeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除二维码信息
     * 
     * @param id 二维码主键
     * @return 结果
     */
    @Override
    public int deleteCpUnlimitCodeById(String id)
    {
        return cpUnlimitCodeMapper.deleteCpUnlimitCodeById(id);
    }
}
