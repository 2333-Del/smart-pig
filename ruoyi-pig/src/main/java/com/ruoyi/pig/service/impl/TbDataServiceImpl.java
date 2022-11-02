package com.ruoyi.pig.service.impl;

import java.util.List;

import com.ruoyi.pig.domain.TbData;
import com.ruoyi.pig.mapper.TbDataMapper;
import com.ruoyi.pig.service.ITbDataService;
import com.ruoyi.pig.vo.ChartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 设备数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-08
 */
@Service
public class TbDataServiceImpl implements ITbDataService
{
    @Autowired
    private TbDataMapper tbDataMapper;

    /**
     * 查询设备数据
     *
     * @param id 设备数据主键
     * @return 设备数据
     */
    @Override
    public TbData selectTbDataById(Long id)
    {
        return tbDataMapper.selectTbDataById(id);
    }

    /**
     * 查询设备数据列表
     * 
     * @param tbData 设备数据
     * @return 设备数据
     */
    @Override
    public List<TbData> selectTbDataList(TbData tbData)
    {
        return tbDataMapper.selectTbDataList(tbData);
    }

    /**
     * 新增设备数据
     * 
     * @param tbData 设备数据
     * @return 结果
     */
    @Override
    public int insertTbData(TbData tbData)
    {
        return tbDataMapper.insertTbData(tbData);
    }

    /**
     * 修改设备数据
     * 
     * @param tbData 设备数据
     * @return 结果
     */
    @Override
    public int updateTbData(TbData tbData)
    {
        return tbDataMapper.updateTbData(tbData);
    }

    /**
     * 批量删除设备数据
     * 
     * @param ids 需要删除的设备数据主键
     * @return 结果
     */
    @Override
    public int deleteTbDataByIds(Long[] ids)
    {
        return tbDataMapper.deleteTbDataByIds(ids);
    }

    /**
     * 删除设备数据信息
     * 
     * @param id 设备数据主键
     * @return 结果
     */
    @Override
    public int deleteTbDataById(Long id)
    {
        return tbDataMapper.deleteTbDataById(id);
    }

    /**
     * 查找温度图表信息
     * @return 结果
     */
    @Override
    public List<ChartVO> selectTemperatureChart() {
        return tbDataMapper.selectTemperatureChart();
    }

    /**
     * 查找湿度图表信息
     * @return 结果
     */
    @Override
    public List<ChartVO> selectHumidityLChart() {
        return tbDataMapper.selectHumidityLChart();
    }

    /**
     * 查找co2图表信息
     * @return 结果
     */
    @Override
    public List<ChartVO> selectCo2Chart() {
        return tbDataMapper.selectCo2Chart();
    }
}
