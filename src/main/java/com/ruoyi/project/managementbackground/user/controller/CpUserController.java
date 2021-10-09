package com.ruoyi.project.managementbackground.user.controller;

import java.util.List;
import java.util.UUID;

import com.ruoyi.project.system.user.domain.User;
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
import com.ruoyi.project.managementbackground.user.domain.CpUser;
import com.ruoyi.project.managementbackground.user.service.ICpUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户Controller
 * 
 * @author ruoyi
 * @date 2021-09-05
 */
@Controller
@RequestMapping("/managementbackground/user")
public class CpUserController extends BaseController
{
    private String prefix = "managementbackground/user";

    @Autowired
    private ICpUserService cpUserService;

    @RequiresPermissions("managementbackground:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    /**
     * 查询用户列表
     */
    @RequiresPermissions("managementbackground:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CpUser cpUser)
    {
        startPage();
        List<CpUser> list = cpUserService.selectCpUserList(cpUser);
        return getDataTable(list);
    }

    @PostMapping("/listAll")
    @ResponseBody
    public TableDataInfo listAll(CpUser cpUser)
    {
        startPage();
        List<CpUser> list = cpUserService.selectCpUserListAll(cpUser);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @RequiresPermissions("managementbackground:user:export")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CpUser cpUser)
    {
        List<CpUser> list = cpUserService.selectCpUserList(cpUser);
        ExcelUtil<CpUser> util = new ExcelUtil<CpUser>(CpUser.class);
        return util.exportExcel(list, "用户数据");
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("managementbackground:user:add")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CpUser cpUser)
    {
        cpUser.setId(UUID.randomUUID().toString());
        return toAjax(cpUserService.insertCpUser(cpUser));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        CpUser cpUser = cpUserService.selectCpUserById(id);
        mmap.put("cpUser", cpUser);
        mmap.put("rid", cpUser.getRankId());
        mmap.put("did", cpUser.getDeptId());
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("managementbackground:user:edit")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CpUser cpUser)
    {
        return toAjax(cpUserService.updateCpUser(cpUser));
    }

    /**
     * 删除用户
     */
    @RequiresPermissions("managementbackground:user:remove")
    @Log(title = "用户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cpUserService.deleteCpUserByIds(ids));
    }

    //根据部门id查询所有的user
    @PostMapping("/listbydeptid")
    @ResponseBody
    public TableDataInfo listbydeptid(String deptid)
    {
        startPage();
        List<CpUser> list = cpUserService.selectCpUserBydeptid(deptid);
        return getDataTable(list);
    }

    @PostMapping("/listallbydeptid")
    @ResponseBody
    public TableDataInfo listallbydeptid(String deptid)
    {
        startPage();
        List<CpUser> list = cpUserService.selectCpUserAllBydeptid(deptid);
        return getDataTable(list);
    }

    @PostMapping("/listallbydeptidandrankid")
    @ResponseBody
    public TableDataInfo listallbydeptidandrankid(CpUser cpUser)
    {
        startPage();
        List<CpUser> list = cpUserService.listallbydeptidandrankid(cpUser);
        return getDataTable(list);
    }

    @RequiresPermissions("managementbackground:user:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<CpUser> util = new ExcelUtil<CpUser>(CpUser.class);
        List<CpUser> cpuserList = util.importExcel(file.getInputStream());
        String message = cpUserService.importcpUser(cpuserList, updateSupport);
        return AjaxResult.success(message);
    }

    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<CpUser> util = new ExcelUtil<CpUser>(CpUser.class);
        return util.importTemplateExcel("用户数据");
    }

}
