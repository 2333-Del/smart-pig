package com.ruoyi.pig.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备对象 tb_equipment
 * 
 * @author ruoyi
 * @date 2022-10-08
 */
@Data
@ApiModel("设备类")
public class TbEquipment
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 设备编号 */
    @Excel(name = "设备编号")
    @ApiModelProperty(name = "设备编号")
    private String equipmentId;

    /** 设备名称 */
    @Excel(name = "设备名称")
    @ApiModelProperty(name = "设备名称")
    private String equipmentName;

    /** 经纬度 */
    @Excel(name = "经纬度")
    @ApiModelProperty(name = "经纬度")
    private String longAndLati;

    /** 设备类型 1表示气味 */
    @Excel(name = "设备类型 1表示气味")
    @ApiModelProperty(name = "设备类型 1表示气味")
    private Long equipmentType;

    /** 最近连接时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近连接时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty(name = "最近连接时间")
    private Date connectTime;

    /** 设备ip */
    @Excel(name = "设备ip")
    @ApiModelProperty(name = "设备ip")
    private String equipmentIp;

    /** 在线状态 0下线  1在线 */
    @Excel(name = "在线状态 0下线  1在线")
    @ApiModelProperty(name = "在线状态 0下线  1在线")
    private Long onlineType;

    /** 设备生产商 */
    @Excel(name = "设备生产商")
    @ApiModelProperty(name = "设备生产商")
    private String manufacturer;

}
