package com.ruoyi.project.business.charge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ChargeFrameVo
 * @Description: 充电帧数据
 * @Author: zhangwk
 * @Date: 2025-03-05 17:31
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
public class ChargeFrameVo implements Serializable {

    private String key;

    private String value;
}
