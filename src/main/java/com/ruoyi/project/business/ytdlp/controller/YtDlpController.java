package com.ruoyi.project.business.ytdlp.controller;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.business.charge.domain.ChargeFrameVo;
import com.ruoyi.project.business.ytdlp.service.YtDlpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: YtDlpController
 * @Description:
 * @Author: zhangwk
 * @Date: 2025-04-10 17:59
 * @Version: 1.0
 **/
@Api("yt-dlp")
@ApiOperation("yt-dlp")
@Controller
@RequestMapping("/business/yt-dlp")
public class YtDlpController {

    @Autowired
    private YtDlpService ytDlpService;

    /**
     * 下载指定资源
     */
    @ApiOperation("下载指定资源")
    @GetMapping("/downloads")
    @ResponseBody
    public AjaxResult downloads(@RequestParam String downloadUrl)
    {

        return AjaxResult.success(ytDlpService.downloads(downloadUrl));
    }
}
