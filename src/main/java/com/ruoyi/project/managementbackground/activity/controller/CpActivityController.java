package com.ruoyi.project.managementbackground.activity.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.ruoyi.project.managementbackground.activity.utils.QRCodeUtils;
import com.ruoyi.project.managementbackground.activity.utils.base64ToImage;
import com.ruoyi.project.userbackground.CpDepartmentCode.domain.CpDepartmentCode;
import com.ruoyi.project.userbackground.CpDepartmentCode.service.ICpDepartmentCodeService;
import com.ruoyi.project.userbackground.CpOnePersonOneYard.domain.CpOnePersonOneYard;
import com.ruoyi.project.userbackground.CpOnePersonOneYard.service.ICpOnePersonOneYardService;
import com.ruoyi.project.userbackground.CpRankCode.domain.CpRankCode;
import com.ruoyi.project.userbackground.CpRankCode.service.ICpRankCodeService;
import com.ruoyi.project.userbackground.CpRankCodeSon.domain.CpRankCodeSon;
import com.ruoyi.project.userbackground.CpRankCodeSon.service.ICpRankCodeSonService;
import com.ruoyi.project.userbackground.CpUnlimitCode.domain.CpUnlimitCode;
import com.ruoyi.project.userbackground.CpUnlimitCode.service.ICpUnlimitCodeService;
import com.ruoyi.project.userbackground.participant.domain.CpSheetObjParticipant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.managementbackground.activity.domain.CpActivity;
import com.ruoyi.project.managementbackground.activity.service.ICpActivityService;
import com.ruoyi.project.userbackground.sheet.domain.CpSheet;
import com.ruoyi.project.userbackground.sheet.service.ICpSheetService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * ????????????Controller
 *
 * @author ruoyi
 * @date 2021-09-05
 */
@Controller
@RequestMapping("/managementbackground/activity")
public class CpActivityController extends BaseController
{
    private String prefix = "managementbackground/activity";

    @Autowired
    private ICpActivityService cpActivityService;
    @Autowired
	private ICpSheetService cpSheetService;
    @Autowired
    private ICpDepartmentCodeService cpDepartmentCodeService;
    @Autowired
    private ICpRankCodeService cpRankCodeService;
    @Autowired
    private ICpUnlimitCodeService cpUnlimitCodeService;
    @Autowired
    private ICpRankCodeSonService cpRankCodeSonService;
    @Autowired
    private ICpOnePersonOneYardService cpOnePersonOneYardService;
    @Value("${ruoyi.rupath}")
    private String rupath;
    @Value("${ruoyi.profile}")
    private String chupath;


    @RequiresPermissions("managementbackground:activity:view")
    @GetMapping()
    public String activity()
    {
        return prefix + "/activity";
    }

    /**
     * ????????????????????????
     */
    @RequiresPermissions("managementbackground:activity:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CpActivity cpActivity)
    {
        startPage();
        List<CpActivity> list = cpActivityService.selectCpActivityList(cpActivity);
        return getDataTable(list);
    }

    /**
     * ????????????????????????
     */
    @RequiresPermissions("managementbackground:activity:export")
    @Log(title = "????????????", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CpActivity cpActivity)
    {
        List<CpActivity> list = cpActivityService.selectCpActivityList(cpActivity);
        ExcelUtil<CpActivity> util = new ExcelUtil<CpActivity>(CpActivity.class);
        return util.exportExcel(list, "??????????????????");
    }

    /**
     * ????????????activityId??????????????????sheet
     * @param activityId
     * @return
     */
    @PostMapping("/selectSheetByActivityId")
	@ResponseBody
	public TableDataInfo selectSheetByActivityId(Long activityId) {
		startPage();
		List<Map<String,Object>> list = cpSheetService.selectSheetListByActivityId(activityId);
		return getDataTable(list);
	}

    /**
     * ??????????????????
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        return prefix + "/add";
    }
//??????????????????
    @RequiresPermissions("managementbackground:activity:look")
    @GetMapping("/look")
    public String look(ModelMap mmap,Long id)
    {
        CpActivity cpActivity = cpActivityService.selectCpActivityById(id);
        mmap.put("cpActivity", cpActivity);
        return prefix + "/look";
    }

    /**
     * ????????????????????????
     */
    @RequiresPermissions("managementbackground:activity:add")
    @Log(title = "????????????", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CpActivity cpActivity)
    {
        return toAjax(cpActivityService.insertCpActivity(cpActivity));
    }

    /**
     * ??????????????????
     */
//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable("id") Long id, ModelMap mmap)
//    {
//        CpActivity cpActivity = cpActivityService.selectCpActivityById(id);
//        mmap.put("cpActivity", cpActivity);
//        return prefix + "/edit";
//    }
    @GetMapping("/edit")
    public String edit(Long id, ModelMap mmap)
    {
        CpActivity cpActivity = cpActivityService.selectCpActivityById(id);
        mmap.put("cpActivity", cpActivity);
        return prefix + "/edit";
    }
//??????
    @GetMapping("/manage")
    public String manage(Long id, ModelMap mmap)
    {
        CpActivity cpActivity = cpActivityService.selectCpActivityById(id);
        mmap.put("cpActivity", cpActivity);
        return prefix + "/manage";
    }
//??????????????????
    @GetMapping("/cpobjgr")
    public String cpobj(Long sheetid,String objType,Long activityId, ModelMap mmap)
    {
        //CpActivity cpActivity = cpActivityService.selectCpActivityById(id);
        //mmap.put("cpActivity", cpActivity);
    	mmap.put("activityId", activityId);
        mmap.put("sheetid", sheetid);
        mmap.put("objType", objType);
        return prefix + "/cpobj";
    }

    @GetMapping("/cpobjdept")
    public String cpobjdept(Long sheetid,String objType,Long activityId, ModelMap mmap)
    {
        //CpActivity cpActivity = cpActivityService.selectCpActivityById(id);
        //mmap.put("cpActivity", cpActivity);
        mmap.put("activityId", activityId);
        mmap.put("sheetid", sheetid);
        mmap.put("objType", objType);
        return prefix + "/cpobjdept";
    }
//?????????
    @GetMapping("/cyz")
    public String cyz(Long sheetid,String objType,Long activityId, ModelMap mmap)
    {
       // CpActivity cpActivity = cpActivityService.selectCpActivityById(id);
       // mmap.put("cpActivity", cpActivity);
    	mmap.put("activityId", activityId);
    	mmap.put("sheetid", sheetid);
        mmap.put("objType", objType);
        return prefix + "/cyz";
    }
//????????????
    @GetMapping("/jf")
    public String jf(Long sheetid,String objType,Long activityId, ModelMap mmap)
    {
        // CpActivity cpActivity = cpActivityService.selectCpActivityById(id);
        // mmap.put("cpActivity", cpActivity);
    	mmap.put("activityId", activityId);
    	mmap.put("sheetid", sheetid);
        mmap.put("objType", objType);
        return prefix + "/jf";
    }

    //????????????--??????????????????
    @GetMapping("/jfDept")
    public String jfDept(Long sheetid,String objType,Long activityId, ModelMap mmap) {
        mmap.put("activityId", activityId);
        mmap.put("sheetid", sheetid);
        mmap.put("objType", objType);
        return prefix + "/jfDept";
    }

    //????????????--???????????????
    @GetMapping("/jfAll")
    public String jfAll(Long sheetid,String objType,Long activityId, ModelMap mmap) {
        mmap.put("activityId", activityId);
        mmap.put("sheetid", sheetid);
        mmap.put("objType", objType);
        return prefix + "/jfAll";
    }
//??????????????????
    @GetMapping("/gencodebydept")
    public String gencodebydept(Long activityId, ModelMap mmap)
    {
        mmap.put("activityId", activityId);
        return prefix + "/gencodebydept";
    }
//??????????????????
    @GetMapping("/gencodebyrank")
    public String gencodebyrank(Long activityId, ModelMap mmap)
    {
        mmap.put("activityId", activityId);
        return prefix + "/gencodebyrank";
    }
//??????????????????
    @GetMapping("/gencodebyperson")
    public String gencodebyperson(Long activityId, ModelMap mmap)
    {
        mmap.put("activityId", activityId);
        List<CpOnePersonOneYard> c = cpOnePersonOneYardService.selectCpOnePersonOneYardByActId(activityId);
        if(c.size() > 0){
            mmap.put("codenum", c.get(0).getCodeNum());
        }
        return prefix + "/gencodebyperson";
    }
//    ???????????????
    @GetMapping("/downcode")
    public String downcode(String id,String codetype,String activityId,ModelMap mmap)
    {
        mmap.put("id", id);
        mmap.put("codetype", codetype);
        mmap.put("activityId", activityId);
        return prefix + "/downcode";
    }
    /**
     * ????????????????????????
     */
    @RequiresPermissions("managementbackground:activity:edit")
    @Log(title = "????????????", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CpActivity cpActivity)
    {
        return toAjax(cpActivityService.updateCpActivity(cpActivity));
    }

    /**
     * ??????????????????
     */
    @RequiresPermissions("managementbackground:activity:remove")
    @Log(title = "????????????", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cpActivityService.deleteCpActivityByIds(ids));
    }
//?????????????????????????????????
    @PostMapping("/selectbyactity")
    @ResponseBody
    public Map<String,String> selectbyactity(String activityId,ModelMap mmap) throws IOException, WriterException {
        String path = "";
        String result = "";
        CpUnlimitCode cc = cpUnlimitCodeService.selectCpUnlimitCodeByactityid(activityId);
        if(cc == null){
            for(int i=0;i<8;i++){
                int intVal=(int)(Math.random()*26+97);
                result=result+(char)intVal;
            }
            path = getcode("4",0,"","",activityId,result).get(0);
        }else{
            path = cc.getQrCodePath();
        }
        Map<String,String> map = new HashMap<>();
        map.put("path",path);
        map.put("p1",result);
        return map;
    }
//???????????????????????????
    @PostMapping( "/getcode")
    @ResponseBody
    public List<String> getcode(String codetype,Integer codenum,String rankid,String deptid,String actityid,String result) throws IOException, WriterException {
        List<String> list = new ArrayList<>();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 0);
        int onColor = 0xFF000000;     //?????????
        int offColor = 0xBFB0A8;    //?????????
        MatrixToImageConfig config = new MatrixToImageConfig(onColor, offColor);

        //codetype:1??????2??????3????????????4?????????
        String content = "";
        if(codetype.equals("1")){
            content = rupath + "?p1="+result+"&p2=dept";
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 150, 150, hints);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", baos, config);
            //???????????????????????????????????????????????????????????????
            list.add(0,base64ToImage.test(codetype,Base64.getEncoder().encodeToString(baos.toByteArray())));
        }else if(codetype.equals("2")){
            content = rupath +"?p1="+result+"&p2=rank";
            for(int i = 0;i<codenum;i++){
                BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 150, 150, hints);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                MatrixToImageWriter.writeToStream(bitMatrix, "png", baos, config);
                //???????????????????????????????????????????????????????????????
                list.add(i,base64ToImage.test(codetype,Base64.getEncoder().encodeToString(baos.toByteArray())));
            }
        }else if(codetype.equals("3") || codetype.equals("4")){
            content = rupath +"?p1="+result+"&p2=one";
            if(codetype.equals("3")){
//                for(int i = 0;i<codenum;i++){
                    BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 150, 150, hints);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    MatrixToImageWriter.writeToStream(bitMatrix, "png", baos, config);
                    //???????????????????????????????????????????????????????????????
                    list.add(0,base64ToImage.test(codetype,Base64.getEncoder().encodeToString(baos.toByteArray())));
//                }
            }else if(codetype.equals("4")){
                content = rupath +"?p1="+result+"&p2=all";
                BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 150, 150, hints);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                MatrixToImageWriter.writeToStream(bitMatrix, "png", baos, config);
                //???????????????????????????????????????????????????????????????
                list.add(0,base64ToImage.test(codetype,Base64.getEncoder().encodeToString(baos.toByteArray())));
            }
        }
        return list;
    }
//??????????????????
    @Transactional
    @PostMapping("/batchaddcodebydept")
    @ResponseBody
    public String batchaddcodebydept(String deptids,String deptNames,Long activityId)
    {
        //??????????????????????????????
        cpDepartmentCodeService.deleteCpDepartmentCodeByActId(activityId);
        List<String> lis = Arrays.asList(deptids.split(","));
        List<String> lis1 = Arrays.asList(deptNames.split(","));
        if(lis.size() > 0){
            for(int i = 0;i<lis.size();i++){
                String result = "";
                for(int j=0;j<8;j++){
                    int intVal=(int)(Math.random()*26+97);
                    result=result+(char)intVal;
                }
                CpDepartmentCode c = new CpDepartmentCode();
                c.setId(result);
                c.setDeptId(lis.get(i));
                c.setDeptName(lis1.get(i));
                c.setScanningTimes(0L);
                c.setActityId(activityId);
                //????????????????????????????????????id???????????????????????????????????????
//                CpDepartmentCode cc = cpDepartmentCodeService.selectCpDepartmentCodeBydeptId(lis.get(i),activityId);
//                if(cc == null){
//                    cpDepartmentCodeService.insertCpDepartmentCode(c);
//                }
                cpDepartmentCodeService.insertCpDepartmentCode(c);
            }
        }
        return "success";
    }
//??????????????????
    @PostMapping("/batchaddcodebyrank")
    @ResponseBody
    public String batchaddcodebyrank(String ranknames,String rankids, String deptid,String deptname,Long activityId)
    {
        //??????????????????????????????
        cpRankCodeService.deleteCpRankCodeByActId(activityId,deptid);
        List<String> lis = Arrays.asList(ranknames.split(","));
        List<String> lis1 = Arrays.asList(rankids.split(","));
        if(lis.size() > 0){
            for(int i = 0;i<lis.size();i++){
                String result = "";
                for(int j=0;j<8;j++){
                    int intVal=(int)(Math.random()*26+97);
                    result=result+(char)intVal;
                }
                CpRankCode c = new CpRankCode();
                c.setId(result);
                c.setDeptId(deptid);
                c.setDeptName(deptname);
                c.setGroupName(lis.get(i));
                c.setRankId(lis1.get(i));
                c.setActityId(activityId);
                //????????????????????????????????????id???????????????????????????????????????
//                CpRankCode cc = cpRankCodeService.selectCpRankCodeByAllId(deptid,lis1.get(i),activityId);
//                if(cc == null){
//                    cpRankCodeService.insertCpRankCode(c);
//                }
                cpRankCodeService.insertCpRankCode(c);
            }
        }
        return "success";
    }
//?????????????????????
    @PostMapping("/setcode")
    @ResponseBody
    public String setcode(String codetype,Integer codenum,String rankid,String deptid,String actityid,String id) throws IOException, WriterException {
        List list = null;
        if(codetype.equals("1")){
            list = getcode(codetype,codenum,rankid,deptid,actityid,id);
            CpDepartmentCode c = cpDepartmentCodeService.selectCpDepartmentCodeById(id);
            c.setQrCodePath(list.get(0).toString());
            cpDepartmentCodeService.updateCpDepartmentCode(c);
        }else if(codetype.equals("3")){
            /*list = getcode(codetype,codenum,rankid,deptid,actityid,result);
            for(int i = 0;i<list.size();i++){
                CpOnePersonOneYard c = new CpOnePersonOneYard();
                c.setId(result);
                c.setQrCodePath(list.get(i).toString());
                c.setCodeNum(codenum.longValue());
                c.setActityId(Long.valueOf(actityid));
                cpOnePersonOneYardService.insertCpOnePersonOneYard(c);
            }*/
            for(int i=0;i<codenum;i++){
                String result = "";
                for(int j=0;j<8;j++){
                    int intVal=(int)(Math.random()*26+97);
                    result=result+(char)intVal;
                }
                list = getcode(codetype,codenum,rankid,deptid,actityid,result);
                CpOnePersonOneYard c = new CpOnePersonOneYard();
                c.setId(result);
                c.setQrCodePath(list.get(0).toString());
                c.setCodeNum(codenum.longValue());
                c.setActityId(Long.valueOf(actityid));
                cpOnePersonOneYardService.insertCpOnePersonOneYard(c);
            }
        }else if(codetype.equals("2")){
            list = getcode(codetype,codenum,rankid,deptid,actityid,id);
            //?????????????????????????????????
            CpRankCode rankCode = cpRankCodeService.selectCpRankCodeById(id);
            rankCode.setCodeNum(codenum.longValue());
            cpRankCodeService.updateCpRankCode(rankCode);
            for(int i = 0;i<list.size();i++){
                CpRankCodeSon c = new CpRankCodeSon();
                c.setId(UUID.randomUUID().toString());
                c.setRankCodeId(id);
                c.setRankId(rankid);
                c.setQrCodePath(list.get(i).toString());
                cpRankCodeSonService.insertCpRankCodeSon(c);
            }
        }

        return "success";
    }


    @PostMapping("/shibiecode")
    @ResponseBody
    public String shibiecode(String imgpath)
    {
        String aa = imgpath.replaceAll("/profile",chupath);
        return QRCodeUtils.deEncodeByPath(aa);

    }

    /**
     * ????????????activityId??????????????????sheet--????????????????????????????????????
     * @param activityId
     * @return
     */
    @PostMapping("/selectSheetListByActivityId")
    @ResponseBody
    public List<Map<String,Object>> selectSheetListByActivityId(Long activityId) {
        List<Map<String,Object>> list = cpSheetService.selectSheetListByActivityId(activityId);
        return list;
    }

    /**
     * ??????????????????
     * @param activityId
     * @return
     */
    @PostMapping("/selectCodeNumState")
    @ResponseBody
    public Map<String,Long> selectCodeNumState(Long activityId) {
        Map<String,Long> map = new HashMap<String,Long>();
        CpActivity cpActivity = cpActivityService.selectCpActivityById(activityId);
        Long allCodeNum = 0L;//?????????
        //??????????????????
        Map<String,Object> deptMap = cpDepartmentCodeService.selectCountCodeNumByActId(activityId);

        if(deptMap.get("codeNum")!=null){
            allCodeNum += Long.valueOf(deptMap.get("codeNum").toString());
        }
        //????????????
        Map<String,Object> oneMap = cpOnePersonOneYardService.selectCountCodeNumByActId(activityId);

        if(oneMap.get("codeNum")!=null) {
            allCodeNum += Long.valueOf(oneMap.get("codeNum").toString());
        }
        //??????????????????????????????????????????
        if(cpActivity.getIsnotRank() == 1){
            Map<String,Object> rankCodeNum = cpRankCodeService.selectSumCodeNumByActId(activityId);

            if(rankCodeNum.get("codeNum") != null) {
                allCodeNum += Long.valueOf(rankCodeNum.get("codeNum").toString());
            }

        }
        //????????????
        Long alreadyVote = 0L;
        CpUnlimitCode unlimitCode = cpUnlimitCodeService.selectCpUnlimitCodeByactityid(activityId.toString());
        if(unlimitCode.getScanningTimes() != null){
            alreadyVote = unlimitCode.getScanningTimes();
        }
        //????????????
        Long oddCodeNum = allCodeNum - alreadyVote;
        map.put("allCodeNum",allCodeNum);//????????????
        map.put("alreadyVote",alreadyVote);//????????????
        map.put("oddCodeNum",oddCodeNum);//????????????

        return map;
    }
}
