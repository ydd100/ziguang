package com.ruoyi.project.managementbackground.dept.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ruoyi.project.managementbackground.rank.domain.CpRank;
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
import com.ruoyi.project.managementbackground.dept.domain.CpDept;
import com.ruoyi.project.managementbackground.dept.service.ICpDeptService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 部门Controller
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
@Controller
@RequestMapping("/managementbackground/dept")
public class CpDeptController extends BaseController
{
    private String prefix = "managementbackground/dept";

    @Autowired
    private ICpDeptService cpDeptService;

    @RequiresPermissions("managementbackground:dept:view")
    @GetMapping()
    public String dept()
    {
        return prefix + "/dept";
    }

    /**
     * 查询部门列表
     */
    @RequiresPermissions("managementbackground:dept:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CpDept cpDept)
    {
        startPage();
        List<CpDept> list = cpDeptService.selectCpDeptList(cpDept);
        return getDataTable(list);
    }

    /**
     * 导出部门列表
     */
    @RequiresPermissions("managementbackground:dept:export")
    @Log(title = "部门", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CpDept cpDept)
    {
        List<CpDept> list = cpDeptService.selectCpDeptList(cpDept);
        ExcelUtil<CpDept> util = new ExcelUtil<CpDept>(CpDept.class);
        return util.exportExcel(list, "部门数据");
    }

    /**
     * 新增部门
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存部门
     */
    @RequiresPermissions("managementbackground:dept:add")
    @Log(title = "部门", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CpDept cpDept) throws ParseException {
        cpDept.setCreatTime(new Date());
        return toAjax(cpDeptService.insertCpDept(cpDept));
    }

    /**
     * 修改部门
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        CpDept cpDept = cpDeptService.selectCpDeptById(id);
        mmap.put("cpDept", cpDept);
        return prefix + "/edit";
    }

    /**
     * 修改保存部门
     */
    @RequiresPermissions("managementbackground:dept:edit")
    @Log(title = "部门", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CpDept cpDept)
    {
        return toAjax(cpDeptService.updateCpDept(cpDept));
    }

    /**
     * 删除部门
     */
    @RequiresPermissions("managementbackground:dept:remove")
    @Log(title = "部门", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cpDeptService.deleteCpDeptByIds(ids));
    }

    @PostMapping( "/selectbyid")
    @ResponseBody
    public String selectbyid(String id)
    {
        return cpDeptService.selectCpDeptById(id).getName();
    }

    /**
     * 根据部门名称只能查出一条数据
     * @param cpDept
     * @return
     */
    @PostMapping( "/checkOneByDeptName")
    @ResponseBody
    public String checkOneByDeptName(CpDept cpDept){
        CpDept dept = cpDeptService.checkOneByDeptName(cpDept);
        if(dept == null){
            return "0";//通过
        }else {
            return "1";//已存在
        }
    }
}
