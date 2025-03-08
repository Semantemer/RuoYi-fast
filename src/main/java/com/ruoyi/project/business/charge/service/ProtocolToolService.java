package com.ruoyi.project.business.charge.service;

import com.ruoyi.project.business.charge.domain.ChargeFrameVo;

import java.util.List;
import java.util.Map;

public interface ProtocolToolService {

    List<ChargeFrameVo> parse(Integer protocol, String data);
}
