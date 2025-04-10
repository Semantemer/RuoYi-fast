package com.ruoyi.project.business.charge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChargeFrameEnum {

    PILE_AUTH(0x01, "充电桩登录认证(充电桩->运营平台)"),
    PILE_AUTH_RETURN(0x02, "登录认证应答(运营平台->充电桩)"),

    PILE_KEEP_ALIVE(0x03, "充电桩心跳包(充电桩->运营平台)"),
    PILE_KEEP_ALIVE_RETURN(0x04, "心跳包应答(运营平台->充电桩)"),

    PILE_BILLING_MODEL_CHECK(0x05, "计费模型验证请求(充电桩->运营平台)"),
    PILE_BILLING_MODEL_CHECK_RETURN(0x06, "计费模型验证请求应答(运营平台->充电桩)"),

    PILE_BILLING_MODEL_GET(0x09, "充电桩计费模型请求(充电桩->运营平台)"),
    PILE_BILLING_MODEL_GET_RETURN(0x0A, "计费模型请求应答(运营平台->充电桩)"),

    PILE_REALTIME_DATA_RETURN(0x12, "读取实时监测数据(运营平台->充电桩)"),
    PILE_REALTIME_DATA(0x13, "离线监测数据(充电桩->运营平台)"),

    PILE_CHARGING_LINK(0x15, "充电握手(充电桩->运营平台)"),

    PILE_BMS_CONFIG(0x17, "参数配置(充电桩->运营平台)"),

    PILE_CHARING_FINISH(0x19, "充电结束(充电桩->运营平台)"),

    PILE_ERROR(0x1B, "错误报文(充电桩->运营平台)"),

    PILE_BMS_STOP_CHARGING(0x1D, "充电阶段BMS中止(充电桩->运营平台)"),

    PILE_CHARGER_STOP_CHARGING(0x21, "充电阶段充电机中止(充电桩->运营平台)"),

    PILE_CHARGING_BMS_REQ_CHARGER_OUT(0x23, "充电过程BMS需求与充电机(充电桩->运营平台)"),

    PILE_CHARGING_BMS_DATA(0x25, "充电过程BMS信息(充电桩->运营平台)"),

    PILE_REQUEST_CHARGING(0x31, "电桩主动申请启动充电(充电桩->运营平台)"),
    PILE_REQUEST_CHARGING_RETURN(0x32, "运营平台确认启动充电(运营平台->充电桩)"),

    REMOTE_START_CHARGING(0x34, "运营平台远程控制启机（充电）(运营平台->充电桩)"),
    REMOTE_START_CHARGING_RETURN(0x33, "远程启机(充电)命令回复(充电桩->运营平台)"),

    REMOTE_STOP_CHARGING(0x36, "运营平台远程停机（停止充电）(运营平台->充电桩)"),
    REMOTE_STOP_CHARGING_RETURN(0x35, "远程停机（停止充电）命令回复(充电桩->运营平台)"),

    TRADING_RECORD(0x3B, "交易记录(充电桩->运营平台)"),
    TRADING_RECORD_RETURN(0x40, "交易记录确认(运营平台->充电桩)"),

    REMOTE_BALANCE_UPDATE(0x42, "远程账户余额更新(运营平台->充电桩)"),
    REMOTE_BALANCE_UPDATE_RETURN(0x41, "余额更新应答(充电桩->运营平台)"),

    PILE_WORK_MODEL_CONFIG(0x52, "充电桩工作参数设置(运营平台->充电桩)"),
    PILE_WORK_MODEL_CONFIG_RETURN(0x51, "电桩工作参数设置应答(充电桩->运营平台)"),

    PILE_TIME_SYNC(0x56, "对时设置(运营平台->充电桩)"),
    PILE_TIME_SYNC_RETURN(0x55, "对时设置应答(充电桩->运营平台)"),

    PILE_BILL_MODEL_CONFIG(0x58, "计费模型设置(运营平台->充电桩)"),
    PILE_BILL_MODEL_CONFIG_RETURN(0x57, "计费模型应答(充电桩->运营平台)"),

    PILE_LOCK_REALTIME_DATA(0x61, "地锁数据上送(充电桩->运营平台)"),

    PILE_REBOOT(0x92, "远程重启(运营平台->充电桩)"),
    PILE_REBOOT_RETURN(0x91, "远程重启应答(充电桩->运营平台)"),

    PILE_REMOTE_UPDATE(0x94, "远程更新(运营平台->充电桩)"),
    PILE_REMOTE_UPDATE_RETURN(0x93, "远程更新应答(充电桩->运营平台)"),

    PILE_PREBOOK_GET(0xB2, "运营平台预约充电获取包(运营平台->充电桩)"),
    PILE_PREBOOK_GET_RETURN(0xB1, "充电桩设备预约充电信息应答(充电桩->运营平台)"),

    PILE_PREBOOK_SEND(0xB4, "运营平台预约充电信息下发给充电桩(运营平台->充电桩)"),
    PILE_PREBOOK_SEND_RETURN(0xB3, "充电桩设置预约充电信息应答(充电桩->运营平台)"),

    OTA_UPGRADE_OF_PILE_RESPONSE_PLATFORM(0xE3, "充电桩应答平台OTA升级请求(充电桩->运营平台)"),
    PLATFORM_REQUESTS_OTA_UPGRADE(0xE4, "平台下发OTA升级请求(运营平台->充电桩)"),

    PLATFORM_RESPONDS_TO_BIN_PACKET_DATA(0xE6, "平台应答充电桩BIN包请求(运营平台->充电桩)"),
    PILE_REQUESTS_BIN_PACKET_DATA(0xE5, "充电桩请求读取BIN包(充电桩->运营平台)");

    ;

    private final Integer code;
    private final String message;
}
