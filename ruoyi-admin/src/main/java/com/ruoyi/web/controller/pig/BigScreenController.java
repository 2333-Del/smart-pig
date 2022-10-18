package com.ruoyi.web.controller.pig;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.pig.domain.TbData;
import com.ruoyi.pig.domain.TbEquipment;
import com.ruoyi.pig.domain.TbNewData;
import com.ruoyi.pig.service.ITbDataService;
import com.ruoyi.pig.service.ITbEquipmentService;
import com.ruoyi.pig.service.ITbNewDataService;
import com.ruoyi.pig.vo.ChartListVo;
import com.ruoyi.pig.vo.ChartVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags="大屏")
@RestController
@RequestMapping("/pig/bigScreen")
public class BigScreenController extends BaseController {

    @Autowired
    private ITbNewDataService iTbNewDataService;

    @Autowired
    private ITbDataService iTbDataService;

    @Autowired
    private ITbEquipmentService equipmentService;

    @ApiOperation("大屏获取最新数据")
    @GetMapping("/newData")
    public R<List<TbNewData>> list(TbNewData tbNewData){
        List<TbNewData> tbNewDataList = iTbNewDataService.selectTbNewDataList(tbNewData);
        return R.ok(tbNewDataList);
    }

    /**
     * 用于图表返回温度湿度等信息
     * @return
     */
    @GetMapping("/chartData")
    @ApiOperation("用于图表返回温度湿度等信息")
    public R<ChartListVo> chartData(ChartVO chartVO) {
        ChartListVo chartListVo = new ChartListVo();
        List<ChartVO> temperatureList = iTbDataService.selectTemperatureChart();//查找温度图表数据
        chartListVo.setTemperature(temperatureList);
        List<ChartVO> humidityList = iTbDataService.selectHumidityLChart();//查找湿度图表数据
        chartListVo.setHumidity(humidityList);
        return R.ok(chartListVo);
    }

    @ApiOperation("获取设备信息")
    @GetMapping("/getEquipment")
    public R<List<TbEquipment>> getEquipment(TbEquipment tbEquipment){
        List<TbEquipment> tbEquipments = equipmentService.selectTbEquipmentList(tbEquipment);
        return R.ok(tbEquipments);
    }


}
