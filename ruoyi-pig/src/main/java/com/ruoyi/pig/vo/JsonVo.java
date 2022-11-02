package com.ruoyi.pig.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class JsonVo {
     String code;
     String name;
     String value ;
     String unit;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     Date time;

}
