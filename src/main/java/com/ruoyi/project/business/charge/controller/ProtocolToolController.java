package com.ruoyi.project.business.charge.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Anonymous;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.business.charge.domain.ChargeFrameVo;
import com.ruoyi.project.business.charge.service.ProtocolToolService;
import com.ruoyi.project.business.nav.domain.NavConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * @ClassName: ProtocolToolController
 * @Description: 协议解析工具
 * @Author: zhangwk
 * @Date: 2025-03-05 17:10
 * @Version: 1.0
 **/
@Api("协议解析工具")
@ApiOperation("协议解析工具")
@Controller
@RequestMapping("/business/charge/protocolTool")
public class ProtocolToolController {

    @Autowired
    private ProtocolToolService protocolToolService;

    /**
     * 根据协议解析指定帧数据
     */
    @ApiOperation("根据协议解析指定帧数据")
    @Anonymous
    @RequestMapping("/parse/{protocol}/{frame}/{data}")
    @ResponseBody
    public List<ChargeFrameVo> parse(@PathVariable Integer protocol, @PathVariable Integer frame, @PathVariable String data)
    {

        return protocolToolService.parse(protocol, frame, data);
    }
}
