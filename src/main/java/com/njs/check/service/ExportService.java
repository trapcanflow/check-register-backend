package com.njs.check.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public interface ExportService {

    Map getWordUrl(Integer applicationId) throws IOException;
}
