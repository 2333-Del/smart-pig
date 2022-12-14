package com.ruoyi.pig.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import lombok.NoArgsConstructor;

/**
 * 最新数据对象 tb_new_data
 * 
 * @author ruoyi
 * @date 2022-10-09
 */
@Data
@NoArgsConstructor
@ApiModel("最新数据类")
public class TbNewData
{
    /** id */
    private Long id;

    /** 设备编号 */
    @Excel(name = "设备编号")
    @ApiModelProperty(name = "设备编号")
    private String equipmentId;

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

    public TbNewData(TbData tbData){
        try {
            this.acquisitionTime = tbData.getAcquisitionTime();
            this.co = tbData.getCo();
            this.co2 = tbData.getCo2();
            this.ammonia = tbData.getAmmonia();
            this.humidity = tbData.getHumidity();
            this.pm10 = tbData.getPm10();
            this.pm25 = tbData.getPm25();
            this.sulfHydr = tbData.getSulfHydr();
            this.temperature = tbData.getTemperature();
            this.equipmentId = tbData.getEquipmentId();
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
