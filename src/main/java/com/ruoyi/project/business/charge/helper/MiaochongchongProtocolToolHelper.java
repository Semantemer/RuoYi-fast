package com.ruoyi.project.business.charge.helper;

import com.ruoyi.project.business.charge.domain.ChargeFrameVo;
import com.ruoyi.project.business.charge.enums.ChargeFrameEnum;
import com.ruoyi.project.business.charge.utils.CP56Time2aUtil;
import com.ruoyi.project.business.charge.utils.DateUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @ClassName: MiaochongchongProtocolToolHelper
 * @Description:
 * @Author: zhangwk
 * @Date: 2025-03-05 17:45
 * @Version: 1.0
 **/
@Component
public class MiaochongchongProtocolToolHelper {
    public List<ChargeFrameVo> parse(Integer frame, String data) {

        Integer frameType = Integer.parseInt(data.substring(11, 12), 16);

        if(ChargeFrameEnum.TRADING_RECORD.getCode().equals(frameType)){
            return parseTradingRecord(data);
        }

        return null;
    }

    /**
     * 交易记录
     * 充电桩->运营平台
     */
    private List<ChargeFrameVo> parseTradingRecord(String data) {

        // 68a2770600
        // 3b
        // 00124120001903012501140540370001 : 流水号
        // 00124120001903 : 桩编号
        // 01 : 枪编号
        // 20cb3417ed0119 : 开始充电时间
        // 78e618072e0119 : 结束充电时间
        // d0070000 : 尖单价
        // 00000000 : 尖电量
        // 00000000 : 计损尖电量
        // 00000000 : 尖金额
        // d0070000 : 峰单价
        // 00000000 : 峰电量
        // 00000000 : 计损峰电量
        // 00000000 : 峰金额
        // d0070000 : 平单价
        // 00000000 : 平电量
        // 00000000 : 计损平电量
        // 00000000 : 平金额
        // d0070000 : 谷单价
        // 00000000 : 谷电量
        // 00000000 : 计损谷电量
        // 00000000 : 谷金额
        // 0000000000 : 电表总起值
        // 0000000000 : 电表总止值
        // 00000000 : 总电量
        // 00000000 : 计损总电量
        // 00000000 : 消费金额
        // 0000000000000000000000000000000000 : 电动汽车唯一标识
        // 09 : 交易标识
        // 000019072e0119 : 交易日期、时
        // 4a : 停止原因
        // 7805af3703000000
        // d303

        List<ChargeFrameVo> result = new ArrayList<>();
        result.add(new ChargeFrameVo("帧类型", "交易记录"));

        result.add(new ChargeFrameVo("交易流水号", data.substring(12, 44)));
        result.add(new ChargeFrameVo("桩编号", data.substring(44, 58)));
        result.add(new ChargeFrameVo("枪编号", data.substring(58, 60)));

        result.add(new ChargeFrameVo("开始充电时间",DateUtil.localDateTime2Str(CP56Time2aUtil.parseCP56Time2a(data.substring(60, 74)))));
        result.add(new ChargeFrameVo("结束充电时间",DateUtil.localDateTime2Str(CP56Time2aUtil.parseCP56Time2a(data.substring(74, 88)))));

        return result;
    }



    public static void main(String[] args) {


        // 输入的16进制字符串
        String hexString = "20cb3417ed0119";
        System.out.println("输入的16进制字符串: " + hexString);

    }

}
