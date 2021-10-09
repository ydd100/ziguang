package com.ruoyi.project.userbackground.CpDepartmentCode.controller;

import java.util.ArrayList;
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
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.userbackground.CpDepartmentCode.domain.CpDepartmentCode;
import com.ruoyi.project.userbackground.CpDepartmentCode.service.ICpDepartmentCodeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 二维码Controller
 * 
 * @author ruoyi
 * @date 2021-09-08
 */
@Controller
@RequestMapping("/userbackground/CpDepartmentCode")
public class CpDepartmentCodeController extends BaseController
{
    private String prefix = "userbackground/CpDepartmentCode";

    @Autowired
    private ICpDepartmentCodeService cpDepartmentCodeService;

    @RequiresPermissions("userbackground:CpDepartmentCode:view")
    @GetMapping()
    public String CpDepartmentCode()
    {
        return prefix + "/CpDepartmentCode";
    }

    /**
     * 查询二维码列表
     */
    @RequiresPermissions("userbackground:CpDepartmentCode:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CpDepartmentCode cpDepartmentCode)
    {
        startPage();
        List<CpDepartmentCode> list = cpDepartmentCodeService.selectCpDepartmentCodeList(cpDepartmentCode);
        return getDataTable(list);
    }

//    下载
    @PostMapping("/selectcode")
    @ResponseBody
    public TableDataInfo selectcode(String id)
    {
        startPage();
        List<CpDepartmentCode> list = new ArrayList<>();
        CpDepartmentCode c = cpDepartmentCodeService.selectCpDepartmentCodeById(id);
        list.add(0,c);
        return getDataTable(list);
    }

    /**
     * 导出二维码列表
     */
    @RequiresPermissions("userbackground:CpDepartmentCode:export")
    @Log(title = "二维码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CpDepartmentCode cpDepartmentCode)
    {
        List<CpDepartmentCode> list = cpDepartmentCodeService.selectCpDepartmentCodeList(cpDepartmentCode);
        ExcelUtil<CpDepartmentCode> util = new ExcelUtil<CpDepartmentCode>(CpDepartmentCode.class);
        return util.exportExcel(list, "二维码数据");
    }

    /**
     * 新增二维码
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存二维码
     */
    @RequiresPermissions("userbackground:CpDepartmentCode:add")
    @Log(title = "二维码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CpDepartmentCode cpDepartmentCode)
    {
        return toAjax(cpDepartmentCodeService.insertCpDepartmentCode(cpDepartmentCode));
    }

    /**
     * 修改二维码
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        CpDepartmentCode cpDepartmentCode = cpDepartmentCodeService.selectCpDepartmentCodeById(id);
        mmap.put("cpDepartmentCode", cpDepartmentCode);
        return prefix + "/edit";
    }

    /**
     * 修改保存二维码
     */
    @RequiresPermissions("userbackground:CpDepartmentCode:edit")
    @Log(title = "二维码", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CpDepartmentCode cpDepartmentCode)
    {
        return toAjax(cpDepartmentCodeService.updateCpDepartmentCode(cpDepartmentCode));
    }

    /**
     * 删除二维码
     */
    @RequiresPermissions("userbackground:CpDepartmentCode:remove")
    @Log(title = "二维码", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cpDepartmentCodeService.deleteCpDepartmentCodeByIds(ids));
    }
}
