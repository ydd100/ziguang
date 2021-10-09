package com.ruoyi.project.userbackground.columnData.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.userbackground.columnData.mapper.CpColumnDataMapper;
import com.ruoyi.project.userbackground.columnData.domain.CpColumnData;
import com.ruoyi.project.userbackground.columnData.service.ICpColumnDataService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 测评表格-列Service业务层处理
 * 
 * @author ying
 * @date 2021-09-05
 */
@Service
public class CpColumnDataServiceImpl implements ICpColumnDataService 
{
    @Autowired
    private CpColumnDataMapper cpColumnDataMapper;

    /**
     * 查询测评表格-列
     * 
     * @param columnDataId 测评表格-列主键
     * @return 测评表格-列
     */
    @Override
    public CpColumnData selectCpColumnDataByColumnDataId(Long columnDataId)
    {
        return cpColumnDataMapper.selectCpColumnDataByColumnDataId(columnDataId);
    }

    /**
     * 查询测评表格-列列表
     * 
     * @param cpColumnData 测评表格-列
     * @return 测评表格-列
     */
    @Override
    public List<CpColumnData> selectCpColumnDataList(CpColumnData cpColumnData)
    {
        return cpColumnDataMapper.selectCpColumnDataList(cpColumnData);
    }

    /**
     * 新增测评表格-列
     * 
     * @param cpColumnData 测评表格-列
     * @return 结果
     */
    @Override
    public int insertCpColumnData(CpColumnData cpColumnData)
    {
        cpColumnData.setCreateTime(DateUtils.getNowDate());
        return cpColumnDataMapper.insertCpColumnData(cpColumnData);
    }

    /**
     * 修改测评表格-列
     * 
     * @param cpColumnData 测评表格-列
     * @return 结果
     */
    @Override
    public int updateCpColumnData(CpColumnData cpColumnData)
    {
        return cpColumnDataMapper.updateCpColumnData(cpColumnData);
    }

    /**
     * 批量删除测评表格-列
     * 
     * @param columnDataIds 需要删除的测评表格-列主键
     * @return 结果
     */
    @Override
    public int deleteCpColumnDataByColumnDataIds(String columnDataIds)
    {
        return cpColumnDataMapper.deleteCpColumnDataByColumnDataIds(Convert.toStrArray(columnDataIds));
    }

    /**
     * 删除测评表格-列信息
     * 
     * @param columnDataId 测评表格-列主键
     * @return 结果
     */
    @Override
    public int deleteCpColumnDataByColumnDataId(Long columnDataId)
    {
        return cpColumnDataMapper.deleteCpColumnDataByColumnDataId(columnDataId);
    }

    /**
     * 根据sheet_id获取column的最大排序的序号
     * 
     * @param sheetId
     * @return
     */
	@Override
	public Long selectMaxSortGroupBySheetId(Long sheetId) {
		Map<String, Long> map = cpColumnDataMapper.selectMaxSortGroupBySheetId(sheetId);
		Long sort = 1L;
		if(map != null) {
			Long columnSort = map.get("columnSort");
			sort = columnSort+1;
		}
		return sort;
	}

	/**
     * 根据sheetId删除column_data
     * @param sheetId
     * @return
     */
	@Override
	public int deleteCpColumnDataBySheetId(Long sheetId) {
		return cpColumnDataMapper.deleteCpColumnDataBySheetId(sheetId);
	}

	/**
     * 根据sheetId查询column_data和eva_item组合列表
     * @param sheetId
     * @return
     */
	@Override
	public List<Map<String,Object>> selectCpColumnDataBySheetId(Long sheetId) {
		return cpColumnDataMapper.selectCpColumnDataBySheetId(sheetId);
	}

	/**
     * 根据sheetId查询column_data数据
     * @param sheetId
     * @return
     */
	@Override
	public List<CpColumnData> selectColDataBySheetId(Long sheetId) {
		return cpColumnDataMapper.selectColDataBySheetId(sheetId);
	}
}
