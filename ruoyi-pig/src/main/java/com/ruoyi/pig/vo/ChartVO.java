package com.ruoyi.pig.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("图表vo")
@Data
public class ChartVO {

    @ApiModelProperty(name = "时间")
    private String time;

    @ApiModelProperty(name = "值")
    private String value;
}
