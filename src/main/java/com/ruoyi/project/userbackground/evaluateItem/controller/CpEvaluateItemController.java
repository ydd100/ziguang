package com.ruoyi.project.userbackground.evaluateItem.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.userbackground.evaluateItem.domain.CpEvaluateItem;
import com.ruoyi.project.userbackground.evaluateItem.service.ICpEvaluateItemService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 测评表格-选项Controller
 * 
 * @author ying
 * @date 2021-09-05
 */
@Controller
@RequestMapping("/userbackground/evaluateItem")
public class CpEvaluateItemController extends BaseController {
	private String prefix = "userbackground/evaluateItem";

	@Autowired
	private ICpEvaluateItemService cpEvaluateItemService;

	/**
	 * 查询测评表格-选项列表
	 */
	@RequiresPermissions("userbackground:evaluateItem:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(CpEvaluateItem cpEvaluateItem) {
		startPage();
		List<CpEvaluateItem> list = cpEvaluateItemService.selectCpEvaluateItemList(cpEvaluateItem);
		return getDataTable(list);
	}

	/**
	 * 新增测评表格-选项
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存测评表格-选项
	 */
	//@RequiresPermissions("userbackground:evaluateItem:add")
	@Log(title = "测评表格-选项", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(CpEvaluateItem cpEvaluateItem,String evaListStr) {
		List<CpEvaluateItem> evaList = JSON.parseArray(evaListStr,CpEvaluateItem.class);
		cpEvaluateItemService.deleteCpEvaluateItemByColumnDataId(cpEvaluateItem.getColumnDataId());
		
		for (CpEvaluateItem evaItem : evaList) {
			CpEvaluateItem newEvaItem = new CpEvaluateItem(evaItem);
			newEvaItem.setSheetId(cpEvaluateItem.getSheetId());
			newEvaItem.setColumnDataId(cpEvaluateItem.getColumnDataId());
			newEvaItem.setEvaluateTitle(cpEvaluateItem.getEvaluateTitle());
			newEvaItem.setGroupTitle(cpEvaluateItem.getGroupTitle());
			cpEvaluateItemService.insertCpEvaluateItem(newEvaItem);
			
		}
		
		return success();
	}

	/**
	 * 修改测评表格-选项
	 */
	@GetMapping("/edit/{evaluateItemId}")
	public String edit(@PathVariable("evaluateItemId") Long evaluateItemId, ModelMap mmap) {
		CpEvaluateItem cpEvaluateItem = cpEvaluateItemService.selectCpEvaluateItemByEvaluateItemId(evaluateItemId);
		mmap.put("cpEvaluateItem", cpEvaluateItem);
		return prefix + "/edit";
	}

	/**
	 * 修改保存测评表格-选项
	 */
	@RequiresPermissions("userbackground:evaluateItem:edit")
	@Log(title = "测评表格-选项", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(CpEvaluateItem cpEvaluateItem) {
		return toAjax(cpEvaluateItemService.updateCpEvaluateItem(cpEvaluateItem));
	}

	/**
	 * 删除测评表格-选项
	 */
	@RequiresPermissions("userbackground:evaluateItem:remove")
	@Log(title = "测评表格-选项", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(cpEvaluateItemService.deleteCpEvaluateItemByEvaluateItemIds(ids));
	}
	
	/**
	 * 根据表格sheetId查询所有项
	 * @param sheetId
	 * @return
	 */
	@PostMapping("/listEvaItemBySheetId")
	@ResponseBody
	public List<CpEvaluateItem> listEvaItemBySheetId(Long sheetId) {
		List<CpEvaluateItem> list = cpEvaluateItemService.selectCpEvaluateItemBySheetId(sheetId);
		return list;
	}
	
	/**
	 * 根据列的colDataId查询相关项
	 * @param sheetId
	 * @return
	 */
	@PostMapping("/listEvaItemByColDataId")
	@ResponseBody
	public List<CpEvaluateItem> listEvaItemByColDataId(Long colDataId) {
		//根据原colDataId查询evaItem数据
		List<CpEvaluateItem> evaItemList = cpEvaluateItemService.selectCpEvaluateItemByColDataId(colDataId);
		return evaItemList;
	}
}
