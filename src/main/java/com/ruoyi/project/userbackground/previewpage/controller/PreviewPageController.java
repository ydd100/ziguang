package com.ruoyi.project.userbackground.previewpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.userbackground.sheet.domain.CpSheet;
import com.ruoyi.project.userbackground.sheet.service.ICpSheetService;

/**
 * 测评表格Controller
 * 
 * @author ying
 * @date 2021-09-07
 */
@Controller
@RequestMapping("/userbackground/previewpage")
public class PreviewPageController extends BaseController  {
	private String prefix = "userbackground/previewpage";
	
	@Autowired
	private ICpSheetService cpSheetService;
	
	/**
	 * 预览
	 * @return
	 */
	@GetMapping("/cpPreview")
	public String cePreview(Long sheetId,ModelMap mmap) {
		CpSheet cpSheet = cpSheetService.selectCpSheetById(sheetId);
		mmap.put("cpSheet",cpSheet);
		return prefix + "/cpPreview";
	}
	
	/**
	 * 开始测评
	 * @param sheetId
	 * @param mmap
	 * @return
	 */
	@GetMapping("/toStartCp")
	public String toStartCp(Long sheetId,ModelMap mmap) {
		CpSheet cpSheet = cpSheetService.selectCpSheetById(sheetId);
		mmap.put("cpSheet",cpSheet);
		return prefix + "/startCpPreview";
	}
	
	/**
	 * 提交测评跳转结束页
	 * @param sheetId
	 * @param mmap
	 * @return
	 */
	@GetMapping("/toEndCp")
	public String toEndCp(Long sheetId,ModelMap mmap) {
		CpSheet cpSheet = cpSheetService.selectCpSheetById(sheetId);
		mmap.put("cpSheet",cpSheet);
		return prefix + "/endCpPreview";
	}
}
