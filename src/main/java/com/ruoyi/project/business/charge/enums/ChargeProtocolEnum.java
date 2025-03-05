package com.ruoyi.project.business.charge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: ProtocolEnum
 * @Description: 充电协议枚举类
 * @Author: zhangwk
 * @Date: 2025-03-05 17:38
 * @Version: 1.0
 **/
@Getter
@AllArgsConstructor
public enum ChargeProtocolEnum {

    MIAO_CHONG_CHONG(1, "喵充充");

    private final Integer code;
    private final String message;
}
