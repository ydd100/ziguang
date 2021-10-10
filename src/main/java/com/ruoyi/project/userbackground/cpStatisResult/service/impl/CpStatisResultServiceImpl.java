package com.ruoyi.project.userbackground.cpStatisResult.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.userbackground.cpStatisResult.mapper.CpStatisResultMapper;
import com.ruoyi.project.userbackground.cpStatisResult.domain.CpStatisResult;
import com.ruoyi.project.userbackground.cpStatisResult.service.ICpStatisResultService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 测评信息结果Service业务层处理
 * 
 * @author ying
 * @date 2021-09-12
 */
@Service
public class CpStatisResultServiceImpl implements ICpStatisResultService 
{
    @Autowired
    private CpStatisResultMapper cpStatisResultMapper;

    /**
     * 查询测评信息结果
     * 
     * @param id 测评信息结果主键
     * @return 测评信息结果
     */
    @Override
    public CpStatisResult selectCpStatisResultById(String id)
    {
        return cpStatisResultMapper.selectCpStatisResultById(id);
    }

    /**
     * 查询测评信息结果列表
     * 
     * @param cpStatisResult 测评信息结果
     * @return 测评信息结果
     */
    @Override
    public List<CpStatisResult> selectCpStatisResultList(CpStatisResult cpStatisResult)
    {
        return cpStatisResultMapper.selectCpStatisResultList(cpStatisResult);
    }

    /**
     * 新增测评信息结果
     * 
     * @param cpStatisResult 测评信息结果
     * @return 结果
     */
    @Override
    public int insertCpStatisResult(CpStatisResult cpStatisResult)
    {
        return cpStatisResultMapper.insertCpStatisResult(cpStatisResult);
    }

    /**
     * 修改测评信息结果
     * 
     * @param cpStatisResult 测评信息结果
     * @return 结果
     */
    @Override
    public int updateCpStatisResult(CpStatisResult cpStatisResult)
    {
        return cpStatisResultMapper.updateCpStatisResult(cpStatisResult);
    }

    /**
     * 批量删除测评信息结果
     * 
     * @param ids 需要删除的测评信息结果主键
     * @return 结果
     */
    @Override
    public int deleteCpStatisResultByIds(String ids)
    {
        return cpStatisResultMapper.deleteCpStatisResultByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除测评信息结果信息
     * 
     * @param id 测评信息结果主键
     * @return 结果
     */
    @Override
    public int deleteCpStatisResultById(String id)
    {
        return cpStatisResultMapper.deleteCpStatisResultById(id);
    }

    /**
     * 批量保存测评信息结果
     * @param cpInfoList
     */
    public void batchInsertCpStatisResult(List<CpStatisResult> cpInfoList) {
        cpStatisResultMapper.batchInsertCpStatisResult(cpInfoList);
    }

    /**
     * 获取部门测评结果(没写明白，先按下面分步写吧)
     * @param activityId
     * @param sheetId
     * @param sheetType
     * @return
     */
    @Override
    public List<Map<String, Object>> selectAvgByActIdShIdShType(Long activityId, Long sheetId, Long sheetType) {
        List<Map<String, Object>> allList = cpStatisResultMapper.selectAvgByActIdShIdShType(activityId,sheetId,sheetType);
        //处理数据

//        for (Map<String, Object> map : allList) {
//
//        }

        return null;
    }

    /**
     * 获取部门表格的统计数据
     * @param activityId
     * @param sheetId
     * @return
     */
    @Override
    public Map<String, Object> selectDeptStatisResult(Long activityId, Long sheetId,Long isnotRank) {
        Map<String,Object> resultMap = new HashMap<String,Object>();//最终返回结果
        DecimalFormat df = new DecimalFormat("#.0000");
        //参数map
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("activityId",activityId);
        paramMap.put("sheetId",sheetId);
        //1.查询表格项
        List<Map<String, Object>> colList = cpStatisResultMapper.selectColListByShId(paramMap);
        resultMap.put("colList",colList);//项名称

        //判断是否开启职级(1开启0关闭)
        if(isnotRank == 1) {
            //2.(活动开启职级)查询测评过的职级
            List<Map<String, Object>> rankList = cpStatisResultMapper.selectRankListByActIdShId(paramMap);
            //3.查询测评对象是部门的均值和加权分数(按部门排序，是为了将相同部门放到一起，便于处理数据)
            List<Map<String, Object>> deptResultList = cpStatisResultMapper.selectDeptResultByActIdShId(paramMap);
            //拼接后的数据map
            List<Map<String,Object>> valueMapList = new ArrayList<Map<String,Object>>();
            Map<String,Object> valueMap = new HashMap<String,Object>();
            List<Object> deptList = new ArrayList<Object>();//加入部门id，为了下方循环拼接的数据格式
            Double zj = 0D;
            Double jqzj = 0D;//加权总计
            for (int i = 0;i < deptResultList.size();i++) {
                Map<String, Object> deptResultMap = deptResultList.get(i);

                if (!deptList.contains(deptResultMap.get("objDeptId"))) {
                    deptList.add(deptResultMap.get("objDeptId"));
                    if (valueMap.size() > 0) {//第一次添加时，valueMap是空集合
                        valueMap.put("zj",df.format(zj));
                        valueMap.put("jqzj",df.format(jqzj));
                        valueMapList.add(valueMap);//循环到新的部门了，将上一个部门加添加list里
                        zj = 0D;
                        jqzj = 0D;
                    }

                    valueMap = new HashMap<String,Object>();//新定义对象
                    //为了判断有相同的部门就不定义新的valueMap,直接拼接平均值和加权分
                    valueMap.put("objDeptId",deptResultMap.get("objDeptId"));
                    valueMap.put("objDeptName",deptResultMap.get("objDeptName"));
                }

                //平均值
                String avgKey = deptResultMap.get("columnId") + "_" + deptResultMap.get("rankId") + "_avg";
                valueMap.put(avgKey,deptResultMap.get("avgValue"));
                zj += Double.parseDouble(deptResultMap.get("avgValue").toString());

                //加权分
                String jqKey = deptResultMap.get("columnId") + "_" + deptResultMap.get("rankId") + "_jq";
                valueMap.put(jqKey,deptResultMap.get("jqValue"));
                jqzj += Double.parseDouble(deptResultMap.get("jqValue").toString());

                //判断是不是最后一条数据，如果是最后一条，将valuefMap保存到list里
                if (i == deptResultList.size()-1) {
                    valueMap.put("zj",df.format(zj));
                    valueMap.put("jqzj",df.format(jqzj));
                    valueMapList.add(valueMap);
                    zj = 0D;
                    jqzj = 0D;
                }

            }
            resultMap.put("rankList",rankList);
            resultMap.put("valueMapList",valueMapList);
        } else {
            //关闭职级
            //3.(不开职级)查询测评对象是部门的均值和加权分数(按部门排序，是为了将相同部门放到一起，便于处理数据)
            List<Map<String, Object>> deptResultList = cpStatisResultMapper.selectDeptResultByActIdShIdNoRank(paramMap);
            //拼接后的数据map
            List<Map<String,Object>> valueMapList = new ArrayList<Map<String,Object>>();
            Map<String,Object> valueMap = new HashMap<String,Object>();
            List<Object> deptList = new ArrayList<Object>();//加入部门id，为了下方循环拼接的数据格式
            Double zj = 0D;
            Double jqzj = 0D;//加权总计
            for (int i = 0;i < deptResultList.size();i++) {
                Map<String, Object> deptResultMap = deptResultList.get(i);

                if (!deptList.contains(deptResultMap.get("objDeptId"))) {
                    deptList.add(deptResultMap.get("objDeptId"));
                    if (valueMap.size() > 0) {//第一次添加时，valueMap是空集合
                        valueMap.put("zj",df.format(zj));
                        valueMap.put("jqzj",df.format(jqzj));
                        valueMapList.add(valueMap);//循环到新的部门了，将上一个部门加添加list里
                        zj = 0D;
                        jqzj = 0D;
                    }

                    valueMap = new HashMap<String,Object>();//新定义对象
                    //为了判断有相同的部门就不定义新的valueMap,直接拼接平均值和加权分
                    valueMap.put("objDeptId",deptResultMap.get("objDeptId"));
                    valueMap.put("objDeptName",deptResultMap.get("objDeptName"));
                }

                //平均值
                String avgKey = deptResultMap.get("columnId") + "_avg";
                valueMap.put(avgKey,deptResultMap.get("avgValue"));
                zj += Double.parseDouble(deptResultMap.get("avgValue").toString());

                //加权分
                String jqKey = deptResultMap.get("columnId") + "_jq";
                valueMap.put(jqKey,deptResultMap.get("jqValue"));
                jqzj += Double.parseDouble(deptResultMap.get("jqValue").toString());

                //判断是不是最后一条数据，如果是最后一条，将valuefMap保存到list里
                if (i == deptResultList.size()-1) {
                    valueMap.put("zj",df.format(zj));
                    valueMap.put("jqzj",df.format(jqzj));
                    valueMapList.add(valueMap);
                    zj = 0D;
                    jqzj = 0D;
                }

            }
            resultMap.put("valueMapList",valueMapList);
        }

        return resultMap;
    }

    /**
     * 获取个人表格的统计数据
     * @param activityId
     * @param sheetId
     * @return
     */
    @Override
    public Map<String, Object> selectUserStatisResult(Long activityId, Long sheetId,Long isnotRank) {
        Map<String,Object> resultMap = new HashMap<String,Object>();//最终返回结果
        DecimalFormat df = new DecimalFormat("#.0000");
        //参数map
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("activityId",activityId);
        paramMap.put("sheetId",sheetId);
        //1.查询表格项
        List<Map<String, Object>> colList = cpStatisResultMapper.selectColListByShId(paramMap);
        resultMap.put("colList",colList);//项名称

        //判断是否开启职级(1开启0关闭)
        if(isnotRank == 1) {
            //2.(活动开启职级)查询测评过的职级
            List<Map<String, Object>> rankList = cpStatisResultMapper.selectRankListByActIdShId(paramMap);
            //3.查询测评对象是个人的均值和加权分数(按人员id排序，是为了将相同部门放到一起，便于处理数据)
            List<Map<String, Object>> userResultList = cpStatisResultMapper.selectUserResultByActIdShId(paramMap);
            //拼接后的数据map
            List<Map<String,Object>> valueMapList = new ArrayList<Map<String,Object>>();
            Map<String,Object> valueMap = new HashMap<String,Object>();
            List<Object> userList = new ArrayList<Object>();//加入部门id，为了下方循环拼接的数据格式
            Double zj = 0D;
            Double jqzj = 0D;//加权总计
            for (int i = 0;i < userResultList.size();i++) {
                Map<String, Object> userResultMap = userResultList.get(i);

                if (!userList.contains(userResultMap.get("objUserId"))) {
                    userList.add(userResultMap.get("objUserId"));
                    if (valueMap.size() > 0) {//第一次添加时，valueMap是空集合
                        valueMap.put("zj",df.format(zj));
                        valueMap.put("jqzj",df.format(jqzj));
                        valueMapList.add(valueMap);//循环到新的部门了，将上一个部门加添加list里
                        zj = 0D;
                        jqzj = 0D;
                    }

                    valueMap = new HashMap<String,Object>();//新定义对象
                    //为了判断有相同的部门就不定义新的valueMap,直接拼接平均值和加权分
                    valueMap.put("objUserId",userResultMap.get("objUserId"));
                    valueMap.put("objUserName",userResultMap.get("objUserName"));
                }

                //平均值
                String avgKey = userResultMap.get("columnId") + "_" + userResultMap.get("rankId") + "_avg";
                valueMap.put(avgKey,userResultMap.get("avgValue"));
                zj += Double.parseDouble(userResultMap.get("avgValue").toString());

                //加权分
                String jqKey = userResultMap.get("columnId") + "_" + userResultMap.get("rankId") + "_jq";
                valueMap.put(jqKey,userResultMap.get("jqValue"));
                jqzj += Double.parseDouble(userResultMap.get("jqValue").toString());

                //判断是不是最后一条数据，如果是最后一条，将valuefMap保存到list里
                if (i == userResultList.size()-1) {
                    valueMap.put("zj",df.format(zj));
                    valueMap.put("jqzj",df.format(jqzj));
                    valueMapList.add(valueMap);
                    zj = 0D;
                    jqzj = 0D;
                }

            }
            resultMap.put("rankList",rankList);
            resultMap.put("valueMapList",valueMapList);
        } else {
            //关闭职级
            //3.查询测评对象是个人的均值和加权分数(按人员id排序，是为了将相同部门放到一起，便于处理数据)
            List<Map<String, Object>> userResultList = cpStatisResultMapper.selectUserResultByActIdShIdNoRank(paramMap);
            //拼接后的数据map
            List<Map<String,Object>> valueMapList = new ArrayList<Map<String,Object>>();
            Map<String,Object> valueMap = new HashMap<String,Object>();
            List<Object> userList = new ArrayList<Object>();//加入部门id，为了下方循环拼接的数据格式
            Double zj = 0D;
            Double jqzj = 0D;//加权总计
            for (int i = 0;i < userResultList.size();i++) {
                Map<String, Object> userResultMap = userResultList.get(i);

                if (!userList.contains(userResultMap.get("objUserId"))) {
                    userList.add(userResultMap.get("objUserId"));
                    if (valueMap.size() > 0) {//第一次添加时，valueMap是空集合
                        valueMap.put("zj",df.format(zj));
                        valueMap.put("jqzj",df.format(jqzj));
                        valueMapList.add(valueMap);//循环到新的部门了，将上一个部门加添加list里
                        zj = 0D;
                        jqzj = 0D;
                    }

                    valueMap = new HashMap<String,Object>();//新定义对象
                    //为了判断有相同的部门就不定义新的valueMap,直接拼接平均值和加权分
                    valueMap.put("objUserId",userResultMap.get("objUserId"));
                    valueMap.put("objUserName",userResultMap.get("objUserName"));
                }

                //平均值
                String avgKey = userResultMap.get("columnId") + "_avg";
                valueMap.put(avgKey,userResultMap.get("avgValue"));
                zj += Double.parseDouble(userResultMap.get("avgValue").toString());

                //加权分
                String jqKey = userResultMap.get("columnId") + "_jq";
                valueMap.put(jqKey,userResultMap.get("jqValue"));
                jqzj += Double.parseDouble(userResultMap.get("jqValue").toString());

                //判断是不是最后一条数据，如果是最后一条，将valuefMap保存到list里
                if (i == userResultList.size()-1) {
                    valueMap.put("zj",df.format(zj));
                    valueMap.put("jqzj",df.format(jqzj));
                    valueMapList.add(valueMap);
                    zj = 0D;
                    jqzj = 0D;
                }

            }
            resultMap.put("valueMapList",valueMapList);
        }





        return resultMap;
    }

    /**
     * 	原始数据
     * @param activityId
     * @param sheetId
     * @param objType-----0:个人；1:部门
     * @return
     */
	@Override
	public List<Map<String, Object>> selectOriDataResult(Long activityId, Long sheetId, String objType) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("activityId", activityId);
		paramMap.put("sheetId", sheetId);
		
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		if("1".equals(objType)) {
			mapList = cpStatisResultMapper.selectOriDeptResult(paramMap);
		}else {
			mapList = cpStatisResultMapper.selectOriUserResult(paramMap);
		}
		return mapList;
	}
}
