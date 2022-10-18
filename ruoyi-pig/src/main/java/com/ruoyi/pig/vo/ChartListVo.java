package com.ruoyi.pig.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("图表listvo类")
public class ChartListVo {
    @ApiModelProperty(name="温度")
    private List<ChartVO> temperature;
    @ApiModelProperty(name="湿度")
    private List<ChartVO> humidity;
}
