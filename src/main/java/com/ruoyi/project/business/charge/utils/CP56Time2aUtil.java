package com.ruoyi.project.business.charge.utils;

import java.time.LocalDateTime;

public class CP56Time2aUtil {

    /**
     * 解析CP56Time2a格式的字节数组为LocalDateTime
     *
     * @param data
     * @return
     */
    public static LocalDateTime parseCP56Time2a(byte[] data) {
        if (data.length != 7) {
            throw new IllegalArgumentException("Invalid CP56Time2a data length. Expected 7 bytes.");
        }

        // 提取毫秒值（前两个字节）
        int millis = ((data[0] & 0xFF) << 8) | (data[1] & 0xFF);

        // 提取分钟、小时、日、月、年
        int minute = data[2] & 0x3F; // 取低6位
        int hour = data[3] & 0x1F;   // 取低5位
        int day = data[4] & 0x1F;    // 取低5位
        int month = data[5] & 0x0F;  // 取低4位
        int year = (data[6] & 0x7F) + 2000; // 取低7位，假设年份为20xx

        // 计算秒和纳秒
        int seconds = millis / 1000;
        int nanos = (millis % 1000) * 1000000;

        return LocalDateTime.of(year, month, day, hour, minute, seconds, nanos);
    }

    /**
     * 解析CP56Time2a格式的字节数组为LocalDateTime
     *
     * @param hexString
     * @return
     */
    public static LocalDateTime parseCP56Time2a(String hexString) {

        byte[] data = HexConverterUtil.hexStringToByteArray(hexString);

        if (data.length != 7) {
            throw new IllegalArgumentException("Invalid CP56Time2a data length. Expected 7 bytes.");
        }

        // 提取毫秒值（前两个字节）
        int millis = ((data[0] & 0xFF) << 8) | (data[1] & 0xFF);

        // 提取分钟、小时、日、月、年
        int minute = data[2] & 0x3F; // 取低6位
        int hour = data[3] & 0x1F;   // 取低5位
        int day = data[4] & 0x1F;    // 取低5位
        int month = data[5] & 0x0F;  // 取低4位
        int year = (data[6] & 0x7F) + 2000; // 取低7位，假设年份为20xx

        // 计算秒和纳秒
        int seconds = millis / 1000;
        int nanos = (millis % 1000) * 1000000;

        return LocalDateTime.of(year, month, day, hour, minute, seconds, nanos);
    }
}
