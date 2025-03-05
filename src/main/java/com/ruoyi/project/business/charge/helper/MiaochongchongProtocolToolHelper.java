package com.ruoyi.project.business.charge.helper;

import com.ruoyi.project.business.charge.domain.ChargeFrameVo;
import com.ruoyi.project.business.charge.enums.ChargeFrameEnum;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MiaochongchongProtocolToolHelper
 * @Description:
 * @Author: zhangwk
 * @Date: 2025-03-05 17:45
 * @Version: 1.0
 **/
@Component
public class MiaochongchongProtocolToolHelper {
    public Map<String,Object> parse(Integer frame, String data) {

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
    private Map<String,Object> parseTradingRecord(String data) {

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

        Map<String,Object> result = new HashMap<>();
        result.put("帧类型", "交易记录");

        String orderNo = data.substring(12, 44);
        result.put("交易流水号", orderNo);

        String pileNo = data.substring(44, 58);
        result.put("桩编号", pileNo);

        String gunNo = data.substring(58, 60);
        result.put("枪编号", gunNo);

        String startTime = data.substring(60, 74);
        result.put("开始充电时间", startTime);

        String endTime = data.substring(74, 88);
        result.put("结束充电时间", endTime);

        return result;
    }

    public static void main(String[] args) {


        String data = "68a27706003b00124120001903012501140540370001001241200019030120cb3417ed011978e618072e0119d0070000000000000000000000000000d0070000000000000000000000000000d0070000000000000000000000000000d007000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009000019072e01194a7805af3703000000d303";
        String orderNo = data.substring(12, 44);
        System.out.println(orderNo);

    }

}
