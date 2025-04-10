package com.ruoyi.project.business.ytdlp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.business.utils.CustomFileUtils;
import com.ruoyi.project.business.utils.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: YtDlpTest
 * @Description: yt-dlp下载工具
 * @Author: zhangwk
 * @Date: 2025-04-10 16:44
 * @Version: 1.0
 **/
@Slf4j
public class YtDlpUtil {

    public static void downloadVideo(String callFilePath, String downloadUrl, String downloadOutputDir) {

        CustomFileUtils.checkAndCreateDir(downloadOutputDir);

        callFilePath = checkAndGetDefaultCallFilePath(callFilePath);

        String finalCallFilePath = callFilePath;
        ThreadPoolUtil.createCachedThreadPool().submit(() -> {

            try {
                // 构造命令
                ProcessBuilder processBuilder = new ProcessBuilder(
                        finalCallFilePath,
                        "-o", downloadOutputDir + File.separator + "%(title)s.%(ext)s",
                        "--no-check-certificate",  // 绕过证书验证
                        downloadUrl
                );

                // 合并错误流和输出流
                processBuilder.redirectErrorStream(true);

                // 启动进程
                Process process = processBuilder.start();

                // 打印命令行输出（调试用）
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }

                // 等待进程结束
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    System.out.println("下载成功！");
                } else {
                    System.out.println("下载失败，错误码: " + exitCode);
                }
            } catch (IOException | InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        });
    }

    private static String checkAndGetDefaultCallFilePath(String callFilePath) {
        if (StringUtils.isEmpty(callFilePath)) {

            // 获取当前线程的类加载器
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            // 获取资源文件的 URL
            URL resourceUrl = classLoader.getResource("lib/yt-dlp.exe");
            if (resourceUrl == null) {
                throw new RuntimeException("yt-dlp 工具未找到");
            }

            callFilePath = resourceUrl.getPath();
        }
        return callFilePath;
    }

    public static void main(String[] args) {
        String videoUrl = "https://www.bilibili.com/video/BV1Xx411c7mD";
        String outputDir = "D:\\downloads";
        downloadVideo(null, videoUrl, outputDir);
    }
}
