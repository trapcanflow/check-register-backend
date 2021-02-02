package com.njs.check.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface OptionsService {
    Map getOptionsBeyondList();

    Map getOptionsFundsList();

    Map getOptionsReasonList();

    Map getOptionsTransportList();

    Map getDepHeaderList(Integer userId);

    Map getLeaderList(Integer userId);
}
