package com.ruoyi.pig.mapper;

import com.ruoyi.pig.domain.TbData;
import com.ruoyi.pig.vo.ChartVO;

import java.util.List;


/**
 * 设备数据Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-08
 */
public interface TbDataMapper 
{
    /**
     * 查询设备数据
     *
     * @param id 设备数据主键
     * @return 设备数据
     */
    public TbData selectTbDataById(Long id);

    /**
     * 查询设备数据列表
     * 
     * @param tbData 设备数据
     * @return 设备数据集合
     */
    public List<TbData> selectTbDataList(TbData tbData);

    /**
     * 新增设备数据
     * 
     * @param tbData 设备数据
     * @return 结果
     */
    public int insertTbData(TbData tbData);

    /**
     * 修改设备数据
     * 
     * @param tbData 设备数据
     * @return 结果
     */
    public int updateTbData(TbData tbData);

    /**
     * 删除设备数据
     * 
     * @param id 设备数据主键
     * @return 结果
     */
    public int deleteTbDataById(Long id);

    /**
     * 批量删除设备数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbDataByIds(Long[] ids);

    /**
     * 查找温度图表信息
     * @return 结果
     */
    public List<ChartVO> selectTemperatureChart();

    /**
     * 查找湿度图表信息
     * @return 结果
     */
    List<ChartVO> selectHumidityLChart();
}
