package com.ruoyi.project.business.charge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChargeFrameEnum {

    TRADING_RECORD(0x3B, "交易记录(充电桩->运营平台)"),
    PILE_REALTIME_DATA(0x13, "离线监测数据(充电桩->运营平台)");

    private final Integer code;
    private final String message;
}
