package com.ruoyi.project.business.ytdlp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: YtDlpConfig
 * @Description:
 * @Author: zhangwk
 * @Date: 2025-04-10 19:06
 * @Version: 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "custom.ytdlp")
public class YtDlpConfig {

    /**
     *  yt-dlp 可执行文件路径
     */
    private String callFilePath;

    /**
     *  下载文件输出目录
     */
    private String downloadOutputDir;
}
