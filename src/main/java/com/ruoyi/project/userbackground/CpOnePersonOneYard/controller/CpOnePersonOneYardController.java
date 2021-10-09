package com.ruoyi.project.userbackground.CpOnePersonOneYard.controller;

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
import com.ruoyi.project.userbackground.CpOnePersonOneYard.domain.CpOnePersonOneYard;
import com.ruoyi.project.userbackground.CpOnePersonOneYard.service.ICpOnePersonOneYardService;
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
@RequestMapping("/userbackground/CpOnePersonOneYard")
public class CpOnePersonOneYardController extends BaseController
{
    private String prefix = "userbackground/CpOnePersonOneYard";

    @Autowired
    private ICpOnePersonOneYardService cpOnePersonOneYardService;

    @RequiresPermissions("userbackground:CpOnePersonOneYard:view")
    @GetMapping()
    public String CpOnePersonOneYard()
    {
        return prefix + "/CpOnePersonOneYard";
    }

    /**
     * 查询二维码列表
     */
    @RequiresPermissions("userbackground:CpOnePersonOneYard:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CpOnePersonOneYard cpOnePersonOneYard)
    {
        startPage();
        List<CpOnePersonOneYard> list = cpOnePersonOneYardService.selectCpOnePersonOneYardList(cpOnePersonOneYard);
        return getDataTable(list);
    }

    /**
     * 导出二维码列表
     */
    @RequiresPermissions("userbackground:CpOnePersonOneYard:export")
    @Log(title = "二维码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CpOnePersonOneYard cpOnePersonOneYard)
    {
        List<CpOnePersonOneYard> list = cpOnePersonOneYardService.selectCpOnePersonOneYardList(cpOnePersonOneYard);
        ExcelUtil<CpOnePersonOneYard> util = new ExcelUtil<CpOnePersonOneYard>(CpOnePersonOneYard.class);
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
    @RequiresPermissions("userbackground:CpOnePersonOneYard:add")
    @Log(title = "二维码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CpOnePersonOneYard cpOnePersonOneYard)
    {
        return toAjax(cpOnePersonOneYardService.insertCpOnePersonOneYard(cpOnePersonOneYard));
    }

    /**
     * 修改二维码
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        CpOnePersonOneYard cpOnePersonOneYard = cpOnePersonOneYardService.selectCpOnePersonOneYardById(id);
        mmap.put("cpOnePersonOneYard", cpOnePersonOneYard);
        return prefix + "/edit";
    }

    /**
     * 修改保存二维码
     */
    @RequiresPermissions("userbackground:CpOnePersonOneYard:edit")
    @Log(title = "二维码", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CpOnePersonOneYard cpOnePersonOneYard)
    {
        return toAjax(cpOnePersonOneYardService.updateCpOnePersonOneYard(cpOnePersonOneYard));
    }

    /**
     * 删除二维码
     */
    @RequiresPermissions("userbackground:CpOnePersonOneYard:remove")
    @Log(title = "二维码", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cpOnePersonOneYardService.deleteCpOnePersonOneYardByIds(ids));
    }
}
