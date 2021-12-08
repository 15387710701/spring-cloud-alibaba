package com.ms.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.commons.domain.UmsMember;
import com.ms.user.mapper.UmsMemberMapper;
import com.ms.user.service.IUmsMember;
import org.springframework.stereotype.Service;

@Service
public class UmsMemberImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements IUmsMember {
}
