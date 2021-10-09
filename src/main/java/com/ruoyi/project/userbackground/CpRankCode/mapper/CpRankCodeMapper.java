package com.ruoyi.project.userbackground.CpRankCode.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.userbackground.CpRankCode.domain.CpRankCode;
import org.apache.ibatis.annotations.Param;

/**
 * 二维码Mapper接口
 * 
 * @author ruoyi
 * @date 2021-09-08
 */
public interface CpRankCodeMapper 
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
    public CpRankCode selectCpRankCodeByAllId(@Param("deptId") String deptid, @Param("rankId") String rankid, @Param("actityId") Long activityId);

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
     * 删除二维码
     * 
     * @param id 二维码主键
     * @return 结果
     */
    public int deleteCpRankCodeById(String id);

    /**
     * 批量删除二维码
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCpRankCodeByIds(String[] ids);
    public List<CpRankCode> selectrankbydeptidandactid(@Param("deptId") String deptId, @Param("actityId") Long activityId);

    /**
     * 根据活动ID，部门ID，职级ID能确定唯一数据
     * @param cpRankCode
     * @return
     */
    public CpRankCode selectCpRankCodeByAidDidRid(CpRankCode cpRankCode);

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
     * @param rankCode
     */
    public void deleteCpRankCodeByActId(CpRankCode rankCode);

}
