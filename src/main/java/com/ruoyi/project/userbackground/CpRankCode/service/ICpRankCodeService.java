package com.ruoyi.project.userbackground.CpRankCode.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.userbackground.CpRankCode.domain.CpRankCode;

/**
 * 二维码Service接口
 * 
 * @author ruoyi
 * @date 2021-09-08
 */
public interface ICpRankCodeService 
{
    /**
     * 查询二维码
     * 
     * @param id 二维码主键
     * @return 二维码
     */
    public CpRankCode selectCpRankCodeById(String id);

    /**
     * 查询二维码列表
     * 
     * @param cpRankCode 二维码
     * @return 二维码集合
     */
    public List<CpRankCode> selectCpRankCodeList(CpRankCode cpRankCode);

    public CpRankCode selectCpRankCodeByAllId(String deptid,String rankid,Long activityId );

    /**
     * 新增二维码
     * 
     * @param cpRankCode 二维码
     * @return 结果
     */
    public int insertCpRankCode(CpRankCode cpRankCode);

    /**
     * 修改二维码
     * 
     * @param cpRankCode 二维码
     * @return 结果
     */
    public int updateCpRankCode(CpRankCode cpRankCode);

    /**
     * 批量删除二维码
     * 
     * @param ids 需要删除的二维码主键集合
     * @return 结果
     */
    public int deleteCpRankCodeByIds(String ids);
    public List<CpRankCode> selectrankbydeptidandactid(String deptId, Long activityId);

    /**
     * 删除二维码信息
     * 
     * @param id 二维码主键
     * @return 结果
     */
    public int deleteCpRankCodeById(String id);

    /**
     * 根据活动ID，部门ID，职级ID能确定唯一数据
     * @param actityId
     * @param deptId
     * @param rankId
     * @return
     */
    public CpRankCode selectCpRankCodeByAidDidRid(Long actityId,String deptId,String rankId);

    /**
     * 查询投票概况
     * @param actityId
     * @return
     */
    public List<Map<String,Object>> selectVotesStatusByActId(Long actityId);

    /**
     * 根据活动id统计职级生成票数
     * @param activityId
     * @return
     */
    public Map<String,Object> selectSumCodeNumByActId(Long activityId);

    /**
     * 根据活动id删除职级制码数据
     * @param activityId
     * @param deptId
     */
    public void deleteCpRankCodeByActId(Long activityId,String deptId);
}
