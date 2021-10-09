package com.ruoyi.project.userbackground.CpRankCode.controller;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.userbackground.participant.domain.CpSheetObjParticipant;
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
import com.ruoyi.project.userbackground.CpRankCode.domain.CpRankCode;
import com.ruoyi.project.userbackground.CpRankCode.service.ICpRankCodeService;
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
@RequestMapping("/userbackground/CpRankCode")
public class CpRankCodeController extends BaseController
{
    private String prefix = "userbackground/CpRankCode";

    @Autowired
    private ICpRankCodeService cpRankCodeService;

    @RequiresPermissions("userbackground:CpRankCode:view")
    @GetMapping()
    public String CpRankCode()
    {
        return prefix + "/CpRankCode";
    }

    /**
     * 查询二维码列表
     */
    @RequiresPermissions("userbackground:CpRankCode:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CpRankCode cpRankCode)
    {
        startPage();
        List<CpRankCode> list = cpRankCodeService.selectCpRankCodeList(cpRankCode);
        return getDataTable(list);
    }



    @PostMapping("/selectbyid")
    @ResponseBody
    public CpRankCode selectbyid(String id)
    {
        CpRankCode c = cpRankCodeService.selectCpRankCodeById(id);
        return c;
    }

    /**
     * 导出二维码列表
     */
    @RequiresPermissions("userbackground:CpRankCode:export")
    @Log(title = "二维码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CpRankCode cpRankCode)
    {
        List<CpRankCode> list = cpRankCodeService.selectCpRankCodeList(cpRankCode);
        ExcelUtil<CpRankCode> util = new ExcelUtil<CpRankCode>(CpRankCode.class);
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
    @RequiresPermissions("userbackground:CpRankCode:add")
    @Log(title = "二维码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CpRankCode cpRankCode)
    {
        return toAjax(cpRankCodeService.insertCpRankCode(cpRankCode));
    }

    /**
     * 修改二维码
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        CpRankCode cpRankCode = cpRankCodeService.selectCpRankCodeById(id);
        mmap.put("cpRankCode", cpRankCode);
        return prefix + "/edit";
    }

    /**
     * 修改保存二维码
     */
    @RequiresPermissions("userbackground:CpRankCode:edit")
    @Log(title = "二维码", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CpRankCode cpRankCode)
    {
        return toAjax(cpRankCodeService.updateCpRankCode(cpRankCode));
    }

    /**
     * 删除二维码
     */
    @RequiresPermissions("userbackground:CpRankCode:remove")
    @Log(title = "二维码", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cpRankCodeService.deleteCpRankCodeByIds(ids));
    }



    @PostMapping( "/selectrankbydeptidandactid")
    @ResponseBody
    public List<CpRankCode> selectrankbydeptidandactid(String deptId, Long activityId){
        List<CpRankCode> objUserIdList = cpRankCodeService.selectrankbydeptidandactid(deptId,activityId);
        return objUserIdList;
    }

    /**
     * 查询投票概况
     * @param activityId
     * @return
     */
    @PostMapping( "/selectVotesStatusByActId")
    @ResponseBody
    public TableDataInfo selectVotesStatusByActId(Long activityId){
        List<Map<String, Object>> mapList = cpRankCodeService.selectVotesStatusByActId(activityId);
        return getDataTable(mapList);
    }

}
