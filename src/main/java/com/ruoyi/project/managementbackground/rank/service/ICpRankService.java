package com.ruoyi.project.managementbackground.rank.service;

import java.util.List;
import com.ruoyi.project.managementbackground.rank.domain.CpRank;

/**
 * 职业级别Service接口
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
public interface ICpRankService 
{
    /**
     * 查询职业级别
     * 
     * @param id 职业级别主键
     * @return 职业级别
     */
    public CpRank selectCpRankById(String id);

    /**
     * 查询职业级别列表
     * 
     * @param cpRank 职业级别
     * @return 职业级别集合
     */
    public List<CpRank> selectCpRankList(CpRank cpRank);

    /**
     * 新增职业级别
     * 
     * @param cpRank 职业级别
     * @return 结果
     */
    public int insertCpRank(CpRank cpRank);

    /**
     * 修改职业级别
     * 
     * @param cpRank 职业级别
     * @return 结果
     */
    public int updateCpRank(CpRank cpRank);

    /**
     * 批量删除职业级别
     * 
     * @param ids 需要删除的职业级别主键集合
     * @return 结果
     */
    public int deleteCpRankByIds(String ids);

    /**
     * 删除职业级别信息
     * 
     * @param id 职业级别主键
     * @return 结果
     */
    public int deleteCpRankById(String id);
}
