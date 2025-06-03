package com.hanchen.service.impl;

import com.hanchen.entity.Moment;
import com.hanchen.mapper.MomentMapper;
import com.hanchen.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MomentServiceImpl implements MomentService {
    @Autowired
    private MomentMapper momentMapper;

    @Override
    public int publishMoment(Moment moment) {
        return momentMapper.insert(moment);
    }

    @Override
    public List<Moment> getMomentsByUserId(Long userId) {
        return momentMapper.selectByUserId(userId);
    }
}