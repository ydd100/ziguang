package com.ruoyi.project.userbackground.CpOnePersonOneYard.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.userbackground.CpOnePersonOneYard.mapper.CpOnePersonOneYardMapper;
import com.ruoyi.project.userbackground.CpOnePersonOneYard.domain.CpOnePersonOneYard;
import com.ruoyi.project.userbackground.CpOnePersonOneYard.service.ICpOnePersonOneYardService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 二维码Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-08
 */
@Service
public class CpOnePersonOneYardServiceImpl implements ICpOnePersonOneYardService 
{
    @Autowired
    private CpOnePersonOneYardMapper cpOnePersonOneYardMapper;

    /**
     * 查询二维码
     * 
     * @param id 二维码主键
     * @return 二维码
     */
    @Override
    public CpOnePersonOneYard selectCpOnePersonOneYardById(String id)
    {
        return cpOnePersonOneYardMapper.selectCpOnePersonOneYardById(id);
    }

    @Override
    public  List<CpOnePersonOneYard> selectCpOnePersonOneYardByActId(Long activityId) {
        return cpOnePersonOneYardMapper.selectCpOnePersonOneYardByActId(activityId);
    }

    /**
     * 查询二维码列表
     * 
     * @param cpOnePersonOneYard 二维码
     * @return 二维码
     */
    @Override
    public List<CpOnePersonOneYard> selectCpOnePersonOneYardList(CpOnePersonOneYard cpOnePersonOneYard)
    {
        return cpOnePersonOneYardMapper.selectCpOnePersonOneYardList(cpOnePersonOneYard);
    }

    /**
     * 新增二维码
     * 
     * @param cpOnePersonOneYard 二维码
     * @return 结果
     */
    @Override
    public int insertCpOnePersonOneYard(CpOnePersonOneYard cpOnePersonOneYard)
    {
        return cpOnePersonOneYardMapper.insertCpOnePersonOneYard(cpOnePersonOneYard);
    }

    /**
     * 修改二维码
     * 
     * @param cpOnePersonOneYard 二维码
     * @return 结果
     */
    @Override
    public int updateCpOnePersonOneYard(CpOnePersonOneYard cpOnePersonOneYard)
    {
        return cpOnePersonOneYardMapper.updateCpOnePersonOneYard(cpOnePersonOneYard);
    }

    /**
     * 批量删除二维码
     * 
     * @param ids 需要删除的二维码主键
     * @return 结果
     */
    @Override
    public int deleteCpOnePersonOneYardByIds(String ids)
    {
        return cpOnePersonOneYardMapper.deleteCpOnePersonOneYardByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除二维码信息
     * 
     * @param id 二维码主键
     * @return 结果
     */
    @Override
    public int deleteCpOnePersonOneYardById(String id)
    {
        return cpOnePersonOneYardMapper.deleteCpOnePersonOneYardById(id);
    }

    /**
     * 根据活动id统计个人生成票数
     * @param activityId
     * @return
     */
    @Override
    public Map<String, Object> selectCountCodeNumByActId(Long activityId) {
        return cpOnePersonOneYardMapper.selectCountCodeNumByActId(activityId);
    }
}
