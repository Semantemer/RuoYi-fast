package com.ruoyi.project.business.ytdlp.service.impl;

import com.ruoyi.project.business.ytdlp.config.YtDlpConfig;
import com.ruoyi.project.business.ytdlp.service.YtDlpService;
import com.ruoyi.project.business.ytdlp.utils.YtDlpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: YtDlpServiceImpl
 * @Description:
 * @Author: zhangwk
 * @Date: 2025-04-10 18:06
 * @Version: 1.0
 **/
@Service
public class YtDlpServiceImpl implements YtDlpService {

    @Resource
    private YtDlpConfig ytDlpConfig;

    @Override
    public String downloads(String downloadUrl) {

        YtDlpUtil.downloadVideo(ytDlpConfig.getCallFilePath(), downloadUrl, ytDlpConfig.getDownloadOutputDir());

        return "正在下载中，请在下载目录查看结果";
    }
}
