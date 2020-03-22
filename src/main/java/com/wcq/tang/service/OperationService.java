package com.wcq.tang.service;

import com.wcq.tang.model.User;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/10 13:56
 */
public interface OperationService {
    void insertOperation(User user, String content,Integer kind);
    int deleteAll();
}
