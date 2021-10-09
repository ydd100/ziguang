package com.ruoyi.project.userbackground.CpDepartmentCode.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.userbackground.CpDepartmentCode.domain.CpDepartmentCode;
import org.apache.ibatis.annotations.Param;

/**
 * 二维码Mapper接口
 * 
 * @author ruoyi
 * @date 2021-09-08
 */
public interface CpDepartmentCodeMapper 
{
    /**
     * 查询二维码
     * 
     * @param id 二维码主键
     * @return 二维码
     */
    public CpDepartmentCode selectCpDepartmentCodeById(String id);
    public CpDepartmentCode selectCpDepartmentCodeBydeptId(@Param("deptId") String deptId,@Param("actityId") Long actityId);

    /**
     * 查询二维码列表
     * 
     * @param cpDepartmentCode 二维码
     * @return 二维码集合
     */
    public List<CpDepartmentCode> selectCpDepartmentCodeList(CpDepartmentCode cpDepartmentCode);

    /**
     * 新增二维码
     * 
     * @param cpDepartmentCode 二维码
     * @return 结果
     */
    public int insertCpDepartmentCode(CpDepartmentCode cpDepartmentCode);

    /**
     * 修改二维码
     * 
     * @param cpDepartmentCode 二维码
     * @return 结果
     */
    public int updateCpDepartmentCode(CpDepartmentCode cpDepartmentCode);

    /**
     * 删除二维码
     * 
     * @param id 二维码主键
     * @return 结果
     */
    public int deleteCpDepartmentCodeById(String id);

    /**
     * 批量删除二维码
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCpDepartmentCodeByIds(String[] ids);

    /**
     * 根据活动id统计部门生成票数
     * @param activityId
     * @return
     */
    public Map<String,Object> selectCountCodeNumByActId(Long activityId);

    /**
     * 根据活动id删除部门制码
     * @param activityId
     */
    public void deleteCpDepartmentCodeByActId(Long activityId);
 }
