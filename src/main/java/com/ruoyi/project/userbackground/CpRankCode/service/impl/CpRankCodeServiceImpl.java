package com.ruoyi.project.userbackground.CpRankCode.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.userbackground.CpRankCode.mapper.CpRankCodeMapper;
import com.ruoyi.project.userbackground.CpRankCode.domain.CpRankCode;
import com.ruoyi.project.userbackground.CpRankCode.service.ICpRankCodeService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 二维码Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-08
 */
@Service
public class CpRankCodeServiceImpl implements ICpRankCodeService 
{
    @Autowired
    private CpRankCodeMapper cpRankCodeMapper;

    /**
     * 查询二维码
     * 
     * @param id 二维码主键
     * @return 二维码
     */
    @Override
    public CpRankCode selectCpRankCodeById(String id)
    {
        return cpRankCodeMapper.selectCpRankCodeById(id);
    }

    /**
     * 查询二维码列表
     * 
     * @param cpRankCode 二维码
     * @return 二维码
     */
    @Override
    public List<CpRankCode> selectCpRankCodeList(CpRankCode cpRankCode)
    {
        return cpRankCodeMapper.selectCpRankCodeList(cpRankCode);
    }

    @Override
    public CpRankCode selectCpRankCodeByAllId(String deptid, String rankid, Long activityId) {
        return cpRankCodeMapper.selectCpRankCodeByAllId( deptid,  rankid,  activityId);
    }

    /**
     * 新增二维码
     * 
     * @param cpRankCode 二维码
     * @return 结果
     */
    @Override
    public int insertCpRankCode(CpRankCode cpRankCode)
    {
        return cpRankCodeMapper.insertCpRankCode(cpRankCode);
    }

    /**
     * 修改二维码
     * 
     * @param cpRankCode 二维码
     * @return 结果
     */
    @Override
    public int updateCpRankCode(CpRankCode cpRankCode)
    {
        return cpRankCodeMapper.updateCpRankCode(cpRankCode);
    }

    /**
     * 批量删除二维码
     * 
     * @param ids 需要删除的二维码主键
     * @return 结果
     */
    @Override
    public int deleteCpRankCodeByIds(String ids)
    {
        return cpRankCodeMapper.deleteCpRankCodeByIds(Convert.toStrArray(ids));
    }

    @Override
    public List<CpRankCode> selectrankbydeptidandactid(String deptId, Long activityId) {
        return cpRankCodeMapper.selectrankbydeptidandactid(deptId,activityId);
    }

    /**
     * 删除二维码信息
     * 
     * @param id 二维码主键
     * @return 结果
     */
    @Override
    public int deleteCpRankCodeById(String id)
    {
        return cpRankCodeMapper.deleteCpRankCodeById(id);
    }

    /**
     * 根据活动ID，部门ID，职级ID能确定唯一数据
     * @param actityId
     * @param deptId
     * @param rankId
     * @return
     */
    @Override
    public CpRankCode selectCpRankCodeByAidDidRid(Long actityId,String deptId,String rankId){
        CpRankCode rankCode = new CpRankCode();
        rankCode.setActityId(actityId);
        rankCode.setDeptId(deptId);
        rankCode.setRankId(rankId);
        return cpRankCodeMapper.selectCpRankCodeByAidDidRid(rankCode);
    }

    /**
     * 查询投票概况
     * @param actityId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectVotesStatusByActId(Long actityId) {
        return cpRankCodeMapper.selectVotesStatusByActId(actityId);
    }

    /**
     * 根据活动id统计职级生成票数
     * @param actityId
     * @return
     */
    public Map<String,Object> selectSumCodeNumByActId(Long activityId){
        return cpRankCodeMapper.selectSumCodeNumByActId(activityId);
    }

    /**
     * 根据活动id删除职级制码数据
     * @param activityId
     * @param deptId
     */
    @Override
    public void deleteCpRankCodeByActId(Long activityId,String deptId) {
        CpRankCode rankCode = new CpRankCode();
        rankCode.setActityId(activityId);
        rankCode.setDeptId(deptId);
        cpRankCodeMapper.deleteCpRankCodeByActId(rankCode);
    }
}
