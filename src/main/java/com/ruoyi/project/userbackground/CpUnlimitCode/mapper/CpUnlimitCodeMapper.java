package com.ruoyi.project.userbackground.CpUnlimitCode.mapper;

import java.util.List;
import com.ruoyi.project.userbackground.CpUnlimitCode.domain.CpUnlimitCode;

/**
 * 二维码Mapper接口
 * 
 * @author ruoyi
 * @date 2021-09-09
 */
public interface CpUnlimitCodeMapper 
{
    /**
     * 查询二维码
     * 
     * @param id 二维码主键
     * @return 二维码
     */
    public CpUnlimitCode selectCpUnlimitCodeById(String id);

    public CpUnlimitCode selectCpUnlimitCodeByactityid(String activityId);

    /**
     * 查询二维码列表
     * 
     * @param cpUnlimitCode 二维码
     * @return 二维码集合
     */
    public List<CpUnlimitCode> selectCpUnlimitCodeList(CpUnlimitCode cpUnlimitCode);

    /**
     * 新增二维码
     * 
     * @param cpUnlimitCode 二维码
     * @return 结果
     */
    public int insertCpUnlimitCode(CpUnlimitCode cpUnlimitCode);

    /**
     * 修改二维码
     * 
     * @param cpUnlimitCode 二维码
     * @return 结果
     */
    public int updateCpUnlimitCode(CpUnlimitCode cpUnlimitCode);

    /**
     * 删除二维码
     * 
     * @param id 二维码主键
     * @return 结果
     */
    public int deleteCpUnlimitCodeById(String id);

    /**
     * 批量删除二维码
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCpUnlimitCodeByIds(String[] ids);
}
