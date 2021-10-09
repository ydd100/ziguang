package com.ruoyi.project.userbackground.CpRankCodeSon.controller;

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
import com.ruoyi.project.userbackground.CpRankCodeSon.domain.CpRankCodeSon;
import com.ruoyi.project.userbackground.CpRankCodeSon.service.ICpRankCodeSonService;
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
@RequestMapping("/userbackground/CpRankCodeSon")
public class CpRankCodeSonController extends BaseController
{
    private String prefix = "userbackground/CpRankCodeSon";

    @Autowired
    private ICpRankCodeSonService cpRankCodeSonService;

    @RequiresPermissions("userbackground:CpRankCodeSon:view")
    @GetMapping()
    public String CpRankCodeSon()
    {
        return prefix + "/CpRankCodeSon";
    }

    /**
     * 查询二维码列表
     */
    @RequiresPermissions("userbackground:CpRankCodeSon:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CpRankCodeSon cpRankCodeSon)
    {
        startPage();
        List<CpRankCodeSon> list = cpRankCodeSonService.selectCpRankCodeSonList(cpRankCodeSon);
        return getDataTable(list);
    }


    @PostMapping("/selectcode")
    @ResponseBody
    public TableDataInfo selectcode(String id)
    {
        startPage();
        List<CpRankCodeSon> list = cpRankCodeSonService.selectCpRankCodeSonListbyrankCodeId(id);
        return getDataTable(list);
    }

    /**
     * 导出二维码列表
     */
    @RequiresPermissions("userbackground:CpRankCodeSon:export")
    @Log(title = "二维码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CpRankCodeSon cpRankCodeSon)
    {
        List<CpRankCodeSon> list = cpRankCodeSonService.selectCpRankCodeSonList(cpRankCodeSon);
        ExcelUtil<CpRankCodeSon> util = new ExcelUtil<CpRankCodeSon>(CpRankCodeSon.class);
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
    @RequiresPermissions("userbackground:CpRankCodeSon:add")
    @Log(title = "二维码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CpRankCodeSon cpRankCodeSon)
    {
        return toAjax(cpRankCodeSonService.insertCpRankCodeSon(cpRankCodeSon));
    }

    /**
     * 修改二维码
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        CpRankCodeSon cpRankCodeSon = cpRankCodeSonService.selectCpRankCodeSonById(id);
        mmap.put("cpRankCodeSon", cpRankCodeSon);
        return prefix + "/edit";
    }

    /**
     * 修改保存二维码
     */
    @RequiresPermissions("userbackground:CpRankCodeSon:edit")
    @Log(title = "二维码", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CpRankCodeSon cpRankCodeSon)
    {
        return toAjax(cpRankCodeSonService.updateCpRankCodeSon(cpRankCodeSon));
    }

    /**
     * 删除二维码
     */
    @RequiresPermissions("userbackground:CpRankCodeSon:remove")
    @Log(title = "二维码", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cpRankCodeSonService.deleteCpRankCodeSonByIds(ids));
    }
}
