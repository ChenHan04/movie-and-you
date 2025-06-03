package com.hanchen.service;

import com.hanchen.entity.Moment;

import java.util.List;

public interface MomentService {
    int publishMoment(Moment moment);
    List<Moment> getMomentsByUserId(Long userId);
}