
package com.example.springsecurity.service.impl;

import com.example.springsecurity.service.OcrService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:macd@zjport.gov.cn">macd</a>
 * @version 2.0
 * @since 2.0
 */
@Service
public class IdCardOcrServiceImpl implements OcrService {

    @Override
    public String execute(String ocrType) {
        return ocrType + "execute";
    }

    @Override
    public String ocrType() {
        return "idCardOcr";
    }
}
