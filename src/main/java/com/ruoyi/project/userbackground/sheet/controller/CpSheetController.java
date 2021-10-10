package com.ruoyi.project.userbackground.sheet.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.project.userbackground.columnData.domain.CpColumnData;
import com.ruoyi.project.userbackground.columnData.service.ICpColumnDataService;
import com.ruoyi.project.userbackground.evaluateItem.domain.CpEvaluateItem;
import com.ruoyi.project.userbackground.evaluateItem.service.ICpEvaluateItemService;
import com.ruoyi.project.userbackground.sheet.domain.CpSheet;
import com.ruoyi.project.userbackground.sheet.service.ICpSheetService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 测评表格Controller
 * 
 * @author ying
 * @date 2021-09-02
 */
@Controller
@RequestMapping("/userbackground/evaluation")
public class CpSheetController extends BaseController {
	private String prefix = "userbackground/evaluation";

	@Autowired
	private ICpSheetService cpSheetService;
	
	@Autowired
	private ICpColumnDataService cpColumnDataService;
	
	@Autowired
	private ICpEvaluateItemService cpEvaluateItemService;

	@RequiresPermissions("userbackground:evaluation:view")
	@GetMapping()
	public String evaluation() {
		return prefix + "/evaluatable";
	}

	/**
	 * 查询测评表格列表
	 */
	@RequiresPermissions("userbackground:evaluation:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(CpSheet cpSheet) {
		startPage();
		List<CpSheet> list = cpSheetService.selectCpSheetList(cpSheet);
		return getDataTable(list);
	}

	/**
	 * 导出测评表格列表
	 */
	@RequiresPermissions("userbackground:evaluation:export")
	@Log(title = "测评表格", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(CpSheet cpSheet) {
		List<CpSheet> list = cpSheetService.selectCpSheetList(cpSheet);
		ExcelUtil<CpSheet> util = new ExcelUtil<CpSheet>(CpSheet.class);
		return util.exportExcel(list, "测评表格数据");
	}

	/**
	 * 新增测评表格
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/addEvaltable1";
	}

	/**
	 * 新增保存测评表格---返回id
	 */
	@RequiresPermissions("userbackground:evaluation:add")
	@Log(title = "测评表格", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public String addSave(CpSheet cpSheet) {
		if(cpSheet.getId() == null) {
			Long userId = ShiroUtils.getUserId();
			cpSheet.setOperateId(userId);
			cpSheetService.insertCpSheet(cpSheet);
		}else {
			cpSheetService.updateCpSheet(cpSheet);
		}
		return String.valueOf(cpSheet.getId());
	}

	/**
	 * 修改测评表格
	 */
	@GetMapping("/edit")
	public String edit(Long id, ModelMap mmap) {
		CpSheet cpSheet = cpSheetService.selectCpSheetById(id);
		mmap.put("cpSheet", cpSheet);
		return prefix + "/editEvaltable";
	}

	/**
	 * 修改保存测评表格
	 */
	@RequiresPermissions("userbackground:evaluation:edit")
	@Log(title = "测评表格", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(CpSheet cpSheet) {
		return toAjax(cpSheetService.updateCpSheet(cpSheet));
	}

	/**
	 * 删除测评表格
	 */
	@RequiresPermissions("userbackground:evaluation:remove")
	@Log(title = "测评表格", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(cpSheetService.deleteCpSheetByIds(ids));
	}
	
	/**
	 * 删除测评表格
	 */
	@RequiresPermissions("userbackground:evaluation:remove")
	@Log(title = "测评表格", businessType = BusinessType.DELETE)
	@PostMapping("/delSheetData")
	@ResponseBody
	public AjaxResult delSheetData(Long id) {
		cpEvaluateItemService.deleteCpEvaluateItemBySheetId(id);
		cpColumnDataService.deleteCpColumnDataBySheetId(id);
		return toAjax(cpSheetService.deleteCpSheetById(id));
	}
	
	/**
	 * 上传封面和背景图片
	 * @param file
	 * @param sheetId
	 * @param pathType
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/uploadFile")
	@ResponseBody
//	public AjaxResult uploadFile(MultipartFile file,Long sheetId,String pathType,HttpServletRequest request) throws Exception {
	public String uploadFile(MultipartFile file,Long sheetId,String pathType,HttpServletRequest request) throws Exception {
//		String filePath = RuoYiConfig.getImgPath(request);
		String aaafile = RuoYiConfig.getProfile()+"/img/evaImg/";

		// 上传并返回新文件名称
		String oriFileName = file.getOriginalFilename();
		String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));
        String fileName = pathType+DateUtils.dateTimeNow() + suffix;
//        File newFile = new File(filePath+'/'+fileName);
		File newFile = new File(aaafile+fileName);

        if(!newFile.exists()) {
        	newFile.mkdir();
        }
        file.transferTo(newFile);
        /*CpSheet cpSheet = new CpSheet();
        cpSheet.setId(sheetId);
        if("cover".equals(pathType)) {
//        	String coverPath = "/img/evaImg/"+fileName;
			String coverPath = "/profile/img/evaImg/"+fileName;

        	cpSheet.setCoverPath(coverPath);
        }else if("back".equals(pathType)){
//        	String backPath = "/img/evaImg/"+fileName;
			String backPath = "/profile/img/evaImg/"+fileName;

        	cpSheet.setBackPath(backPath);
        }
        return toAjax(cpSheetService.updateCpSheet(cpSheet));*/
		return "/profile/img/evaImg/"+fileName;
		
	}
	
	@Transactional
	@PostMapping("/copySheetAllInfo")
	@ResponseBody
	public AjaxResult copySheetAllInfo(Long id) {
		CpSheet sheet = cpSheetService.selectCpSheetById(id);
		
		//除了id和创建时间，其他数据保存一份
		CpSheet copySheet = new CpSheet(sheet); 
		cpSheetService.insertCpSheet(copySheet);
		
		//copySheet.getId()新id
		Long copySheetId = copySheet.getId();
		
		//根据sheetId查询column_data数据
		List<CpColumnData> colDataList = cpColumnDataService.selectColDataBySheetId(id);
		
		//遍历列数据并复制新数据，查询对应的选项批量复制
		for (CpColumnData colData : colDataList) {
			CpColumnData copyColData = new CpColumnData(colData);
			copyColData.setSheetId(copySheetId);
			cpColumnDataService.insertCpColumnData(copyColData);
			
			//copyColData.getColumnDataId()新的colDataId
			Long copyColDataId = copyColData.getColumnDataId();
			List<CpEvaluateItem> evaItemList = cpEvaluateItemService.selectCpEvaluateItemByColDataId(colData.getColumnDataId());
			
			//批量保存eva_item----(用不到groupTitle,默认空)
			cpEvaluateItemService.batchInsertEvaItem(copySheetId, copyColDataId, "", evaItemList);
		}
		return success();
		
	}
	
}
