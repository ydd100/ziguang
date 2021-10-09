package com.ruoyi.project.userbackground.CpRankCodeSon.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.userbackground.CpRankCodeSon.mapper.CpRankCodeSonMapper;
import com.ruoyi.project.userbackground.CpRankCodeSon.domain.CpRankCodeSon;
import com.ruoyi.project.userbackground.CpRankCodeSon.service.ICpRankCodeSonService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 二维码Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-08
 */
@Service
public class CpRankCodeSonServiceImpl implements ICpRankCodeSonService 
{
    @Autowired
    private CpRankCodeSonMapper cpRankCodeSonMapper;

    /**
     * 查询二维码
     * 
     * @param id 二维码主键
     * @return 二维码
     */
    @Override
    public CpRankCodeSon selectCpRankCodeSonById(String id)
    {
        return cpRankCodeSonMapper.selectCpRankCodeSonById(id);
    }

    /**
     * 查询二维码列表
     * 
     * @param cpRankCodeSon 二维码
     * @return 二维码
     */
    @Override
    public List<CpRankCodeSon> selectCpRankCodeSonList(CpRankCodeSon cpRankCodeSon)
    {
        return cpRankCodeSonMapper.selectCpRankCodeSonList(cpRankCodeSon);
    }

    @Override
    public List<CpRankCodeSon> selectCpRankCodeSonListbyrankCodeId(String rankCodeId) {
        return cpRankCodeSonMapper.selectCpRankCodeSonListbyrankCodeId(rankCodeId);
    }

    /**
     * 新增二维码
     * 
     * @param cpRankCodeSon 二维码
     * @return 结果
     */
    @Override
    public int insertCpRankCodeSon(CpRankCodeSon cpRankCodeSon)
    {
        return cpRankCodeSonMapper.insertCpRankCodeSon(cpRankCodeSon);
    }

    /**
     * 修改二维码
     * 
     * @param cpRankCodeSon 二维码
     * @return 结果
     */
    @Override
    public int updateCpRankCodeSon(CpRankCodeSon cpRankCodeSon)
    {
        return cpRankCodeSonMapper.updateCpRankCodeSon(cpRankCodeSon);
    }

    /**
     * 批量删除二维码
     * 
     * @param ids 需要删除的二维码主键
     * @return 结果
     */
    @Override
    public int deleteCpRankCodeSonByIds(String ids)
    {
        return cpRankCodeSonMapper.deleteCpRankCodeSonByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除二维码信息
     * 
     * @param id 二维码主键
     * @return 结果
     */
    @Override
    public int deleteCpRankCodeSonById(String id)
    {
        return cpRankCodeSonMapper.deleteCpRankCodeSonById(id);
    }
}
