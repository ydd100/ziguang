package com.ruoyi.project.userbackground.participant.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.userbackground.participant.domain.CpSheetObjParticipant;

/**
 * 表格测评Mapper接口
 * 
 * @author ruoyi
 * @date 2021-09-06
 */
public interface CpSheetObjParticipantMapper 
{
    /**
     * 查询表格测评
     * 
     * @param id 表格测评主键
     * @return 表格测评
     */
    public CpSheetObjParticipant selectCpSheetObjParticipantById(String id);

    /**
     * 查询表格测评列表
     * 
     * @param cpSheetObjParticipant 表格测评
     * @return 表格测评集合
     */
    public List<CpSheetObjParticipant> selectCpSheetObjParticipantList(CpSheetObjParticipant cpSheetObjParticipant);

    /**
     * 新增表格测评
     * 
     * @param cpSheetObjParticipant 表格测评
     * @return 结果
     */
    public int insertCpSheetObjParticipant(CpSheetObjParticipant cpSheetObjParticipant);

    /**
     * 修改表格测评
     * 
     * @param cpSheetObjParticipant 表格测评
     * @return 结果
     */
    public int updateCpSheetObjParticipant(CpSheetObjParticipant cpSheetObjParticipant);

    /**
     * 删除表格测评
     * 
     * @param id 表格测评主键
     * @return 结果
     */
    public int deleteCpSheetObjParticipantById(String id);

    /**
     * 批量删除表格测评
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCpSheetObjParticipantByIds(String[] ids);
    
    /**
     * 查询参与者列表
     * @param sheetId
     * @param sheetType
     * @return
     */
    public List<Map<String,Object>> selectSheetObjPartBySheetIdTypeActId(@Param("sheetId")Long sheetId,@Param("sheetType")Long sheetType,@Param("activityId")Long activityId);
    
    /**
     * 根据多条件删除测评对象或参与者
     * @param cpSheetObjParticipant
     */
    public void delSheetObjPartByMore(CpSheetObjParticipant cpSheetObjParticipant);
    
    /**
     * 根据多条件查询测评对象
     * @param cpSheetObjParticipant
     * @return
     */
    public List<Map<String,String>> selectObjUserByActIdSidStype(CpSheetObjParticipant cpSheetObjParticipant);
    public List<Map<String,String>> selectObjUserByActIdSidStypedept(CpSheetObjParticipant cpSheetObjParticipant);
    public List<CpSheetObjParticipant> selectrankbydeptidandactid(@Param("sheetId") Long sheetId,@Param("deptId") String deptId, @Param("activityId") Long activityId);
}
