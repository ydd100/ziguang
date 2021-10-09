package com.ruoyi.project.userbackground.sheet.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.userbackground.sheet.mapper.CpSheetMapper;
import com.ruoyi.project.userbackground.sheet.domain.CpSheet;
import com.ruoyi.project.userbackground.sheet.service.ICpSheetService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 测评表格Service业务层处理
 * 
 * @author ying
 * @date 2021-09-02
 */
@Service
public class CpSheetServiceImpl implements ICpSheetService 
{
    @Autowired
    private CpSheetMapper cpSheetMapper;

    /**
     * 查询测评表格
     * 
     * @param id 测评表格主键
     * @return 测评表格
     */
    @Override
    public CpSheet selectCpSheetById(Long id)
    {
        return cpSheetMapper.selectCpSheetById(id);
    }

    /**
     * 查询测评表格列表
     * 
     * @param cpSheet 测评表格
     * @return 测评表格
     */
    @Override
    public List<CpSheet> selectCpSheetList(CpSheet cpSheet)
    {
        return cpSheetMapper.selectCpSheetList(cpSheet);
    }

    /**
     * 新增测评表格
     * 
     * @param cpSheet 测评表格
     * @return 结果
     */
    @Override
    public int insertCpSheet(CpSheet cpSheet)
    {
        cpSheet.setCreateTime(DateUtils.getNowDate());
        return cpSheetMapper.insertCpSheet(cpSheet);
    }

    /**
     * 修改测评表格
     * 
     * @param cpSheet 测评表格
     * @return 结果
     */
    @Override
    public int updateCpSheet(CpSheet cpSheet)
    {
        return cpSheetMapper.updateCpSheet(cpSheet);
    }

    /**
     * 批量删除测评表格
     * 
     * @param ids 需要删除的测评表格主键
     * @return 结果
     */
    @Override
    public int deleteCpSheetByIds(String ids)
    {
        return cpSheetMapper.deleteCpSheetByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除测评表格信息
     * 
     * @param id 测评表格主键
     * @return 结果
     */
    @Override
    public int deleteCpSheetById(Long id)
    {
        return cpSheetMapper.deleteCpSheetById(id);
    }

    /**
     * 根据活动activityId查询测评表格sheet
     * @param activityId
     * @return
     */
	@Override
	public List<Map<String, Object>> selectSheetListByActivityId(Long activityId) {
		return cpSheetMapper.selectSheetListByActivityId(activityId);
	}

    @Override
    public List<Map<String, Object>> selectSheetListByActivityId1(Long activityId,String sheetidList) {
        Long[] sIds = Convert.toLongArray(sheetidList);
        return cpSheetMapper.selectSheetListByActivityId1(activityId,sIds);
    }

}
