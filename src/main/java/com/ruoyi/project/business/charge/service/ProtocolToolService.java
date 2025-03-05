package com.ruoyi.project.business.charge.service;

import com.ruoyi.project.business.charge.domain.ChargeFrameVo;

import java.util.Map;

public interface ProtocolToolService {
    Map<String,Object> parse(Integer protocol, Integer frame, String data);
}
