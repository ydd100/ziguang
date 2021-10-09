package com.ruoyi.project.userbackground.cpResult.mapper;

import com.ruoyi.project.userbackground.cpResult.domain.ParamVO;
import com.ruoyi.project.userbackground.cpResult.domain.TestObjVO;

import java.util.List;
import java.util.Map;

/**
 * 测评信息Mapper接口
 *
 * @author ying
 * @date 2021-09-12
 */
public interface CpResultMapper {
    /**
     * 查询部门的被测对象
     * @param paramVO
     * @return
     */
    public List<TestObjVO> selectTestDeptObjByParam(ParamVO paramVO);

    /**
     * 查询个人的被测对象
     * @param paramVO
     * @return
     */
    public List<TestObjVO> selectTestUserObjByParam(ParamVO paramVO);

    /**
     * 根据职级查询职级可参与的表格列表
     * @param paramVO
     * @return
     */
    public List<Map<String,Object>> selectSheetByCyzRank(ParamVO paramVO);

    /**
     * 不开启职级查询表格列表
     * @param paramVO
     * @return
     */
    public List<Map<String,Object>> selectSheetByCyzNotRank(ParamVO paramVO);
}
