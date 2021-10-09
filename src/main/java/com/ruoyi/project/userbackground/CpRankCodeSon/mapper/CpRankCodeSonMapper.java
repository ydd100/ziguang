package com.ruoyi.project.userbackground.CpRankCodeSon.mapper;

import java.util.List;
import com.ruoyi.project.userbackground.CpRankCodeSon.domain.CpRankCodeSon;

/**
 * 二维码Mapper接口
 * 
 * @author ruoyi
 * @date 2021-09-08
 */
public interface CpRankCodeSonMapper 
{
    /**
     * 查询二维码
     * 
     * @param id 二维码主键
     * @return 二维码
     */
    public CpRankCodeSon selectCpRankCodeSonById(String id);

    /**
     * 查询二维码列表
     * 
     * @param cpRankCodeSon 二维码
     * @return 二维码集合
     */
    public List<CpRankCodeSon> selectCpRankCodeSonList(CpRankCodeSon cpRankCodeSon);
    public List<CpRankCodeSon> selectCpRankCodeSonListbyrankCodeId(String rankCodeId);

    /**
     * 新增二维码
     * 
     * @param cpRankCodeSon 二维码
     * @return 结果
     */
    public int insertCpRankCodeSon(CpRankCodeSon cpRankCodeSon);

    /**
     * 修改二维码
     * 
     * @param cpRankCodeSon 二维码
     * @return 结果
     */
    public int updateCpRankCodeSon(CpRankCodeSon cpRankCodeSon);

    /**
     * 删除二维码
     * 
     * @param id 二维码主键
     * @return 结果
     */
    public int deleteCpRankCodeSonById(String id);

    /**
     * 批量删除二维码
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCpRankCodeSonByIds(String[] ids);
}
