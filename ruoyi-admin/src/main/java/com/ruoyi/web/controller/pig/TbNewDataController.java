package com.ruoyi.web.controller.pig;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.pig.domain.TbNewData;
import com.ruoyi.pig.service.ITbNewDataService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 最新数据Controller
 * 
 * @author ruoyi
 * @date 2022-10-09
 */
@RestController
@RequestMapping("/pig/newData")
public class TbNewDataController extends BaseController
{
    @Autowired
    private ITbNewDataService tbNewDataService;

    /**
     * 查询最新数据列表
     */
    @PreAuthorize("@ss.hasPermi('pig:data:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbNewData tbNewData)
    {
        startPage();
        List<TbNewData> list = tbNewDataService.selectTbNewDataList(tbNewData);
        return getDataTable(list);
    }

    /**
     * 导出最新数据列表
     */
    @PreAuthorize("@ss.hasPermi('pig:data:export')")
    @Log(title = "最新数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbNewData tbNewData)
    {
        List<TbNewData> list = tbNewDataService.selectTbNewDataList(tbNewData);
        ExcelUtil<TbNewData> util = new ExcelUtil<TbNewData>(TbNewData.class);
        util.exportExcel(response, list, "最新数据数据");
    }

    /**
     * 获取最新数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('pig:data:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tbNewDataService.selectTbNewDataById(id));
    }

    /**
     * 新增最新数据
     */
    @PreAuthorize("@ss.hasPermi('pig:data:add')")
    @Log(title = "最新数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbNewData tbNewData)
    {
        return toAjax(tbNewDataService.insertTbNewData(tbNewData));
    }

    /**
     * 修改最新数据
     */
    @PreAuthorize("@ss.hasPermi('pig:data:edit')")
    @Log(title = "最新数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbNewData tbNewData)
    {
        return toAjax(tbNewDataService.updateTbNewData(tbNewData));
    }

    /**
     * 删除最新数据
     */
    @PreAuthorize("@ss.hasPermi('pig:data:remove')")
    @Log(title = "最新数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbNewDataService.deleteTbNewDataByIds(ids));
    }
}
