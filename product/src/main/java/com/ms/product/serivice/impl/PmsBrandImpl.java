package com.ms.product.serivice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.product.domain.PmsBrand;
import com.ms.product.mapper.PmsBrandMapper;
import com.ms.product.serivice.IPmsBrand;
import org.springframework.stereotype.Service;

@Service
public class PmsBrandImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements IPmsBrand {
}
