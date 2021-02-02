package com.njs.check.service;

import com.njs.check.common.ServerResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {
    Map login(String identityId);
}
