package com.ruoyi.pig.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.pig.vo.JsonVo;
import lombok.Data;

import java.util.List;

@Data
public class JsonData {
    String deviceName;
    String deviceNo;
    String deviceIP;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    String connDate;
    List<JsonVo> sensors;

}
