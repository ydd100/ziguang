package com.ruoyi.project.userbackground.cpResult.service.impl;

import com.ruoyi.project.userbackground.cpResult.domain.ParamVO;
import com.ruoyi.project.userbackground.cpResult.domain.TestObjVO;
import com.ruoyi.project.userbackground.cpResult.mapper.CpResultMapper;
import com.ruoyi.project.userbackground.cpResult.service.ICpResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CpResultServiceImpl implements ICpResultService {
    @Autowired
    private CpResultMapper cpResultMapper;

    /**
     * 查询部门的被测对象
     * @param paramVO
     * @return
     */
    @Override
    public List<TestObjVO> selectTestDeptObjByParam(ParamVO paramVO) {
        return cpResultMapper.selectTestDeptObjByParam(paramVO);
    }

    /**
     * 查询个人的被测对象
     * @param paramVO
     * @return
     */
    @Override
    public List<TestObjVO> selectTestUserObjByParam(ParamVO paramVO) {
        return cpResultMapper.selectTestUserObjByParam(paramVO);
    }

    /**
     * 根据表格类型查询被测对象
     * @param paramVO
     * @return
     */
    public Map<String,String> selectTestObjByObjType(ParamVO paramVO) {
        Map<String,String> map = new HashMap<String,String>();
        String objType = paramVO.getObjtype();//对象类型（0:个人；1:单位／部门）
        //表格设置测评对象类型（0:个人/测评对象；2：部门/测评对象；）
        if("0".equals(objType)){
            paramVO.setSheettype(0L);
            List<TestObjVO> testObjVOList = cpResultMapper.selectTestUserObjByParam(paramVO);
            for (TestObjVO testObj : testObjVOList){
                map.put(testObj.getObjUserId(),testObj.getObjUserName());
            }
        } else {
            paramVO.setSheettype(2L);
            List<TestObjVO> testObjVOList = cpResultMapper.selectTestDeptObjByParam(paramVO);
            for (TestObjVO testObj : testObjVOList){
                map.put(testObj.getObjDeptId(),testObj.getObjDeptName());
            }
        }
        return map;
    }

    /**
     * 根据职级查询职级可参与的表格列表
     * @param paramVO
     * @return
     */
    @Override
    public List<Map<String, Object>> selectSheetByCyzRank(ParamVO paramVO) {
        return cpResultMapper.selectSheetByCyzRank(paramVO);
    }

    /**
     * 不开启职级查询表格列表
     * @param paramVO
     * @return
     */
    @Override
    public List<Map<String, Object>> selectSheetByCyzNotRank(ParamVO paramVO) {
        return cpResultMapper.selectSheetByCyzNotRank(paramVO);
    }
}
