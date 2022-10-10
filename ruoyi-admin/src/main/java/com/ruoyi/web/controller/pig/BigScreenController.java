package com.ruoyi.web.controller.pig;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.pig.domain.TbData;
import com.ruoyi.pig.domain.TbNewData;
import com.ruoyi.pig.service.ITbDataService;
import com.ruoyi.pig.service.ITbNewDataService;
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

    @ApiOperation("大屏获取")
    @GetMapping("/list")
    public TableDataInfo<TbNewData> list(){
        List<TbNewData> tbNewDataList = iTbNewDataService.selectTbNewDataList(new TbNewData());
        return getDataTable(tbNewDataList);
    }

    /**
     * 用于图表返回温度湿度等信息
     * @return
     */
    @GetMapping("/chartData")
    public AjaxResult chartData() {
        AjaxResult ajaxResult = new AjaxResult();
        List<ChartVO> temperatureList = iTbDataService.selectTemperatureChart();//查找温度图表数据
        ajaxResult.put("temperature",temperatureList);
        List<ChartVO> humidityList = iTbDataService.selectHumidityLChart();//查找湿度图表数据
        ajaxResult.put("humidity",humidityList);
        return ajaxResult;
    }

}