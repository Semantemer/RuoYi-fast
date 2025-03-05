package com.ruoyi.project.business.charge.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String localDateTime2Str(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault(); // 或者使用特定的时区，如 ZoneId.of("America/New_York")
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 包含时区信息
        String zonedDateTimeStr = zonedDateTime.format(formatter);

        return zonedDateTimeStr;
    }
}
