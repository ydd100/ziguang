package com.ruoyi.project.managementbackground.template.controller;

import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
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
import com.ruoyi.project.managementbackground.template.domain.CpTemplate;
import com.ruoyi.project.managementbackground.template.service.ICpTemplateService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 测评模板Controller
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
@Controller
@RequestMapping("/managementbackground/template")
public class CpTemplateController extends BaseController
{
    private String prefix = "managementbackground/template";

    @Autowired
    private ICpTemplateService cpTemplateService;

    @RequiresPermissions("managementbackground:template:view")
    @GetMapping()
    public String template()
    {
        return prefix + "/template";
    }

    /**
     * 查询测评模板列表
     */
    @RequiresPermissions("managementbackground:template:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CpTemplate cpTemplate)
    {
        startPage();
        List<CpTemplate> list = cpTemplateService.selectCpTemplateList(cpTemplate);
        return getDataTable(list);
    }

    /**
     * 导出测评模板列表
     */
    @RequiresPermissions("managementbackground:template:export")
    @Log(title = "测评模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CpTemplate cpTemplate)
    {
        List<CpTemplate> list = cpTemplateService.selectCpTemplateList(cpTemplate);
        ExcelUtil<CpTemplate> util = new ExcelUtil<CpTemplate>(CpTemplate.class);
        return util.exportExcel(list, "测评模板数据");
    }

    /**
     * 新增测评模板
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
    @GetMapping("/look")
    public String look(ModelMap mmap,Long id)
    {
        CpTemplate cpTemplate = cpTemplateService.selectCpTemplateById(id);
        mmap.put("cpTemplate", cpTemplate);
        return prefix + "/look";
    }

    /**
     * 新增保存测评模板
     */
    @RequiresPermissions("managementbackground:template:add")
    @Log(title = "测评模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CpTemplate cpTemplate)
    {
        cpTemplate.setCreateUserId(ShiroUtils.getUserId());
        cpTemplate.setCreateUserName(ShiroUtils.getLoginName());
        return toAjax(cpTemplateService.insertCpTemplate(cpTemplate));
    }

    /**
     * 修改测评模板
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CpTemplate cpTemplate = cpTemplateService.selectCpTemplateById(id);
        mmap.put("cpTemplate", cpTemplate);
        return prefix + "/edit";
    }

    /**
     * 修改保存测评模板
     */
    @RequiresPermissions("managementbackground:template:edit")
    @Log(title = "测评模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CpTemplate cpTemplate)
    {
        return toAjax(cpTemplateService.updateCpTemplate(cpTemplate));
    }

    /**
     * 删除测评模板
     */
    @RequiresPermissions("managementbackground:template:remove")
    @Log(title = "测评模板", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cpTemplateService.deleteCpTemplateByIds(ids));
    }
}
