package com.ruoyi.project.userbackground.columnData.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.util.StringUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.userbackground.columnData.domain.CpColumnData;
import com.ruoyi.project.userbackground.columnData.service.ICpColumnDataService;
import com.ruoyi.project.userbackground.evaluateItem.domain.CpEvaluateItem;
import com.ruoyi.project.userbackground.evaluateItem.service.ICpEvaluateItemService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 测评表格-列Controller
 * 
 * @author ying
 * @date 2021-09-05
 */
@Controller
@RequestMapping("/userbackground/columnData")
public class CpColumnDataController extends BaseController {
	private String prefix = "userbackground/columnData";

	@Autowired
	private ICpColumnDataService cpColumnDataService;
	
	@Autowired
	private ICpEvaluateItemService cpEvaluateItemService;

	/**
	 * 查询测评表格-列列表
	 */
	@RequiresPermissions("userbackground:columnData:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(CpColumnData cpColumnData) {
		startPage();
		List<CpColumnData> list = cpColumnDataService.selectCpColumnDataList(cpColumnData);
		return getDataTable(list);
	}

	/**
	 * 新增测评表格-列
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存测评表格-列---返回column_data_id
	 */
	//@RequiresPermissions("userbackground:columnData:add")
	@Log(title = "测评表格-列", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public String addSave(CpColumnData cpColumnData) {
		Long colSort = cpColumnDataService.selectMaxSortGroupBySheetId(cpColumnData.getSheetId());
		cpColumnData.setColumnSort(String.valueOf(colSort));
		cpColumnDataService.insertCpColumnData(cpColumnData);
		return String.valueOf(cpColumnData.getColumnDataId());
	}

	/**
	 * 修改测评表格-列
	 */
	@GetMapping("/edit/{columnDataId}")
	public String edit(@PathVariable("columnDataId") Long columnDataId, ModelMap mmap) {
		CpColumnData cpColumnData = cpColumnDataService.selectCpColumnDataByColumnDataId(columnDataId);
		mmap.put("cpColumnData", cpColumnData);
		return prefix + "/edit";
	}

	/**
	 * 修改保存测评表格-列
	 */
	//@RequiresPermissions("userbackground:columnData:edit")
	@Log(title = "测评表格-列", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(CpColumnData cpColumnData) {
		return toAjax(cpColumnDataService.updateCpColumnData(cpColumnData));
	}

	/**
	 * 删除测评表格-列
	 */
	@RequiresPermissions("userbackground:columnData:remove")
	@Log(title = "测评表格-列", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(cpColumnDataService.deleteCpColumnDataByColumnDataIds(ids));
	}
	
	/**
	 * 根据sheetId查询column_data
	 * @param sheetId
	 * @return
	 */
	@PostMapping("/listColDataBySheetId")
	@ResponseBody
	public List<Map<String,Object>> listColDataBySheetId(Long sheetId) {
		List<Map<String,Object>> list = cpColumnDataService.selectCpColumnDataBySheetId(sheetId);
		return list;
	}
	
	/**
	 * 批量删除colData和evalItem数据
	 * @param ids
	 * @return
	 */
	@Transactional
	@Log(title = "测评表格-列", businessType = BusinessType.DELETE)
	@PostMapping("/delColDataItemInfo")
	@ResponseBody
	public AjaxResult delColDataItemInfo(Long colDataId,Long sheetId,String groupTitle) {
		//判断是否是分组数据,不是分组数据，直接删除colData和evaItem
		if(StringUtils.isNullOrEmpty(groupTitle)) {
			cpEvaluateItemService.deleteCpEvaluateItemByColumnDataId(colDataId);
			cpColumnDataService.deleteCpColumnDataByColumnDataId(colDataId);
		}else {
			//是分组数据，先根据sheetId和groupTitle查询出有多少colDataId
			String colDataIds = cpEvaluateItemService.selectColDataIdListBySheetIdGroupTitle(sheetId,groupTitle);
			//先删除eva_item，在删除column_data
			cpEvaluateItemService.deleteCpEvaluateItemByColumnDataIds(colDataIds);
			cpColumnDataService.deleteCpColumnDataByColumnDataIds(colDataIds);
		}
		return success();
	}
	
	@Transactional
	@PostMapping("/copyColDataItemInfo")
	@ResponseBody
	public AjaxResult copyColDataItemInfo(Long colDataId,Long sheetId,String groupTitle,String newGroupTitle) {
		//判断是否是分组数据,不是分组数据，直接复制colData和evaItem
		if(StringUtils.isNullOrEmpty(groupTitle)) {
			CpColumnData columnData = cpColumnDataService.selectCpColumnDataByColumnDataId(colDataId);
			
			//重新定义新数据，处理column_data_id,其他数据保留
			CpColumnData copyColData = new CpColumnData(columnData);
			copyColData.setSheetId(sheetId);
			cpColumnDataService.insertCpColumnData(copyColData);
			
			//根据原colDataId查询evaItem数据
			List<CpEvaluateItem> evaItemList = cpEvaluateItemService.selectCpEvaluateItemByColDataId(colDataId);
			
			//新的colDataId
			Long copyColDataId = copyColData.getColumnDataId();
			//(用不到groupTitle,默认空)
			cpEvaluateItemService.batchInsertEvaItem(sheetId, copyColDataId, "", evaItemList);
			
		} else {
			//是分组数据，先根据sheetId和groupTitle查询出有多少colDataId
			String colDataIds = cpEvaluateItemService.selectColDataIdListBySheetIdGroupTitle(sheetId,groupTitle);
			
			Long[] colDataIdArr = Convert.toLongArray(colDataIds);
			for(Long cdId : colDataIdArr) {
				CpColumnData columnData = cpColumnDataService.selectCpColumnDataByColumnDataId(cdId);
				
				//重新定义新数据，处理column_data_id,其他数据保留
				CpColumnData copyColData = new CpColumnData(columnData);
				copyColData.setSheetId(sheetId);
				cpColumnDataService.insertCpColumnData(copyColData);
				
				//根据原colDataId查询evaItem数据
				List<CpEvaluateItem> evaItemList = cpEvaluateItemService.selectCpEvaluateItemByColDataId(cdId);
				
				//新的colDataId
				Long copyColDataId = copyColData.getColumnDataId();
				cpEvaluateItemService.batchInsertEvaItem(sheetId, copyColDataId, newGroupTitle, evaItemList);
				
			}
		}
		return success();
	}

	@PostMapping("/update_down_up")
	@ResponseBody
	public String update_down_up(String arrid) {
		List<String> strList = Arrays.asList(arrid.split(","));
		String msg = "";
		if(strList.size() > 0){
			for(int i=0;i<strList.size();i++){
				CpColumnData c = cpColumnDataService.selectCpColumnDataByColumnDataId(Long.parseLong(strList.get(i)));
				c.setColumnSort(String.valueOf(i+1));
				cpColumnDataService.updateCpColumnData(c);
				msg = "success";
			}
		}
		return msg;
	}
}
