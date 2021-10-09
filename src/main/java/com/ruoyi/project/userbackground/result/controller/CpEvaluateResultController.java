package com.ruoyi.project.userbackground.result.controller;

import java.util.Arrays;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.userbackground.result.domain.CpEvaluateResult;
import com.ruoyi.project.userbackground.result.service.ICpEvaluateResultService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 活动表格关联Controller
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
@Controller
@RequestMapping("/userbackground/result")
public class CpEvaluateResultController extends BaseController
{
    private String prefix = "userbackground/result";

    @Autowired
    private ICpEvaluateResultService cpEvaluateResultService;

    @RequiresPermissions("userbackground:result:view")
    @GetMapping()
    public String result()
    {
        return prefix + "/result";
    }

    /**
     * 查询活动表格关联列表
     */
    @RequiresPermissions("userbackground:result:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CpEvaluateResult cpEvaluateResult)
    {
        startPage();
        List<CpEvaluateResult> list = cpEvaluateResultService.selectCpEvaluateResultList(cpEvaluateResult);
        return getDataTable(list);
    }

    /**
     * 导出活动表格关联列表
     */
    @RequiresPermissions("userbackground:result:export")
    @Log(title = "活动表格关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CpEvaluateResult cpEvaluateResult)
    {
        List<CpEvaluateResult> list = cpEvaluateResultService.selectCpEvaluateResultList(cpEvaluateResult);
        ExcelUtil<CpEvaluateResult> util = new ExcelUtil<CpEvaluateResult>(CpEvaluateResult.class);
        return util.exportExcel(list, "活动表格关联数据");
    }

    /**
     * 新增活动表格关联
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存活动表格关联
     */
    @RequiresPermissions("userbackground:result:add")
    @Log(title = "活动表格关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CpEvaluateResult cpEvaluateResult)
    {
        return toAjax(cpEvaluateResultService.insertCpEvaluateResult(cpEvaluateResult));
    }

    /**
     * 修改活动表格关联
     */
    @GetMapping("/edit/{evaluateResultId}")
    public String edit(@PathVariable("evaluateResultId") Long evaluateResultId, ModelMap mmap)
    {
        CpEvaluateResult cpEvaluateResult = cpEvaluateResultService.selectCpEvaluateResultByEvaluateResultId(evaluateResultId);
        mmap.put("cpEvaluateResult", cpEvaluateResult);
        return prefix + "/edit";
    }

    /**
     * 修改保存活动表格关联
     */
    @RequiresPermissions("userbackground:result:edit")
    @Log(title = "活动表格关联", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CpEvaluateResult cpEvaluateResult)
    {
        return toAjax(cpEvaluateResultService.updateCpEvaluateResult(cpEvaluateResult));
    }

    /**
     * 删除活动表格关联
     */
    @RequiresPermissions("userbackground:result:remove")
    @Log(title = "活动表格关联", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cpEvaluateResultService.deleteCpEvaluateResultByEvaluateResultIds(ids));
    }

    //批量保存
    @Log(title = "活动表格关联", businessType = BusinessType.INSERT)
    @PostMapping("/batchadd")
    @ResponseBody
    public String batchadd(String ids,Long actid)
    {
        //判断活动是否绑定表格
        List<CpEvaluateResult> list = cpEvaluateResultService.selectCpEvaluateResultByact(actid);
        if(list.size() > 0){
            cpEvaluateResultService.deleteCpEvaluateResultByactId(actid);
        }


        List<String> lis = Arrays.asList(ids.split(","));
        for (String string : lis) {
            CpEvaluateResult ct = new CpEvaluateResult();
            ct.setActivityId(actid);
            ct.setSheetId(Long.valueOf(string));
            cpEvaluateResultService.insertCpEvaluateResult(ct);
        }
        return "success";
    }


    @PostMapping("/selectsheetbyact")
    @ResponseBody
    public TableDataInfo selectsheetbyact(Long actid)
    {
        startPage();
        List<CpEvaluateResult> list = cpEvaluateResultService.selectCpEvaluateResultByact(actid);
        return getDataTable(list);
    }
}
