package com.ruoyi.project.userbackground.cpResult.domain;

/**
 * 参数vo类
 */
public class ParamVO {
    private Long activityid;//活动id
    private Long sheetid;//表格id
    private String deptid;//部门id
    private String rankid;//职级id
    private String pageSelectRankId;//页面选择的职级id----开始测评前，在页面选的职级id（暂时不知道用途，目前这个值存到测评结果里了）
    private String objtype;//对象类型（0:个人；1:单位／部门）
    //表格设置测评对象和参与者类型（0:个人/测评对象；1：个人/参与者；2：部门/测评对象；3部门/参与者）
    private Long sheettype;

    private String codeType;//制码类型－－all:所有；dept:部门制码；rank:职级制码；one:一人一码

    public Long getActivityid() {
        return activityid;
    }

    public void setActivityid(Long activityid) {
        this.activityid = activityid;
    }

    public Long getSheetid() {
        return sheetid;
    }

    public void setSheetid(Long sheetid) {
        this.sheetid = sheetid;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getRankid() {
        return rankid;
    }

    public void setRankid(String rankid) {
        this.rankid = rankid;
    }

    public String getObjtype() {
        return objtype;
    }

    public void setObjtype(String objtype) {
        this.objtype = objtype;
    }

    public Long getSheettype() {
        return sheettype;
    }

    public void setSheettype(Long sheettype) {
        this.sheettype = sheettype;
    }

    public String getPageSelectRankId() {
        return pageSelectRankId;
    }

    public void setPageSelectRankId(String pageSelectRankId) {
        this.pageSelectRankId = pageSelectRankId;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }
}
