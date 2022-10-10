package com.ruoyi.pig.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pig.mapper.TbNewDataMapper;
import com.ruoyi.pig.domain.TbNewData;
import com.ruoyi.pig.service.ITbNewDataService;

/**
 * 最新数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-09
 */
@Service
public class TbNewDataServiceImpl implements ITbNewDataService 
{
    @Autowired
    private TbNewDataMapper tbNewDataMapper;

    /**
     * 查询最新数据
     * 
     * @param id 最新数据主键
     * @return 最新数据
     */
    @Override
    public TbNewData selectTbNewDataById(Long id)
    {
        return tbNewDataMapper.selectTbNewDataById(id);
    }

    /**
     * 查询最新数据列表
     * 
     * @param tbNewData 最新数据
     * @return 最新数据
     */
    @Override
    public List<TbNewData> selectTbNewDataList(TbNewData tbNewData)
    {
        return tbNewDataMapper.selectTbNewDataList(tbNewData);
    }

    /**
     * 新增最新数据
     * 
     * @param tbNewData 最新数据
     * @return 结果
     */
    @Override
    public int insertTbNewData(TbNewData tbNewData)
    {
        return tbNewDataMapper.insertTbNewData(tbNewData);
    }

    /**
     * 修改最新数据
     * 
     * @param tbNewData 最新数据
     * @return 结果
     */
    @Override
    public int updateTbNewData(TbNewData tbNewData)
    {
        return tbNewDataMapper.updateTbNewData(tbNewData);
    }

    /**
     * 批量删除最新数据
     * 
     * @param ids 需要删除的最新数据主键
     * @return 结果
     */
    @Override
    public int deleteTbNewDataByIds(Long[] ids)
    {
        return tbNewDataMapper.deleteTbNewDataByIds(ids);
    }

    /**
     * 删除最新数据信息
     * 
     * @param id 最新数据主键
     * @return 结果
     */
    @Override
    public int deleteTbNewDataById(Long id)
    {
        return tbNewDataMapper.deleteTbNewDataById(id);
    }
}
