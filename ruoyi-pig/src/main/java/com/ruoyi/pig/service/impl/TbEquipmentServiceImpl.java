package com.ruoyi.pig.service.impl;

import java.util.List;

import com.ruoyi.pig.domain.TbEquipment;
import com.ruoyi.pig.mapper.TbEquipmentMapper;
import com.ruoyi.pig.service.ITbEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 设备Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-08
 */
@Service
public class TbEquipmentServiceImpl implements ITbEquipmentService {

    @Autowired
    private TbEquipmentMapper tbEquipmentMapper;

    /**
     * 查询设备
     *
     * @param id 设备主键
     * @return 设备
     */
    @Override
    public TbEquipment selectTbEquipmentById(Long id) {
        return tbEquipmentMapper.selectTbEquipmentById(id);
    }

    /**
     * 查询设备列表
     *
     * @param tbEquipment 设备
     * @return 设备
     */
    @Override
    public List<TbEquipment> selectTbEquipmentList(TbEquipment tbEquipment) {
        return tbEquipmentMapper.selectTbEquipmentList(tbEquipment);
    }

    /**
     * 新增设备
     *
     * @param tbEquipment 设备
     * @return 结果
     */
    @Override
    public int insertTbEquipment(TbEquipment tbEquipment) {
            return tbEquipmentMapper.insertTbEquipment(tbEquipment);
    }

    /**
     * 修改设备
     *
     * @param tbEquipment 设备
     * @return 结果
     */
    @Override
    public int updateTbEquipment(TbEquipment tbEquipment) {
        return tbEquipmentMapper.updateTbEquipment(tbEquipment);
    }

    /**
     * 批量删除设备
     *
     * @param ids 需要删除的设备主键
     * @return 结果
     */
    @Override
    public int deleteTbEquipmentByIds(Long[] ids) {
        return tbEquipmentMapper.deleteTbEquipmentByIds(ids);
    }

    /**
     * 删除设备信息
     *
     * @param id 设备主键
     * @return 结果
     */
    @Override
    public int deleteTbEquipmentById(Long id) {
        return tbEquipmentMapper.deleteTbEquipmentById(id);
    }

}
