package com.ruoyi.project.userbackground.cpResult.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.managementbackground.activity.domain.CpActivity;
import com.ruoyi.project.managementbackground.activity.service.ICpActivityService;
import com.ruoyi.project.managementbackground.rank.domain.CpRank;
import com.ruoyi.project.managementbackground.rank.service.ICpRankService;
import com.ruoyi.project.userbackground.CpDepartmentCode.domain.CpDepartmentCode;
import com.ruoyi.project.userbackground.CpDepartmentCode.service.ICpDepartmentCodeService;
import com.ruoyi.project.userbackground.CpOnePersonOneYard.domain.CpOnePersonOneYard;
import com.ruoyi.project.userbackground.CpOnePersonOneYard.service.ICpOnePersonOneYardService;
import com.ruoyi.project.userbackground.CpRankCode.domain.CpRankCode;
import com.ruoyi.project.userbackground.CpRankCode.service.ICpRankCodeService;
import com.ruoyi.project.userbackground.CpUnlimitCode.domain.CpUnlimitCode;
import com.ruoyi.project.userbackground.CpUnlimitCode.service.ICpUnlimitCodeService;
import com.ruoyi.project.userbackground.cpResult.domain.ParamVO;
import com.ruoyi.project.userbackground.cpResult.domain.TestObjVO;
import com.ruoyi.project.userbackground.cpResult.service.ICpResultService;
import com.ruoyi.project.userbackground.result.domain.CpEvaluateResult;
import com.ruoyi.project.userbackground.result.service.ICpEvaluateResultService;
import com.ruoyi.project.userbackground.sheet.domain.CpSheet;
import com.ruoyi.project.userbackground.sheet.service.ICpSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测评信息结果Controller
 *
 * @author ying
 * @date 2021-09-11
 */
@Controller
@RequestMapping("/kp")
public class CpResultController extends BaseController {
    private String prefix = "userbackground/cpResult";

    @Autowired
    private ICpResultService cpResultService;

    @Autowired
    private ICpActivityService cpActivityService;

    @Autowired
    private ICpRankService cpRankService;

    @Autowired
    private ICpSheetService cpSheetService;

    @Autowired
    private ICpUnlimitCodeService cpUnlimitCodeService;

    @Autowired
    private ICpDepartmentCodeService cpDepartmentCodeService;

    @Autowired
    private ICpRankCodeService cpRankCodeService;

    @Autowired
    private ICpOnePersonOneYardService cpOnePersonOneYardService;

    private List<Long> sheetidList = new ArrayList<Long>();

    //全局变量－－－被测对象｛id：name｝
    Map<String,String> testObjMap = new HashMap<String,String>();

    //全局变量－－－选中被测的表格
    CpSheet cpSheetSelect = new CpSheet();

    /**
     * 扫码或网址进入考评页
     * @return
     */
    @GetMapping()
    public String kaopingPage(ModelMap mmap, HttpServletRequest request) {
        /*
         * 为了人员手动输入网址方便，改成短的
         *分别是cp_department_code，cp_one_person_one_yard，cp_rank_code，cp_unlimit_code是这4个表的id之一
         *p2对应以上表类型:'dept'，'one'，'rank'，'all'
         */
        String param1 = request.getParameter("p1");
        String param2 = request.getParameter("p2");

        ParamVO paramVO = new ParamVO();//
        if("all".equals(param2)){
            CpUnlimitCode unlimitCode = cpUnlimitCodeService.selectCpUnlimitCodeById(param1);
            paramVO.setActivityid(unlimitCode.getActityId());
        } else if("dept".equals(param2)){
            CpDepartmentCode deptCode = cpDepartmentCodeService.selectCpDepartmentCodeById(param1);
            paramVO.setActivityid(deptCode.getActityId());
            paramVO.setDeptid(deptCode.getDeptId());
        } else if("rank".equals(param2)){
            CpRankCode rankCode = cpRankCodeService.selectCpRankCodeById(param1);
            paramVO.setActivityid(rankCode.getActityId());
            paramVO.setDeptid(rankCode.getDeptId());
            paramVO.setRankid(rankCode.getRankId());
        } else if("one".equals(param2)){
            CpOnePersonOneYard onePersonCode = cpOnePersonOneYardService.selectCpOnePersonOneYardById(param1);
            paramVO.setActivityid(onePersonCode.getActityId());
        }
        //为方便判断制码类型
        paramVO.setCodeType(param2);

        //根据activityid查询活动是否开启职级
        CpActivity cpActivity = cpActivityService.selectCpActivityById(paramVO.getActivityid());
        if(cpActivity.getIsnotRank() == 1 && !"rank".equals(param2)) {//开启职级 并且不是职级制码
            //查询职级--查询所有，不需要条件
            List<CpRank> list = cpRankService.selectCpRankList(null);
            mmap.put("rankList",list);
            mmap.put("allParam",paramVO);
            return prefix + "/zhiji";
        } else {//不开启职级和职级制码都直接显示表格列表
            return selectSheetPage(paramVO, mmap);
        }
    }

    /**
     * 选择表格页
     * @param paramVO
     * @return
     */
    @GetMapping("/selectSheetPage")
    public String selectSheetPage(ParamVO paramVO, ModelMap mmap) {
        CpActivity cpActivity = cpActivityService.selectCpActivityById(paramVO.getActivityid());
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        if(cpActivity.getIsnotRank() == 1 ) {//开启职级
            /**
             * sql里判断二维码类型，all(所有)和one(一人一码)查询选中职级(pageSelectRankId)权限内的所有列表
             * dept(部门制码)查询部门(deptid)和选中的职级(pageSelectRankId)同时满足的表格列表
             * rank(职级制码）查询职级(rankid)权限内的表格
             */
            list = cpResultService.selectSheetByCyzRank(paramVO);
        } else {
            if(!"rank".equals(paramVO.getCodeType())){//没开启职级，职级制码无用
                list = cpResultService.selectSheetByCyzNotRank(paramVO);
            }
        }

        mmap.put("cpActivity",cpActivity);
        mmap.put("sheetList",list);
        mmap.put("allParam",paramVO);
        return prefix + "/start";
    }

    @GetMapping("/selectSheetPage1")
    public String selectSheetPage1(ParamVO paramVO, ModelMap mmap) {
        if(paramVO.getSheetid() != null){
            //sheetidList +=  paramVO.getSheetid()+",";
            if(!sheetidList.contains(paramVO.getSheetid())) {
                sheetidList.add(paramVO.getSheetid());
            }

        }
        System.out.println(sheetidList);
        CpActivity cpActivity = cpActivityService.selectCpActivityById(paramVO.getActivityid());
        //List<Map<String,Object>> list = cpSheetService.selectSheetListByActivityId1(paramVO.getActivityid(),sheetidList);
        //List<Map<String,Object>> list = cpSheetService.selectSheetListByActivityId(paramVO.getActivityid());

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        if(cpActivity.getIsnotRank() == 1 ) {//开启职级
            /**
             * sql里判断二维码类型，all(所有)和one(一人一码)查询选中职级(pageSelectRankId)权限内的所有列表
             * dept(部门制码)查询部门(deptid)和选中的职级(pageSelectRankId)同时满足的表格列表
             * rank(职级制码）查询职级(rankid)权限内的表格
             */
            list = cpResultService.selectSheetByCyzRank(paramVO);
        } else {
            if(!"rank".equals(paramVO.getCodeType())){//没开启职级，职级制码无用
                list = cpResultService.selectSheetByCyzNotRank(paramVO);
            }
        }

        mmap.put("cpActivity",cpActivity);
        mmap.put("sheetList",list);
        mmap.put("allParam",paramVO);
        mmap.put("bjSheetId",sheetidList);
        //已测评的表格id数量小于权限内表格的数量，进行下一步，否则，结束提交
        if(sheetidList.size() < list.size()){//list.size() > 0
            return prefix + "/start";
        }else{
            sheetidList = new ArrayList<Long>();
            return prefix + "/cp-ok";
        }
    }

    /**
     * 跳转检测首页
     * @param paramVO
     * @param mmap
     * @return
     */
    @GetMapping("/toCpIndex")
    public String toCpIndex(ParamVO paramVO, ModelMap mmap){
        cpSheetSelect = cpSheetService.selectCpSheetById(paramVO.getSheetid());
        mmap.put("cpSheet",cpSheetSelect);
        mmap.put("allParam",paramVO);
        return prefix + "/cpIndex";
    }

    /**
     * 跳转测评对象信息页
     * @param paramVO
     * @param mmap
     * @return
     */
    @GetMapping("/toItemPage")
    public String toItemPage(ParamVO paramVO, ModelMap mmap){
        //获取表格背景主题等信息
        mmap.put("cpSheet",cpSheetSelect);

        //获取被测对象信息
        testObjMap = cpResultService.selectTestObjByObjType(paramVO);
        mmap.put("testObjMap",testObjMap);
        mmap.put("allParam",paramVO);//页面提交时用到活动id，职级id等数据

        cpSheetSelect = cpSheetService.selectCpSheetById(paramVO.getSheetid());
        mmap.put("cpSheet",cpSheetSelect);
        return prefix + "/item";
    }

    /**
     * 提交成功，跳转成功页
     * @param objId
     * @param mmap
     * @return
     */
    @GetMapping("/finishSubmit")
    public String finishSubmit(String objId, ModelMap mmap){
        //获取表格背景主题等信息
        mmap.put("cpSheet",cpSheetSelect);
        return prefix + "/cp-ok";
    }

    /**
     * 查询表格是否设置被测对象，没有被测对象，不能进入开始测评
     * @param paramVO
     * @return
     */
    @PostMapping("/selectTestObj")
    @ResponseBody
    public String selectTestObj(ParamVO paramVO){
        Map<String,String> map = cpResultService.selectTestObjByObjType(paramVO);
        if(map != null && map.size() > 0) {
            return "0";
        }else {
            return "1";//没有测评对象，阻止页面跳转
        }
    }
}
