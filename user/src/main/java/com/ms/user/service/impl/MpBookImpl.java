package com.ms.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.user.domain.MpBook;
import com.ms.user.mapper.MpBookMapper;
import com.ms.user.service.IMpBook;
import org.springframework.stereotype.Service;

/**
 * @author:SmartV
 * @date:2021/10/11 18:21
 */
@Service
public class MpBookImpl extends ServiceImpl<MpBookMapper, MpBook> implements IMpBook {
}
