package com.ruoyi.pig.service;

import com.ruoyi.pig.domain.TbEquipment;

import java.util.List;


/**
 * 设备Service接口
 *
 * @author ruoyi
 * @date 2022-10-08
 */
public interface ITbEquipmentService {
    /**
     * 查询设备
     *
     * @param id 设备主键
     * @return 设备
     */
     TbEquipment selectTbEquipmentById(Long id);

    /**
     * 查询设备列表
     *
     * @param tbEquipment 设备
     * @return 设备集合
     */
     List<TbEquipment> selectTbEquipmentList(TbEquipment tbEquipment);

    /**
     * 新增设备
     *
     * @param tbEquipment 设备
     * @return 结果
     */
     int insertTbEquipment(TbEquipment tbEquipment);

    /**
     * 修改设备
     *
     * @param tbEquipment 设备
     * @return 结果
     */
     int updateTbEquipment(TbEquipment tbEquipment);

    /**
     * 批量删除设备
     *
     * @param ids 需要删除的设备主键集合
     * @return 结果
     */
     int deleteTbEquipmentByIds(Long[] ids);

    /**
     * 删除设备信息
     *
     * @param id 设备主键
     * @return 结果
     */
     int deleteTbEquipmentById(Long id);

}
