package com.ruoyi.project.business.utils;

import java.io.File;

/**
 * @ClassName: FileUtils
 * @Description:
 * @Author: zhangwk
 * @Date: 2025-04-10 18:12
 * @Version: 1.0
 **/
public class CustomFileUtils {

    public static void checkAndCreateDir(String outputDir) {
        // 创建输出目录
        File outputDirFile = new File(outputDir);
        if(!outputDirFile.exists()){
            boolean mkdirResult = outputDirFile.mkdirs();
            if(!mkdirResult){
                throw new RuntimeException("创建输出目录失败");
            }
        }
    }
}
