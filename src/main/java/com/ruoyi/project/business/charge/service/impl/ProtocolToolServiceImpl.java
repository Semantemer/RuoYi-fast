package com.ruoyi.project.business.charge.service.impl;

import com.ruoyi.project.business.charge.domain.ChargeFrameVo;
import com.ruoyi.project.business.charge.enums.ChargeProtocolEnum;
import com.ruoyi.project.business.charge.helper.MiaochongchongProtocolToolHelper;
import com.ruoyi.project.business.charge.service.ProtocolToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: ProtocolToolServiceImpl
 * @Description:
 * @Author: zhangwk
 * @Date: 2025-03-05 17:33
 * @Version: 1.0
 **/
@Service
public class ProtocolToolServiceImpl implements ProtocolToolService {

    @Autowired
    private MiaochongchongProtocolToolHelper miaochongchongProtocolToolHelper;

    @Override
    public Map<String,Object> parse(Integer protocol, Integer frame, String data) {

        if(ChargeProtocolEnum.MIAO_CHONG_CHONG.getCode().equals(protocol)){
            return miaochongchongProtocolToolHelper.parse(frame, data);
        }

        return null;
    }
}
