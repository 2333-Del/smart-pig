package com.ruoyi.pig.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.Date;

/**
 * 设备数据对象 tb_data
 * 
 * @author ruoyi
 * @date 2022-10-08
 */
@Data
@ApiModel("数据类")
public class TbData
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 设备编号 */
    @Excel(name = "设备编号")
    @ApiModelProperty(name = "设备编号")
    private Long equipmentId;

    /** 温度 */
    @Excel(name = "温度")
    @ApiModelProperty(name = "温度")
    private String temperature;

    /** 氨气含量 */
    @Excel(name = "氨气含量")
    @ApiModelProperty(name = "氨气含量")
    private String ammonia;

    /** 湿度 */
    @Excel(name = "湿度")
    @ApiModelProperty(name = "湿度")
    private String humidity;

    /** pm2.5 */
    @Excel(name = "pm2.5")
    @ApiModelProperty(name = "pm2.5")
    private String pm25;

    /** pm10含量 */
    @Excel(name = "pm10含量")
    @ApiModelProperty(name = "pm10含量")
    private String pm10;

    /** 二氧化碳 */
    @Excel(name = "二氧化碳")
    @ApiModelProperty(name = "二氧化碳")
    private String co2;

    /** 一氧化碳 */
    @Excel(name = "一氧化碳")
    @ApiModelProperty(name = "一氧化碳")
    private String co;

    /** 硫化氢 */
    @Excel(name = "硫化氢")
    @ApiModelProperty(name = "硫化氢")
    private String sulfHydr;

    /** 采集时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采集时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty(name = "采集时间")
    private Date acquisitionTime;

}
