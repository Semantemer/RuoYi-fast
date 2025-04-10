package com.ruoyi.project.business.charge.helper;

import com.ruoyi.project.business.charge.domain.ChargeFrameVo;
import com.ruoyi.project.business.charge.enums.ChargeFrameEnum;
import com.ruoyi.project.business.charge.utils.CP56Time2aUtil;
import com.ruoyi.project.business.charge.utils.FrameParseUtil;
import com.ruoyi.project.business.charge.utils.DateUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
    public List<ChargeFrameVo> parse(String data) {

        Integer frameType = Integer.parseInt(data.substring(10, 12), 16);

        if(ChargeFrameEnum.PILE_AUTH.getCode().equals(frameType)){
            return parsePileAuth(data);
        }else if(ChargeFrameEnum.PILE_KEEP_ALIVE.getCode().equals(frameType)){
            return parsePileKeepalive(data);
        }else if(ChargeFrameEnum.PILE_BILLING_MODEL_CHECK.getCode().equals(frameType)){
            return parsePileBillingModelCheck(data);
        }else if(ChargeFrameEnum.PILE_BILLING_MODEL_GET.getCode().equals(frameType)){
            return parsePileBillingModelGet(data);
        }else if(ChargeFrameEnum.PILE_CHARGING_LINK.getCode().equals(frameType)){
            return parsePileChargingLink(data);
        }else if(ChargeFrameEnum.PILE_BMS_CONFIG.getCode().equals(frameType)){
            return parsePileBmsConfig(data);
        }else if(ChargeFrameEnum.PILE_CHARING_FINISH.getCode().equals(frameType)){
            return parsePileCharingFinish(data);
        }else if(ChargeFrameEnum.TRADING_RECORD.getCode().equals(frameType)){
            return parseTradingRecord(data);
        }else if(ChargeFrameEnum.PILE_REALTIME_DATA.getCode().equals(frameType)){
            return parsePileRealtimeData(data);
        }

        return null;
    }

    private List<ChargeFrameVo> parsePileCharingFinish(String data) {
        List<ChargeFrameVo> result = new ArrayList<>();
        result.add(new ChargeFrameVo("帧类型", ChargeFrameEnum.PILE_CHARING_FINISH.getMessage()));
        result.add(new ChargeFrameVo("桩编号", data.substring(12, 26)));

        return result;
    }

    private List<ChargeFrameVo> parsePileBmsConfig(String data) {
        List<ChargeFrameVo> result = new ArrayList<>();
        result.add(new ChargeFrameVo("帧类型", ChargeFrameEnum.PILE_BMS_CONFIG.getMessage()));
        result.add(new ChargeFrameVo("桩编号", data.substring(12, 26)));

        return result;
    }

    private List<ChargeFrameVo> parsePileChargingLink(String data) {

        List<ChargeFrameVo> result = new ArrayList<>();
        result.add(new ChargeFrameVo("帧类型", ChargeFrameEnum.PILE_CHARGING_LINK.getMessage()));
        result.add(new ChargeFrameVo("桩编号", data.substring(12, 26)));

        return result;
    }

    /**
     * 计费模型验证请求(充电桩->运营平台)
     *
     * @param data
     * @return
     * 680b0200000900624100001201341b
     * 685E0002000A00624100001201F002E8030000E8030000E8030000E8030000E8030000E8030000E8030000E803000000000000000000000000000000000001010101010101010101010101010101010101010202020202020202020203030303BF08
     */
    private List<ChargeFrameVo> parsePileBillingModelGet(String data) {

        // 680b020000
        // 09
        // 00624100001201
        // 341b

        List<ChargeFrameVo> result = new ArrayList<>();
        result.add(new ChargeFrameVo("帧类型", ChargeFrameEnum.PILE_BILLING_MODEL_GET.getMessage()));
        result.add(new ChargeFrameVo("桩编号", data.substring(12, 26)));

        return result;
    }

    private List<ChargeFrameVo> parsePileBillingModelCheck(String data) {

        List<ChargeFrameVo> result = new ArrayList<>();
        result.add(new ChargeFrameVo("帧类型", ChargeFrameEnum.PILE_BILLING_MODEL_CHECK.getMessage()));

        return result;
    }

    /**
     * 充电桩心跳包(充电桩->运营平台)
     * @param data
     * @return
     * 680d0300000300624100001201010075fa
     * 680D00030004006241000012010100C5E7
     */
    private List<ChargeFrameVo> parsePileKeepalive(String data) {

        // 680d030000
        // 03
        // 00624100001201 : 桩编码
        // 01 : 枪号
        // 00 : 枪状态(0x00：正常 0x01：故障)
        // 75fa

        List<ChargeFrameVo> result = new ArrayList<>();
        result.add(new ChargeFrameVo("帧类型", ChargeFrameEnum.PILE_KEEP_ALIVE.getMessage()));

        result.add(new ChargeFrameVo("桩编号", data.substring(12, 26)));
        result.add(new ChargeFrameVo("枪号", data.substring(26, 28)));
        result.add(new ChargeFrameVo("枪状态(0x00：正常 0x01：故障)", data.substring(28, 30)));

        return result;
    }

    /**
     *  充电桩登录认证(充电桩->运营平台)
     * @param data
     * @return
     * 682200000001006241000012010101103431325f342e353000898604861024c0006426003abc
     * 680C0000000200624100001201009020
     */
    private List<ChargeFrameVo> parsePileAuth(String data) {

        // 6822000000
        // 01
        // 00624100001201 : 桩编号
        // 01 : 桩类型（0表示直流桩， 1表示交流桩）
        // 01 : 枪编号
        // 10 ： 通信协议版本（版本号乘 10，v1.0表示0x0A）
        // 3431325f342e3530 ： 程序版本
        // 00 ： 网络链接类型（0x00 SIM卡 0x01 LAN 0x02 WAN 0x03 其他）
        // 898604861024c0006426 : Sim卡
        // 00 : 运营商（0x00 移动 0x02 电信 0x03 联通 0x04 其他）
        // 3abc

        // 680C0000000200624100001201009020

        List<ChargeFrameVo> result = new ArrayList<>();
        result.add(new ChargeFrameVo("帧类型", ChargeFrameEnum.PILE_AUTH.getMessage()));

        result.add(new ChargeFrameVo("桩编号", data.substring(12, 26)));
        result.add(new ChargeFrameVo("桩类型（0表示直流桩， 1表示交流桩）", data.substring(26, 28)));
        result.add(new ChargeFrameVo("枪数量", FrameParseUtil.bytesParseFor1(data.substring(28, 30)).toString()));

        BigDecimal versionBigDecimal = FrameParseUtil.bytesParseFor1(data.substring(30, 32)).divide(new BigDecimal(10)).setScale(1, BigDecimal.ROUND_HALF_UP);
        result.add(new ChargeFrameVo("通信协议版本（版本号乘 10，v1.0表示0x0A）", versionBigDecimal.toString()));
        result.add(new ChargeFrameVo("程序版本", data.substring(32, 48)));
        result.add(new ChargeFrameVo("网络链接类型（0x00 SIM卡 0x01 LAN 0x02 WAN 0x03 其他）", data.substring(48, 50)));

        result.add(new ChargeFrameVo("Sim卡", data.substring(50, 70)));
        result.add(new ChargeFrameVo("运营商（0x00 移动 0x02 电信 0x03 联通 0x04 其他）", data.substring(70, 72)));

        return result;
    }

    private List<ChargeFrameVo> parsePileRealtimeData(String data) {
        // 6840216f00
        // 13
        // 00725020006901012503202328050001 : 流水号
        // 00725020006901 : 桩编号
        // 01 : 枪编号
        // 03 : 状态（0x00：离线 0x01：故障 0x02：空闲 0x03：充电）
        // 02 : 枪是否归位（0x00否 0x01是 0x02未知）
        // 01 : 是否插枪（0x00 否 0x01是）
        // 5f08 : 输出电压
        // 3e01 : 输出电流
        // 59 : 枪线温度
        // 0000000000000000 : 枪线编码
        // 00 : SOC（待机置零；交流桩置零）
        // 00 : 电池组最高温度
        // 2800 : 累计充电时间
        // 0000 : 剩余时间
        // 8fb40000 : 充电度数
        // 8fb40000 : 计损充电度数
        // 8fb40000 : 已充金额
        // 0000 : 硬件故障
        // e494

        List<ChargeFrameVo> result = new ArrayList<>();
        result.add(new ChargeFrameVo("帧类型", ChargeFrameEnum.PILE_REALTIME_DATA.getMessage()));

        result.add(new ChargeFrameVo("交易流水号", data.substring(12, 44)));
        result.add(new ChargeFrameVo("桩编号", data.substring(44, 58)));
        result.add(new ChargeFrameVo("枪编号", data.substring(58, 60)));
        result.add(new ChargeFrameVo("状态（0x00：离线 0x01：故障 0x02：空闲 0x03：充电）", data.substring(60, 62)));
        result.add(new ChargeFrameVo("枪是否归位（0x00否 0x01是 0x02未知）", data.substring(62, 64)));
        result.add(new ChargeFrameVo("是否插枪（0x00 否 0x01是）", data.substring(64, 66)));

        result.add(new ChargeFrameVo("输出电压", FrameParseUtil.bytesParseFor2(data.substring(66, 70))));
        result.add(new ChargeFrameVo("输出电流", FrameParseUtil.bytesParseFor2(data.substring(70, 74))));
        BigDecimal bigDecimal = FrameParseUtil.bytesParseFor1(data.substring(74, 76));
        result.add(new ChargeFrameVo("枪线温度", BigDecimal.ZERO.compareTo(bigDecimal) == 0 ? "0" : bigDecimal.subtract(new BigDecimal(50)).toString()));
        result.add(new ChargeFrameVo("枪线编码", data.substring(76, 92)));

        result.add(new ChargeFrameVo("SOC（待机置零；交流桩置零）", data.substring(92, 94)));
        BigDecimal bigDecimal1 = FrameParseUtil.bytesParseFor1(data.substring(94, 96));
        result.add(new ChargeFrameVo("电池组最高温度（待机置零；交流桩置零）", BigDecimal.ZERO.compareTo(bigDecimal1) == 0 ? "0" : bigDecimal1.subtract(new BigDecimal(50)).toString()));

        result.add(new ChargeFrameVo("累计充电时间", FrameParseUtil.bytesParseFor2v2(data.substring(96, 100))));
        result.add(new ChargeFrameVo("剩余时间", data.substring(100, 104)));
        result.add(new ChargeFrameVo("充电度数", FrameParseUtil.bytesParseFor4(data.substring(104, 112))));
        result.add(new ChargeFrameVo("计损充电度数", FrameParseUtil.bytesParseFor4(data.substring(112, 120))));
        result.add(new ChargeFrameVo("已充金额", data.substring(120, 128)));
        result.add(new ChargeFrameVo("硬件故障", data.substring(128, 132)));

        return result;
    }

    public static void main(String[] args) {

        String data = "6840216f00130072502000690101250320232805000100725020006901010302015f083e015900000000000000000000280000008fb400008fb400008fb400000000e494";

        MiaochongchongProtocolToolHelper miaochongchongProtocolToolHelper = new MiaochongchongProtocolToolHelper();

        List<ChargeFrameVo> chargeFrameVos = miaochongchongProtocolToolHelper.parsePileRealtimeData(data);

        System.out.println(chargeFrameVos);
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
        result.add(new ChargeFrameVo("帧类型", ChargeFrameEnum.TRADING_RECORD.getMessage()));

        result.add(new ChargeFrameVo("交易流水号", data.substring(12, 44)));
        result.add(new ChargeFrameVo("桩编号", data.substring(44, 58)));
        result.add(new ChargeFrameVo("枪编号", data.substring(58, 60)));

        result.add(new ChargeFrameVo("开始充电时间",DateUtil.localDateTime2Str(CP56Time2aUtil.parseCP56Time2a(data.substring(60, 74)))));
        result.add(new ChargeFrameVo("结束充电时间",DateUtil.localDateTime2Str(CP56Time2aUtil.parseCP56Time2a(data.substring(74, 88)))));

        result.add(new ChargeFrameVo("尖单价", data.substring(88, 96)));
        result.add(new ChargeFrameVo("尖电量", data.substring(96, 104)));
        result.add(new ChargeFrameVo("计损尖电量", data.substring(104, 112)));
        result.add(new ChargeFrameVo("尖金额", data.substring(112, 120)));

        result.add(new ChargeFrameVo("峰单价", data.substring(120, 128)));
        result.add(new ChargeFrameVo("峰电量", data.substring(128, 136)));
        result.add(new ChargeFrameVo("计损峰电量", data.substring(136, 144)));
        result.add(new ChargeFrameVo("峰金额", data.substring(144, 152)));

        result.add(new ChargeFrameVo("平单价", data.substring(152, 160)));
        result.add(new ChargeFrameVo("平电量", data.substring(160, 168)));
        result.add(new ChargeFrameVo("计损平电量", data.substring(168, 176)));
        result.add(new ChargeFrameVo("平金额", data.substring(176, 184)));

        result.add(new ChargeFrameVo("谷单价", data.substring(184, 192)));
        result.add(new ChargeFrameVo("谷电量", data.substring(192, 200)));
        result.add(new ChargeFrameVo("计损谷电量", data.substring(200, 208)));
        result.add(new ChargeFrameVo("谷金额", data.substring(208, 218)));

        result.add(new ChargeFrameVo("电表总起值", data.substring(218, 228)));
        result.add(new ChargeFrameVo("电表总止值", data.substring(228, 236)));

        result.add(new ChargeFrameVo("总电量", FrameParseUtil.bytesParseFor4(data.substring(236, 244))));
        result.add(new ChargeFrameVo("计损总电量", FrameParseUtil.bytesParseFor4(data.substring(244, 252))));
        result.add(new ChargeFrameVo("消费金额", FrameParseUtil.bytesParseFor4(data.substring(252, 260))));

        result.add(new ChargeFrameVo("电动汽车唯一标识", data.substring(260, 294)));
        result.add(new ChargeFrameVo("交易标识", data.substring(294, 296)));
        result.add(new ChargeFrameVo("交易日期、时", DateUtil.localDateTime2Str(CP56Time2aUtil.parseCP56Time2a(data.substring(296, 310)))));
        result.add(new ChargeFrameVo("停止原因", data.substring(310, 312)));

        return result;
    }

}
