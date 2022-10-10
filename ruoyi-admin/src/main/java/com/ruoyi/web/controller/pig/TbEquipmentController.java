package com.ruoyi.web.controller.pig;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.pig.domain.TbEquipment;
import com.ruoyi.pig.service.ITbEquipmentService;
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
 * 设备Controller
 *
 * @author ruoyi
 * @date 2022-10-08
 */
@RestController
@RequestMapping("/pig/equipment")
public class TbEquipmentController extends BaseController {
    @Autowired
    private ITbEquipmentService tbEquipmentService;

    /**
     * 查询设备列表
     */
    @PreAuthorize("@ss.hasPermi('pig:equipment:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbEquipment tbEquipment) {
        startPage();
        List<TbEquipment> list = tbEquipmentService.selectTbEquipmentList(tbEquipment);
        return getDataTable(list);
    }

    /**
     * 导出设备列表
     */
    @PreAuthorize("@ss.hasPermi('pig:equipment:export')")
    @Log(title = "设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbEquipment tbEquipment) {
        List<TbEquipment> list = tbEquipmentService.selectTbEquipmentList(tbEquipment);
        ExcelUtil<TbEquipment> util = new ExcelUtil<TbEquipment>(TbEquipment. class);
        util.exportExcel(response, list, "设备数据");
    }

    /**
     * 获取设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('pig:equipment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(tbEquipmentService.selectTbEquipmentById(id));
    }

    /**
     * 新增设备
     */
    @PreAuthorize("@ss.hasPermi('pig:equipment:add')")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbEquipment tbEquipment) {
        return toAjax(tbEquipmentService.insertTbEquipment(tbEquipment));
    }

    /**
     * 修改设备
     */
    @PreAuthorize("@ss.hasPermi('pig:equipment:edit')")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbEquipment tbEquipment) {
        return toAjax(tbEquipmentService.updateTbEquipment(tbEquipment));
    }

    /**
     * 删除设备
     */
    @PreAuthorize("@ss.hasPermi('pig:equipment:remove')")
    @Log(title = "设备", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tbEquipmentService.deleteTbEquipmentByIds(ids));
    }
}
