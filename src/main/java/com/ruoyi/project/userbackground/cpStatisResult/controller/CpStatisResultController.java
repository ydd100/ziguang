package com.ruoyi.project.userbackground.cpStatisResult.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.ruoyi.project.userbackground.CpDepartmentCode.domain.CpDepartmentCode;
import com.ruoyi.project.userbackground.CpDepartmentCode.service.ICpDepartmentCodeService;
import com.ruoyi.project.userbackground.CpRankCode.domain.CpRankCode;
import com.ruoyi.project.userbackground.CpRankCode.service.ICpRankCodeService;
import com.ruoyi.project.userbackground.cpResult.domain.ParamVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.userbackground.cpStatisResult.domain.CpStatisResult;
import com.ruoyi.project.userbackground.cpStatisResult.service.ICpStatisResultService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 测评信息结果Controller
 * 
 * @author ying
 * @date 2021-09-12
 */
@Controller
@RequestMapping("/userbackground/cpStatisResult")
public class CpStatisResultController extends BaseController
{
    private String prefix = "userbackground/cpStatisResult";

    @Autowired
    private ICpStatisResultService cpStatisResultService;

    @Autowired
    private ICpDepartmentCodeService cpDepartmentCodeService;

    @Autowired
    private ICpRankCodeService cpRankCodeService;



    @RequiresPermissions("userbackground:cpStatisResult:view")
    @GetMapping()
    public String cpStatisResult()
    {
        return prefix + "/cpStatisResult";
    }

    /**
     * 查询测评信息结果列表
     */
    @RequiresPermissions("userbackground:cpStatisResult:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CpStatisResult cpStatisResult)
    {
        startPage();
        List<CpStatisResult> list = cpStatisResultService.selectCpStatisResultList(cpStatisResult);
        return getDataTable(list);
    }

    /**
     * 导出测评信息结果列表
     */
    @RequiresPermissions("userbackground:cpStatisResult:export")
    @Log(title = "测评信息结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CpStatisResult cpStatisResult)
    {
        List<CpStatisResult> list = cpStatisResultService.selectCpStatisResultList(cpStatisResult);
        ExcelUtil<CpStatisResult> util = new ExcelUtil<CpStatisResult>(CpStatisResult.class);
        return util.exportExcel(list, "测评信息结果数据");
    }

    /**
     * 新增测评信息结果
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存测评信息结果
     */
    @RequiresPermissions("userbackground:cpStatisResult:add")
    @Log(title = "测评信息结果", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CpStatisResult cpStatisResult)
    {
        return toAjax(cpStatisResultService.insertCpStatisResult(cpStatisResult));
    }

    /**
     * 修改测评信息结果
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        CpStatisResult cpStatisResult = cpStatisResultService.selectCpStatisResultById(id);
        mmap.put("cpStatisResult", cpStatisResult);
        return prefix + "/edit";
    }

    /**
     * 修改保存测评信息结果
     */
    @RequiresPermissions("userbackground:cpStatisResult:edit")
    @Log(title = "测评信息结果", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CpStatisResult cpStatisResult)
    {
        return toAjax(cpStatisResultService.updateCpStatisResult(cpStatisResult));
    }

    /**
     * 删除测评信息结果
     */
    @RequiresPermissions("userbackground:cpStatisResult:remove")
    @Log(title = "测评信息结果", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cpStatisResultService.deleteCpStatisResultByIds(ids));
    }

    /**
     * 批量保存测评信息
     * @param cpInfoListStr
     * @return
     */
    //@Log(title = "保存测评信息结果", businessType = BusinessType.INSERT)
    @Transactional
    @PostMapping( "/batchSaveCpInfoResult")
    @ResponseBody
    public AjaxResult batchSaveCpInfoResult(ParamVO paramVO,String cpInfoListStr){
        if("rank".equals(paramVO.getCodeType())) {
            //职级制码
            CpRankCode rankCode = cpRankCodeService.selectCpRankCodeByAidDidRid(paramVO.getActivityid(),paramVO.getDeptid(),paramVO.getRankid());
            Long smTimes = rankCode.getScanningTimes();//扫码次数
            if(smTimes == null){
                rankCode.setScanningTimes(1L);
            }else {
                rankCode.setScanningTimes(smTimes + 1);
            }
            //更新扫码次数
            cpRankCodeService.updateCpRankCode(rankCode);
        } else if ("dept".equals(paramVO.getCodeType())) {
            //部门制码
            CpDepartmentCode deptCode = cpDepartmentCodeService.selectCpDepartmentCodeBydeptId(paramVO.getDeptid(),paramVO.getActivityid());
            Long smTimes = deptCode.getScanningTimes();//扫码次数
            if(smTimes == null){
                deptCode.setScanningTimes(1L);
            }else {
                deptCode.setScanningTimes(smTimes + 1);
            }
            //更新扫码次数
            cpDepartmentCodeService.updateCpDepartmentCode(deptCode);
        }

        List<CpStatisResult> cpInfoList = JSONArray.parseArray(cpInfoListStr,CpStatisResult.class);
        cpStatisResultService.batchInsertCpStatisResult(cpInfoList);
        return success();
    }

    /**
     *
     * @param activityId
     * @param sheetId
     * @param isnotRank(1开启0关闭)
     * @param objType(0:个人；1:部门)
     * @param request
     * @return
     */
    @PostMapping( "/selectDeptStatisResult")
    @ResponseBody
    public Map<String,Object> selectDeptStatisResult(Long activityId, Long sheetId, Long isnotRank,String objType, HttpServletRequest request){
        if ("1".equals(objType)) {
            return cpStatisResultService.selectDeptStatisResult(activityId, sheetId, isnotRank);
        }else {
            return cpStatisResultService.selectUserStatisResult(activityId, sheetId, isnotRank);
        }
    }
    
    /**
     *	 查询原始数据
     * @param activityId
     * @param sheetId
     * @param objType
     * @return
     */
    @PostMapping("/selectOriDataResult")
    @ResponseBody
    public TableDataInfo selectOriDataResult(Long activityId, Long sheetId, String objType) {
    	List<Map<String,Object>> list = cpStatisResultService.selectOriDataResult(activityId,sheetId,objType);
        return getDataTable(list);
    }

}
