package com.ruoyi.project.userbackground.evaluateItem.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.userbackground.evaluateItem.mapper.CpEvaluateItemMapper;
import com.ruoyi.project.userbackground.evaluateItem.domain.CpEvaluateItem;
import com.ruoyi.project.userbackground.evaluateItem.service.ICpEvaluateItemService;
import com.mysql.cj.util.StringUtils;
import com.ruoyi.common.utils.text.Convert;

/**
 * 测评表格-选项Service业务层处理
 * 
 * @author ying
 * @date 2021-09-05
 */
@Service
public class CpEvaluateItemServiceImpl implements ICpEvaluateItemService 
{
    @Autowired
    private CpEvaluateItemMapper cpEvaluateItemMapper;

    /**
     * 查询测评表格-选项
     * 
     * @param evaluateItemId 测评表格-选项主键
     * @return 测评表格-选项
     */
    @Override
    public CpEvaluateItem selectCpEvaluateItemByEvaluateItemId(Long evaluateItemId)
    {
        return cpEvaluateItemMapper.selectCpEvaluateItemByEvaluateItemId(evaluateItemId);
    }

    /**
     * 查询测评表格-选项列表
     * 
     * @param cpEvaluateItem 测评表格-选项
     * @return 测评表格-选项
     */
    @Override
    public List<CpEvaluateItem> selectCpEvaluateItemList(CpEvaluateItem cpEvaluateItem)
    {
        return cpEvaluateItemMapper.selectCpEvaluateItemList(cpEvaluateItem);
    }

    /**
     * 新增测评表格-选项
     * 
     * @param cpEvaluateItem 测评表格-选项
     * @return 结果
     */
    @Override
    public int insertCpEvaluateItem(CpEvaluateItem cpEvaluateItem)
    {
        return cpEvaluateItemMapper.insertCpEvaluateItem(cpEvaluateItem);
    }

    /**
     * 修改测评表格-选项
     * 
     * @param cpEvaluateItem 测评表格-选项
     * @return 结果
     */
    @Override
    public int updateCpEvaluateItem(CpEvaluateItem cpEvaluateItem)
    {
        return cpEvaluateItemMapper.updateCpEvaluateItem(cpEvaluateItem);
    }

    /**
     * 批量删除测评表格-选项
     * 
     * @param evaluateItemIds 需要删除的测评表格-选项主键
     * @return 结果
     */
    @Override
    public int deleteCpEvaluateItemByEvaluateItemIds(String evaluateItemIds)
    {
        return cpEvaluateItemMapper.deleteCpEvaluateItemByEvaluateItemIds(Convert.toStrArray(evaluateItemIds));
    }

    /**
     * 删除测评表格-选项信息
     * 
     * @param evaluateItemId 测评表格-选项主键
     * @return 结果
     */
    @Override
    public int deleteCpEvaluateItemByEvaluateItemId(Long evaluateItemId)
    {
        return cpEvaluateItemMapper.deleteCpEvaluateItemByEvaluateItemId(evaluateItemId);
    }

    /**
     * 根据sheetId删除eval_item
     * @param sheetId
     * @return
     */
	@Override
	public int deleteCpEvaluateItemBySheetId(Long sheetId) {
		return cpEvaluateItemMapper.deleteCpEvaluateItemBySheetId(sheetId);
	}

	/**
     * 根据sheetId查询eval_item
     * @param sheetId
     * @return
     */
	@Override
	public List<CpEvaluateItem> selectCpEvaluateItemBySheetId(Long sheetId) {
		// TODO Auto-generated method stub
		return cpEvaluateItemMapper.selectCpEvaluateItemBySheetId(sheetId);
	}

	/**
     * 根据column_data_id查询列对应项
     * @param columnDataId
     * @return
     */
	@Override
	public List<CpEvaluateItem> selectCpEvaluateItemByColDataId(Long columnDataId) {
		return cpEvaluateItemMapper.selectCpEvaluateItemByColDataId(columnDataId);
	}

	/**
     * 批量保存eva_item
     * @param map
     * @return
     */
	@Override
	public void batchInsertEvaItem(Long sheetId, Long columnDataId, String groupTitle, List<CpEvaluateItem> evaItemList) {
		//将sheetId，coldataId，evaItemList放入Map<String,Object>中
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sheetId", sheetId);
		map.put("columnDataId", columnDataId);
		map.put("groupTitle", groupTitle);
		map.put("evaItemList", evaItemList);
		cpEvaluateItemMapper.batchInsertEvaItem(map);
	}

	/**
     * 根据column_data_id批量删除选项
     * @param columnDataIds
     * @return
     */
	@Override
	public int deleteCpEvaluateItemByColumnDataIds(String columnDataIds) {
		return cpEvaluateItemMapper.deleteCpEvaluateItemByColumnDataIds(Convert.toStrArray(columnDataIds));
	}

	/**
     * 根据column_data_id删除eva_item
     * @param columnDataId
     * @return
     */
	@Override
	public int deleteCpEvaluateItemByColumnDataId(Long columnDataId) {
		return cpEvaluateItemMapper.deleteCpEvaluateItemByColumnDataId(columnDataId);
	}

	/**
     * 根据sheetId和groupTitle查询colDataId
     * @param sheetId
     * @param groupTitle
     * @return
     */
	@Override
	public String selectColDataIdListBySheetIdGroupTitle(Long sheetId, String groupTitle) {
		List<Map<String,Long>> mapList = cpEvaluateItemMapper.selectColDataIdListBySheetIdGroupTitle(sheetId, groupTitle);
		String colDataIds = "";
		for (Map<String,Long> map : mapList) {
			colDataIds += map.get("colDataId") + ",";
		}
		if(!StringUtils.isNullOrEmpty(colDataIds)) {
			colDataIds = colDataIds.substring(0, colDataIds.length()-1);
		}
		return colDataIds;
	}
}
