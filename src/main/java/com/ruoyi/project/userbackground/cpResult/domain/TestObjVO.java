package com.ruoyi.project.userbackground.cpResult.domain;

/**
 * 测评对象vo类
 */
public class TestObjVO {
    private String objDeptId;//被测部门id
    private String objDeptName;//被测部门名称
    private String objUserId;//被测人id
    private String objUserName;//被测人姓名

    public String getObjDeptId() {
        return objDeptId;
    }

    public void setObjDeptId(String objDeptId) {
        this.objDeptId = objDeptId;
    }

    public String getObjDeptName() {
        return objDeptName;
    }

    public void setObjDeptName(String objDeptName) {
        this.objDeptName = objDeptName;
    }

    public String getObjUserId() {
        return objUserId;
    }

    public void setObjUserId(String objUserId) {
        this.objUserId = objUserId;
    }

    public String getObjUserName() {
        return objUserName;
    }

    public void setObjUserName(String objUserName) {
        this.objUserName = objUserName;
    }
}
