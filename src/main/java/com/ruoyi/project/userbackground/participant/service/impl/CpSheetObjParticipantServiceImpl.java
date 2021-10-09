package com.ruoyi.project.userbackground.participant.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.userbackground.participant.mapper.CpSheetObjParticipantMapper;
import com.ruoyi.project.userbackground.participant.domain.CpSheetObjParticipant;
import com.ruoyi.project.userbackground.participant.service.ICpSheetObjParticipantService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 表格测评Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-09-06
 */
@Service
public class CpSheetObjParticipantServiceImpl implements ICpSheetObjParticipantService 
{
    @Autowired
    private CpSheetObjParticipantMapper cpSheetObjParticipantMapper;

    /**
     * 查询表格测评
     * 
     * @param id 表格测评主键
     * @return 表格测评
     */
    @Override
    public CpSheetObjParticipant selectCpSheetObjParticipantById(String id)
    {
        return cpSheetObjParticipantMapper.selectCpSheetObjParticipantById(id);
    }

    /**
     * 查询表格测评列表
     * 
     * @param cpSheetObjParticipant 表格测评
     * @return 表格测评
     */
    @Override
    public List<CpSheetObjParticipant> selectCpSheetObjParticipantList(CpSheetObjParticipant cpSheetObjParticipant)
    {
        return cpSheetObjParticipantMapper.selectCpSheetObjParticipantList(cpSheetObjParticipant);
    }

    /**
     * 新增表格测评
     * 
     * @param cpSheetObjParticipant 表格测评
     * @return 结果
     */
    @Override
    public int insertCpSheetObjParticipant(CpSheetObjParticipant cpSheetObjParticipant)
    {
        return cpSheetObjParticipantMapper.insertCpSheetObjParticipant(cpSheetObjParticipant);
    }

    /**
     * 修改表格测评
     * 
     * @param cpSheetObjParticipant 表格测评
     * @return 结果
     */
    @Override
    public int updateCpSheetObjParticipant(CpSheetObjParticipant cpSheetObjParticipant)
    {
        return cpSheetObjParticipantMapper.updateCpSheetObjParticipant(cpSheetObjParticipant);
    }

    /**
     * 批量删除表格测评
     * 
     * @param ids 需要删除的表格测评主键
     * @return 结果
     */
    @Override
    public int deleteCpSheetObjParticipantByIds(String ids)
    {
        return cpSheetObjParticipantMapper.deleteCpSheetObjParticipantByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除表格测评信息
     * 
     * @param id 表格测评主键
     * @return 结果
     */
    @Override
    public int deleteCpSheetObjParticipantById(String id)
    {
        return cpSheetObjParticipantMapper.deleteCpSheetObjParticipantById(id);
    }

    /**
     * 查询参与者列表
     * @param sheetId
     * @param sheetType
     * @return
     */
	@Override
	public List<Map<String, Object>> selectSheetObjPartBySheetIdTypeActId(Long sheetId, Long sheetType,Long activityId) {
		return cpSheetObjParticipantMapper.selectSheetObjPartBySheetIdTypeActId(sheetId, sheetType,activityId);
	}

	/**
     * 根据多条件删除测评对象或参与者
     * @param cpSheetObjParticipant
     */
	@Override
	public void delSheetObjPartByMore(CpSheetObjParticipant cpSheetObjParticipant) {
		cpSheetObjParticipantMapper.delSheetObjPartByMore(cpSheetObjParticipant);
		
	}

	/**
     * 根据多条件查询测评对象
     * @param cpSheetObjParticipant
     * @return
     */
	@Override
	public List<String> selectObjUserByActIdSidStype(CpSheetObjParticipant cpSheetObjParticipant) {
		List<String> objUserIdList = new ArrayList<String>();
//		List<Map<String,String>> mapList = null;
		if(cpSheetObjParticipant.getSheetType() == 2){

            List<Map<String,String>> mList = cpSheetObjParticipantMapper.selectObjUserByActIdSidStypedept(cpSheetObjParticipant);
            for (Map<String,String> map : mList) {
                objUserIdList.add(map.get("deptId"));
            }
        }else {
            List<Map<String,String>> mapList = cpSheetObjParticipantMapper.selectObjUserByActIdSidStype(cpSheetObjParticipant);
            for (Map<String,String> map : mapList) {
                objUserIdList.add(map.get("objUserId"));
            }
        }
		return objUserIdList;
	}

    @Override
    public List<CpSheetObjParticipant> selectrankbydeptidandactid(Long sheetId,String deptId, Long activityId) {
        return cpSheetObjParticipantMapper.selectrankbydeptidandactid(sheetId,deptId,activityId);
    }
}
