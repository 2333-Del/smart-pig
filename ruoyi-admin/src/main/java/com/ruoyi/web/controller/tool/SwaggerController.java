package com.ruoyi.web.controller.tool;

import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruoyi.common.core.controller.BaseController;

/**
 * swagger 接口
 * 
 * @author ruoyi
 */
@Controller
public class SwaggerController extends BaseController
{
//    @PreAuthorize("@ss.hasPermi('tool:swagger:view')")
    @GetMapping("/doc.html")
    public String index()
    {
        return redirect("/swagger-ui.html");
    }
}
