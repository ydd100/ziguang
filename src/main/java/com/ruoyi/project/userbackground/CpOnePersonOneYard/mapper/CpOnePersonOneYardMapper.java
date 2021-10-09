package com.ruoyi.project.userbackground.CpOnePersonOneYard.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.userbackground.CpOnePersonOneYard.domain.CpOnePersonOneYard;

/**
 * 二维码Mapper接口
 * 
 * @author ruoyi
 * @date 2021-09-08
 */
public interface CpOnePersonOneYardMapper 
{
    /**
     * 查询二维码
     * 
     * @param id 二维码主键
     * @return 二维码
     */
    public CpOnePersonOneYard selectCpOnePersonOneYardById(String id);
    public  List<CpOnePersonOneYard> selectCpOnePersonOneYardByActId(Long actityId);

    /**
     * 查询二维码列表
     * 
     * @param cpOnePersonOneYard 二维码
     * @return 二维码集合
     */
    public List<CpOnePersonOneYard> selectCpOnePersonOneYardList(CpOnePersonOneYard cpOnePersonOneYard);

    /**
     * 新增二维码
     * 
     * @param cpOnePersonOneYard 二维码
     * @return 结果
     */
    public int insertCpOnePersonOneYard(CpOnePersonOneYard cpOnePersonOneYard);

    /**
     * 修改二维码
     * 
     * @param cpOnePersonOneYard 二维码
     * @return 结果
     */
    public int updateCpOnePersonOneYard(CpOnePersonOneYard cpOnePersonOneYard);

    /**
     * 删除二维码
     * 
     * @param id 二维码主键
     * @return 结果
     */
    public int deleteCpOnePersonOneYardById(String id);

    /**
     * 批量删除二维码
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCpOnePersonOneYardByIds(String[] ids);

    /**
     * 根据活动id统计个人生成票数
     * @param activityId
     * @return
     */
    public Map<String,Object> selectCountCodeNumByActId(Long activityId);
}
