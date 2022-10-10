package com.ruoyi.pig.service;

import java.util.List;
import com.ruoyi.pig.domain.TbNewData;

/**
 * 最新数据Service接口
 * 
 * @author ruoyi
 * @date 2022-10-09
 */
public interface ITbNewDataService 
{
    /**
     * 查询最新数据
     * 
     * @param id 最新数据主键
     * @return 最新数据
     */
    public TbNewData selectTbNewDataById(Long id);

    /**
     * 查询最新数据列表
     * 
     * @param tbNewData 最新数据
     * @return 最新数据集合
     */
    public List<TbNewData> selectTbNewDataList(TbNewData tbNewData);

    /**
     * 新增最新数据
     * 
     * @param tbNewData 最新数据
     * @return 结果
     */
    public int insertTbNewData(TbNewData tbNewData);

    /**
     * 修改最新数据
     * 
     * @param tbNewData 最新数据
     * @return 结果
     */
    public int updateTbNewData(TbNewData tbNewData);

    /**
     * 批量删除最新数据
     * 
     * @param ids 需要删除的最新数据主键集合
     * @return 结果
     */
    public int deleteTbNewDataByIds(Long[] ids);

    /**
     * 删除最新数据信息
     * 
     * @param id 最新数据主键
     * @return 结果
     */
    public int deleteTbNewDataById(Long id);
}
