package com.wcq.tang.service.impl;

import com.wcq.tang.mapper.IndexmsgMapper;
import com.wcq.tang.model.Indexmsg;
import com.wcq.tang.model.IndexmsgExample;
import com.wcq.tang.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/4 13:22
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexmsgMapper indexmsgMapper;
    @Override
    public List<Indexmsg> getIndexmsg() {
        List<Indexmsg> indexmsgList = indexmsgMapper.selectByExample(new IndexmsgExample());
        return indexmsgList;
    }
}
