package com.wcq.tang.service.impl;

import com.wcq.tang.mapper.OperationMapper;
import com.wcq.tang.model.Operation;
import com.wcq.tang.model.OperationExample;
import com.wcq.tang.model.User;
import com.wcq.tang.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/10 13:57
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class OperationServiceImpl implements OperationService {
    @Autowired
    private OperationMapper operationMapper;
    @Override
    public void insertOperation(User user, String content, Integer kind) {
        String str = user.getUserName()+"用户,"+content;
        Operation operation = new Operation();
        operation.setOperationContent(str);
        operation.setOperationDate(new Date());
        operation.setOperationKind(kind);
        operationMapper.insertSelective(operation);
    }

    @Override
    public int deleteAll() {
        int i = operationMapper.deleteByExample(new OperationExample());
        return i;
    }
}
