package com.ruoyi.web.controller.pig;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.pig.domain.TbData;
import com.ruoyi.pig.service.ITbDataService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备数据Controller
 * 
 * @author ruoyi
 * @date 2022-10-08
 */
@RestController
@RequestMapping("/pig/data")
public class TbDataController extends BaseController
{
    @Autowired
    private ITbDataService tbDataService;

    /**
     * 查询设备数据列表
     */
    @PreAuthorize("@ss.hasPermi('pig:data:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbData tbData)
    {
        startPage();
        List<TbData> list = tbDataService.selectTbDataList(tbData);
        return getDataTable(list);
    }

    /**
     * 导出设备数据列表
     */
    @PreAuthorize("@ss.hasPermi('pig:data:export')")
    @Log(title = "设备数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbData tbData)
    {
        List<TbData> list = tbDataService.selectTbDataList(tbData);
        ExcelUtil<TbData> util = new ExcelUtil<TbData>(TbData.class);
        util.exportExcel(response, list, "设备数据数据");
    }

    /**
     * 获取设备数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('pig:data:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tbDataService.selectTbDataById(id));
    }

    /**
     * 新增设备数据
     */
    @PreAuthorize("@ss.hasPermi('pig:data:add')")
    @Log(title = "设备数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbData tbData)
    {
        return toAjax(tbDataService.insertTbData(tbData));
    }

    /**
     * 修改设备数据
     */
    @PreAuthorize("@ss.hasPermi('pig:data:edit')")
    @Log(title = "设备数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbData tbData)
    {
        return toAjax(tbDataService.updateTbData(tbData));
    }

    /**
     * 删除设备数据
     */
    @PreAuthorize("@ss.hasPermi('pig:data:remove')")
    @Log(title = "设备数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbDataService.deleteTbDataByIds(ids));
    }
}
