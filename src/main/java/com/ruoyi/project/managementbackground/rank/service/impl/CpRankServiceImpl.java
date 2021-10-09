package com.ruoyi.project.managementbackground.rank.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.managementbackground.rank.mapper.CpRankMapper;
import com.ruoyi.project.managementbackground.rank.domain.CpRank;
import com.ruoyi.project.managementbackground.rank.service.ICpRankService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 职业级别Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
@Service
public class CpRankServiceImpl implements ICpRankService 
{
    @Autowired
    private CpRankMapper cpRankMapper;

    /**
     * 查询职业级别
     * 
     * @param id 职业级别主键
     * @return 职业级别
     */
    @Override
    public CpRank selectCpRankById(String id)
    {
        return cpRankMapper.selectCpRankById(id);
    }

    /**
     * 查询职业级别列表
     * 
     * @param cpRank 职业级别
     * @return 职业级别
     */
    @Override
    public List<CpRank> selectCpRankList(CpRank cpRank)
    {
        return cpRankMapper.selectCpRankList(cpRank);
    }

    /**
     * 新增职业级别
     * 
     * @param cpRank 职业级别
     * @return 结果
     */
    @Override
    public int insertCpRank(CpRank cpRank)
    {
        cpRank.setCreateTime(DateUtils.getNowDate());
        return cpRankMapper.insertCpRank(cpRank);
    }

    /**
     * 修改职业级别
     * 
     * @param cpRank 职业级别
     * @return 结果
     */
    @Override
    public int updateCpRank(CpRank cpRank)
    {
        return cpRankMapper.updateCpRank(cpRank);
    }

    /**
     * 批量删除职业级别
     * 
     * @param ids 需要删除的职业级别主键
     * @return 结果
     */
    @Override
    public int deleteCpRankByIds(String ids)
    {
        return cpRankMapper.deleteCpRankByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除职业级别信息
     * 
     * @param id 职业级别主键
     * @return 结果
     */
    @Override
    public int deleteCpRankById(String id)
    {
        return cpRankMapper.deleteCpRankById(id);
    }
}
