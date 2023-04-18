
package com.example.springsecurity.service.impl;

import com.example.springsecurity.service.OcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:macd@163.com">macd</a>
 * @version 2.0
 * @since 2.0
 */
@Component
public class FactoryOcrStrategy {

    @Autowired
    private Map<String, OcrService> ocrServiceMap = new HashMap<>();

    public OcrService getType(String ocrType) {
        for (OcrService value : ocrServiceMap.values()) {
            if (value.ocrType().equals(ocrType)) {
                return value;
            }
        }
        return null;
    }
}
