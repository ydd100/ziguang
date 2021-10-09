package com.ruoyi.project.userbackground.rules.controller;

import java.util.*;

import com.alibaba.fastjson.JSON;
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
import com.ruoyi.project.userbackground.rules.domain.CpSheetScoringRules;
import com.ruoyi.project.userbackground.rules.service.ICpSheetScoringRulesService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 计分规则Controller
 * 
 * @author ruoyi
 * @date 2021-09-06
 */
@Controller
@RequestMapping("/userbackground/rules")
public class CpSheetScoringRulesController extends BaseController
{
    private String prefix = "userbackground/rules";

    @Autowired
    private ICpSheetScoringRulesService cpSheetScoringRulesService;

    @RequiresPermissions("userbackground:rules:view")
    @GetMapping()
    public String rules()
    {
        return prefix + "/rules";
    }

    /**
     * 查询计分规则列表
     */
    @RequiresPermissions("userbackground:rules:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CpSheetScoringRules cpSheetScoringRules)
    {
        startPage();
        List<CpSheetScoringRules> list = cpSheetScoringRulesService.selectCpSheetScoringRulesList(cpSheetScoringRules);
        return getDataTable(list);
    }

    /**
     * 导出计分规则列表
     */
    @RequiresPermissions("userbackground:rules:export")
    @Log(title = "计分规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CpSheetScoringRules cpSheetScoringRules)
    {
        List<CpSheetScoringRules> list = cpSheetScoringRulesService.selectCpSheetScoringRulesList(cpSheetScoringRules);
        ExcelUtil<CpSheetScoringRules> util = new ExcelUtil<CpSheetScoringRules>(CpSheetScoringRules.class);
        return util.exportExcel(list, "计分规则数据");
    }

    /**
     * 新增计分规则
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存计分规则
     */
    @RequiresPermissions("userbackground:rules:add")
    @Log(title = "计分规则", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CpSheetScoringRules cpSheetScoringRules)
    {
        return toAjax(cpSheetScoringRulesService.insertCpSheetScoringRules(cpSheetScoringRules));
    }

    /**
     * 修改计分规则
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        CpSheetScoringRules cpSheetScoringRules = cpSheetScoringRulesService.selectCpSheetScoringRulesById(id);
        mmap.put("cpSheetScoringRules", cpSheetScoringRules);
        return prefix + "/edit";
    }

    /**
     * 修改保存计分规则
     */
    @RequiresPermissions("userbackground:rules:edit")
    @Log(title = "计分规则", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CpSheetScoringRules cpSheetScoringRules)
    {
        return toAjax(cpSheetScoringRulesService.updateCpSheetScoringRules(cpSheetScoringRules));
    }

    /**
     * 删除计分规则
     */
    //@RequiresPermissions("userbackground:rules:remove")
    @Log(title = "计分规则", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cpSheetScoringRulesService.deleteCpSheetScoringRulesByIds(ids));
    }

    @PostMapping("/batchaddjf")
    @ResponseBody
    public String batchaddjf(Long sheetid,String deptid,String rankids,String rankName,String points,Long aaaa,Long activityId)
    {
    	//先删除原有数据
    	CpSheetScoringRules scoreRules = new CpSheetScoringRules(sheetid, deptid, aaaa, activityId,rankName);
    	cpSheetScoringRulesService.delSheetScoreRulesByMore(scoreRules);
    	
    	List<String> lis = Arrays.asList(rankids.split(","));
        if(lis.size() > 0){
            for(int i = 0;i<lis.size();i++){
                String id = UUID.randomUUID().toString();
                CpSheetScoringRules c = new CpSheetScoringRules();
                c.setId(id);
                c.setSheetId(sheetid);
                c.setDeptId(deptid);
                c.setRankId(lis.get(i));
                c.setGroupName(rankName);
                c.setFraction(points);
                c.setSheetType(aaaa);
                c.setActivityId(activityId);
                cpSheetScoringRulesService.insertCpSheetScoringRules(c);
            }
        }
        return "success";
    }
    
    /**
     * 获取计分规则列表
     * @param sheetId
     * @param sheetType
     * @param activityId
     * @return
     */
    @PostMapping("/selectJfRulesList")
    @ResponseBody
    public TableDataInfo selectJfRulesList(Long sheetId,Long sheetType,Long activityId){
        List<Map<String, Object>> list = cpSheetScoringRulesService.selectSheetScoreRulesBySheetIdTypeActId(sheetId, sheetType, activityId);
        return getDataTable(list);
    }
    
    /**
     * 删除计分规则列表选中的数
     * @param sheetId
     * @param deptId
     * @param sheetType
     * @param activityId
     * @return
     */
    @Log(title = "计分规则", businessType = BusinessType.DELETE)
    @PostMapping( "/delSheetScoreRulesByMore")
    @ResponseBody
    public AjaxResult delSheetScoreRulesByMore(Long sheetId,String deptId,Long sheetType,Long activityId,String groupName){
    	CpSheetScoringRules scoreRules = new CpSheetScoringRules(sheetId, deptId, sheetType, activityId, groupName);
    	cpSheetScoringRulesService.delSheetScoreRulesByMore(scoreRules);
    	return success();
    }


    @PostMapping( "/selectrankbydeptidandactid")
    @ResponseBody
    public List<CpSheetScoringRules> selectrankbydeptidandactid(Long sheetId, String deptId, Long activityId){
        List<CpSheetScoringRules> objUserIdList = cpSheetScoringRulesService.selectrankbydeptidandactid(sheetId,deptId,activityId);
        return objUserIdList;
    }

    /**
     * 循环保存职级计分
     * @param scoreRulesListStr
     * @return
     */
    @PostMapping( "/batchSaveScoreRules")
    @ResponseBody
    public AjaxResult batchSaveScoreRules(String scoreRulesListStr){
        List<CpSheetScoringRules> scoreRulesList = JSON.parseArray(scoreRulesListStr,CpSheetScoringRules.class);
        for (CpSheetScoringRules scoreRules : scoreRulesList) {
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            scoreRules.setId(uuid);
            scoreRules.setCreateTime(new Date());
            cpSheetScoringRulesService.insertCpSheetScoringRules(scoreRules);
        }
        return success();
    }
    
    /**
     * 循环修改职级计分
     * @param scoreRulesListStr
     * @return
     */
    @PostMapping( "/batchEditScoreRules")
    @ResponseBody
    public AjaxResult batchEditScoreRules(String scoreRulesListStr){
        List<CpSheetScoringRules> scoreRulesList = JSON.parseArray(scoreRulesListStr,CpSheetScoringRules.class);
        for (CpSheetScoringRules scoreRules : scoreRulesList) {
            cpSheetScoringRulesService.updateCpSheetScoringRules(scoreRules);
        }
        return success();
    }
    
    /**
     * 	计分规则又改了
     * @param cpSheetScoringRules
     * @return
     */
    @PostMapping("/selectAllJfRulesList")
    @ResponseBody
    public TableDataInfo selectAllJfRulesList(CpSheetScoringRules cpSheetScoringRules){
        List<Map<String, Object>> list = cpSheetScoringRulesService.selectRankScoreRulesByActIdSid(cpSheetScoringRules);
//        List<Map<String, Object>> llist = new ArrayList<>();
//        if(list.size() > 0){
//            for(int i = 0;i<list.size();i++){
//                if(list.get(i).size() == 2){
//                    list.get(i).put("fraction","点击设置");
//                }
//                llist.add(i,list.get(i));
//            }
//        }
        return getDataTable(list);
    }
}
