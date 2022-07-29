package com.ccut.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccut.main.bean.actor;
import com.ccut.main.mapper.actorMapper;
import com.ccut.main.service.actorService;
import org.springframework.stereotype.Service;

@Service
public class actorServiceImpl  extends ServiceImpl<actorMapper, actor> implements actorService  {
}
