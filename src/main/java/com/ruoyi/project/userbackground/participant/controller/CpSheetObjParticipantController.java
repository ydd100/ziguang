package com.ruoyi.project.userbackground.participant.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
import com.ruoyi.project.userbackground.participant.domain.CpSheetObjParticipant;
import com.ruoyi.project.userbackground.participant.service.ICpSheetObjParticipantService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 表格测评Controller
 * 
 * @author ruoyi
 * @date 2021-09-06
 */
@Controller
@RequestMapping("/userbackground/participant")
public class CpSheetObjParticipantController extends BaseController
{
    private String prefix = "userbackground/participant";

    @Autowired
    private ICpSheetObjParticipantService cpSheetObjParticipantService;

    @RequiresPermissions("userbackground:participant:view")
    @GetMapping()
    public String participant()
    {
        return prefix + "/participant";
    }

    /**
     * 查询表格测评列表
     */
    @RequiresPermissions("userbackground:participant:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CpSheetObjParticipant cpSheetObjParticipant)
    {
        startPage();
        List<CpSheetObjParticipant> list = cpSheetObjParticipantService.selectCpSheetObjParticipantList(cpSheetObjParticipant);
        return getDataTable(list);
    }

    /**
     * 导出表格测评列表
     */
    @RequiresPermissions("userbackground:participant:export")
    @Log(title = "表格测评", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CpSheetObjParticipant cpSheetObjParticipant)
    {
        List<CpSheetObjParticipant> list = cpSheetObjParticipantService.selectCpSheetObjParticipantList(cpSheetObjParticipant);
        ExcelUtil<CpSheetObjParticipant> util = new ExcelUtil<CpSheetObjParticipant>(CpSheetObjParticipant.class);
        return util.exportExcel(list, "表格测评数据");
    }

    /**
     * 新增表格测评
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存表格测评
     */
    @RequiresPermissions("userbackground:participant:add")
    @Log(title = "表格测评", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CpSheetObjParticipant cpSheetObjParticipant)
    {
        return toAjax(cpSheetObjParticipantService.insertCpSheetObjParticipant(cpSheetObjParticipant));
    }

    /**
     * 修改表格测评
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        CpSheetObjParticipant cpSheetObjParticipant = cpSheetObjParticipantService.selectCpSheetObjParticipantById(id);
        mmap.put("cpSheetObjParticipant", cpSheetObjParticipant);
        return prefix + "/edit";
    }

    /**
     * 修改保存表格测评
     */
    @RequiresPermissions("userbackground:participant:edit")
    @Log(title = "表格测评", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CpSheetObjParticipant cpSheetObjParticipant)
    {
        return toAjax(cpSheetObjParticipantService.updateCpSheetObjParticipant(cpSheetObjParticipant));
    }

    /**
     * 删除表格测评
     */
    @RequiresPermissions("userbackground:participant:remove")
    @Log(title = "表格测评", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cpSheetObjParticipantService.deleteCpSheetObjParticipantByIds(ids));
    }

    /**
     * 保存测评对象
     * @param sheetid
     * @param useridobj
     * @param activityId
     * @param deptid
     * @param aaaa－－－－//0:个人；1:单位／部门---sheet_type:0:个人测评对象；2:部门测评对象
     * @return
     */
    @PostMapping("/batchadd")
    @ResponseBody
    public String batchadd(Long sheetid,String useridobj,Long activityId,String deptid,Long aaaa)
    {
        //注意：这里易混
        Long sheetType = 0L;//默认个人测评对象
        if (aaaa == 1) {
            sheetType = 2L;//部门测评对象
        }
        //先删除原有数据
        CpSheetObjParticipant objPart = new CpSheetObjParticipant(sheetid, deptid, sheetType, activityId);
        cpSheetObjParticipantService.delSheetObjPartByMore(objPart);

        if (sheetType == 0) {
            List<String> lis = Arrays.asList(useridobj.split(","));
            if(lis.size() > 0){
                for(int i = 0;i<lis.size();i++){
                    CpSheetObjParticipant c = new CpSheetObjParticipant();
                    String id = UUID.randomUUID().toString();
                    c.setId(id);
                    c.setSheetId(sheetid);
                    if(aaaa.toString().equals("0")){
                        c.setObjUserId(lis.get(i));
                    }
                    c.setDeptId(deptid);
                    c.setSheetType(sheetType);
                    c.setActivityId(activityId);
                    cpSheetObjParticipantService.insertCpSheetObjParticipant(c);
                }
            }
        } else if (sheetType == 2) {
            List<String> deptList = Arrays.asList(deptid.split(","));
            if(deptList.size() > 0){
                for(int i = 0;i<deptList.size();i++){
                    CpSheetObjParticipant c = new CpSheetObjParticipant();
                    String id = UUID.randomUUID().toString();
                    c.setId(id);
                    c.setSheetId(sheetid);
                    c.setDeptId(deptList.get(i));
                    c.setSheetType(sheetType);
                    c.setActivityId(activityId);
                    cpSheetObjParticipantService.insertCpSheetObjParticipant(c);
                }
            }
        }

        return "success";
    }

    /**
     *
     * @param sheetid
     * @param rankids
     * @param deptid
     * @param aaaa//0:个人；1:单位／部门---sheet_type:1:个人测评对象；3:部门测评对象
     * @param activityId
     * @return
     */
    @PostMapping("/batchaddcyz")
    @ResponseBody
    public String batchaddcyz(Long sheetid,String rankids,String deptid,Long aaaa,Long activityId)
    {
        //注意：这里易混
        Long sheetType = 1l;//默认个人测评对象
    	if(aaaa == 1){
    		sheetType = 3l;//部门测评对象
        }
    	//先删除原有数据
    	CpSheetObjParticipant objPart = new CpSheetObjParticipant(sheetid, deptid, sheetType, activityId);
    	cpSheetObjParticipantService.delSheetObjPartByMore(objPart);
    	
        List<String> lis = Arrays.asList(rankids.split(","));
        if(lis.size() > 0){
            for(int i = 0;i<lis.size();i++){
            	String id = UUID.randomUUID().toString();
            	CpSheetObjParticipant c = new CpSheetObjParticipant();
                c.setId(id);
                c.setSheetId(sheetid);
                c.setActivityId(activityId);

                c.setDeptId(deptid);
                c.setRankId(lis.get(i));
                c.setSheetType(sheetType);
                cpSheetObjParticipantService.insertCpSheetObjParticipant(c);
            }
        }
        return "success";
    }
    
    /**
     * 查询参与者列表
     * @param sheetId
     * @param sheetType
     * @param activityId
     * @return
     */
    @PostMapping("/selectCyzList")
    @ResponseBody
    public TableDataInfo selectCyzList(Long sheetId,Long sheetType,Long activityId){
        List<Map<String, Object>> list = cpSheetObjParticipantService.selectSheetObjPartBySheetIdTypeActId(sheetId, sheetType,activityId);
        return getDataTable(list);
    }
    
    /**
     * 删除参与者列表选中的数据
     * @param sheetId
     * @param deptId
     * @param sheetType
     * @param activityId
     * @return
     */
    @Log(title = "表格测评", businessType = BusinessType.DELETE)
    @PostMapping( "/delSheetObjPartByMore")
    @ResponseBody
    public AjaxResult delSheetObjPartByMore(Long sheetId,String deptId,Long sheetType,Long activityId){
    	CpSheetObjParticipant objPart = new CpSheetObjParticipant(sheetId, deptId, sheetType, activityId);
    	cpSheetObjParticipantService.delSheetObjPartByMore(objPart);
    	return success();
    }
    
    /**
     * 根据多条件查询测评对象
     * @param sheetId
     * @param sheetType
     * @param activityId
     * @return
     */
    @PostMapping( "/selectObjUserByActIdSidStype")
    @ResponseBody
    public List<String> selectObjUserByActIdSidStype(Long sheetId,String deptId,Long sheetType,Long activityId){
    	CpSheetObjParticipant objPart = new CpSheetObjParticipant(sheetId, deptId, sheetType, activityId);
    	List<String> objUserIdList = cpSheetObjParticipantService.selectObjUserByActIdSidStype(objPart);
    	return objUserIdList;
    }


    @PostMapping( "/selectrankbydeptidandactid")
    @ResponseBody
    public List<CpSheetObjParticipant> selectrankbydeptidandactid(Long sheetId,String deptId,Long activityId){
        List<CpSheetObjParticipant> objUserIdList = cpSheetObjParticipantService.selectrankbydeptidandactid(sheetId,deptId,activityId);
        return objUserIdList;
    }
}
