package com.ruoyi.project.business.charge.utils;

import java.math.BigDecimal;

/**
 * @ClassName: FrameParseUtil
 * @Description: 帧数据解析工具类
 * @Author: zhangwk
 * @Date: 2025-03-08 17:29
 * @Version: 1.0
 **/
public class FrameParseUtil {

    public static String bytesParseFor4(String hexString) {

        byte[] data = HexConverterUtil.hexStringToByteArray(hexString);

        if (data.length != 4) {
            throw new IllegalArgumentException("Invalid totalPower data length. Expected 4 bytes.");
        }

        // 提取毫秒值（前两个字节）
        int value = ((data[3] & 0xFF) << 24) | ((data[2] & 0xFF) << 16) | ((data[1] & 0xFF) << 8) | (data[0] & 0xFF);
        BigDecimal valueBigDecimal = new BigDecimal(value).divide(new BigDecimal(10000)).setScale(4, BigDecimal.ROUND_HALF_UP);
        return valueBigDecimal.toString();
    }

    public static String bytesParseFor2(String hexString) {

        byte[] data = HexConverterUtil.hexStringToByteArray(hexString);

        if (data.length != 2) {
            throw new IllegalArgumentException("Invalid totalPower data length. Expected 2 bytes.");
        }

        // 提取毫秒值（前两个字节）
        int value = ((data[1] & 0xFF) << 8) | (data[0] & 0xFF);
        BigDecimal valueBigDecimal = new BigDecimal(value).divide(new BigDecimal(10)).setScale(1, BigDecimal.ROUND_HALF_UP);
        return valueBigDecimal.toString();
    }

    public static String bytesParseFor2v2(String hexString) {

        byte[] data = HexConverterUtil.hexStringToByteArray(hexString);

        if (data.length != 2) {
            throw new IllegalArgumentException("Invalid totalPower data length. Expected 2 bytes.");
        }

        // 提取毫秒值（前两个字节）
        int value = ((data[1] & 0xFF) << 8) | (data[0] & 0xFF);
        BigDecimal valueBigDecimal = new BigDecimal(value);
        return valueBigDecimal.toString();
    }

    public static BigDecimal bytesParseFor1(String hexString) {

        byte[] data = HexConverterUtil.hexStringToByteArray(hexString);

        if (data.length != 1) {
            throw new IllegalArgumentException("Invalid totalPower data length. Expected 1 bytes.");
        }

        // 提取毫秒值（前两个字节）
        int value = (data[0] & 0xFF);
        return new BigDecimal(value);
    }

}
