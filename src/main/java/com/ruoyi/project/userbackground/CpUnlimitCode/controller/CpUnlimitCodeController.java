package com.ruoyi.project.userbackground.CpUnlimitCode.controller;

import java.util.List;
import java.util.UUID;

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
import com.ruoyi.project.userbackground.CpUnlimitCode.domain.CpUnlimitCode;
import com.ruoyi.project.userbackground.CpUnlimitCode.service.ICpUnlimitCodeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 二维码Controller
 * 
 * @author ruoyi
 * @date 2021-09-09
 */
@Controller
@RequestMapping("/userbackground/CpUnlimitCode")
public class CpUnlimitCodeController extends BaseController
{
    private String prefix = "userbackground/CpUnlimitCode";

    @Autowired
    private ICpUnlimitCodeService cpUnlimitCodeService;

    @RequiresPermissions("userbackground:CpUnlimitCode:view")
    @GetMapping()
    public String CpUnlimitCode()
    {
        return prefix + "/CpUnlimitCode";
    }

    /**
     * 查询二维码列表
     */
    @RequiresPermissions("userbackground:CpUnlimitCode:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CpUnlimitCode cpUnlimitCode)
    {
        startPage();
        List<CpUnlimitCode> list = cpUnlimitCodeService.selectCpUnlimitCodeList(cpUnlimitCode);
        return getDataTable(list);
    }

    /**
     * 导出二维码列表
     */
    @RequiresPermissions("userbackground:CpUnlimitCode:export")
    @Log(title = "二维码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CpUnlimitCode cpUnlimitCode)
    {
        List<CpUnlimitCode> list = cpUnlimitCodeService.selectCpUnlimitCodeList(cpUnlimitCode);
        ExcelUtil<CpUnlimitCode> util = new ExcelUtil<CpUnlimitCode>(CpUnlimitCode.class);
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
    @RequiresPermissions("userbackground:CpUnlimitCode:add")
    @Log(title = "二维码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CpUnlimitCode cpUnlimitCode)
    {
        return toAjax(cpUnlimitCodeService.insertCpUnlimitCode(cpUnlimitCode));
    }

    @PostMapping("/addcode")
    @ResponseBody
    public String addcode(String pathcode,Long activityId,String p1)
    {
        CpUnlimitCode cpUnlimitCode = new CpUnlimitCode();
        cpUnlimitCode.setId(p1);
        cpUnlimitCode.setQrCodePath(pathcode);
        cpUnlimitCode.setActityId(activityId);
        cpUnlimitCode.setScanningTimes(0L);
        if(cpUnlimitCodeService.selectCpUnlimitCodeByactityid(activityId.toString()) == null){
            cpUnlimitCodeService.insertCpUnlimitCode(cpUnlimitCode);
        }
        return "success";
    }

    /**
     * 修改二维码
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        CpUnlimitCode cpUnlimitCode = cpUnlimitCodeService.selectCpUnlimitCodeById(id);
        mmap.put("cpUnlimitCode", cpUnlimitCode);
        return prefix + "/edit";
    }

    /**
     * 修改保存二维码
     */
    @RequiresPermissions("userbackground:CpUnlimitCode:edit")
    @Log(title = "二维码", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CpUnlimitCode cpUnlimitCode)
    {
        return toAjax(cpUnlimitCodeService.updateCpUnlimitCode(cpUnlimitCode));
    }

    /**
     * 删除二维码
     */
    @RequiresPermissions("userbackground:CpUnlimitCode:remove")
    @Log(title = "二维码", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cpUnlimitCodeService.deleteCpUnlimitCodeByIds(ids));
    }



}
