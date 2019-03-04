package com.boot.security.server.service;

import java.util.List;
import java.util.Map;

public interface KjscService {
    List<Map<String, Object>> getResultInfo(String tableName, String tableName2);

    List<Map<String, Object>> getProductInfo(String tableName, String tableName2);

    List<Map<String, Object>> getZlsuperInfo(String tableName, String tableName2);
}
