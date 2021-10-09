package com.ruoyi.project.managementbackground.rank.controller;

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
import com.ruoyi.project.managementbackground.rank.domain.CpRank;
import com.ruoyi.project.managementbackground.rank.service.ICpRankService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 职业级别Controller
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
@Controller
@RequestMapping("/managementbackground/rank")
public class CpRankController extends BaseController
{
    private String prefix = "managementbackground/rank";

    @Autowired
    private ICpRankService cpRankService;

    @RequiresPermissions("managementbackground:rank:view")
    @GetMapping()
    public String rank()
    {
        return prefix + "/rank";
    }

    /**
     * 查询职业级别列表
     */
    @RequiresPermissions("managementbackground:rank:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CpRank cpRank)
    {
        startPage();
        List<CpRank> list = cpRankService.selectCpRankList(cpRank);
        return getDataTable(list);
    }

    /**
     * 导出职业级别列表
     */
    @RequiresPermissions("managementbackground:rank:export")
    @Log(title = "职业级别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CpRank cpRank)
    {
        List<CpRank> list = cpRankService.selectCpRankList(cpRank);
        ExcelUtil<CpRank> util = new ExcelUtil<CpRank>(CpRank.class);
        return util.exportExcel(list, "职业级别数据");
    }

    /**
     * 新增职业级别
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存职业级别
     */
    @RequiresPermissions("managementbackground:rank:add")
    @Log(title = "职业级别", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CpRank cpRank)
    {
        return toAjax(cpRankService.insertCpRank(cpRank));
    }

    /**
     * 修改职业级别
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        CpRank cpRank = cpRankService.selectCpRankById(id);
        mmap.put("cpRank", cpRank);
        return prefix + "/edit";
    }

    /**
     * 修改保存职业级别
     */
    @RequiresPermissions("managementbackground:rank:edit")
    @Log(title = "职业级别", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CpRank cpRank)
    {
        return toAjax(cpRankService.updateCpRank(cpRank));
    }

    /**
     * 删除职业级别
     */
    @RequiresPermissions("managementbackground:rank:remove")
    @Log(title = "职业级别", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cpRankService.deleteCpRankByIds(ids));
    }

    @PostMapping( "/selectbyid")
    @ResponseBody
    public String selectbyid(String id)
    {
        return cpRankService.selectCpRankById(id).getName();
    }
}
