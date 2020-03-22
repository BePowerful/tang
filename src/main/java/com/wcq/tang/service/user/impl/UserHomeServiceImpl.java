package com.wcq.tang.service.user.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wcq.tang.mapper.CorpusMapper;
import com.wcq.tang.mapper.OriginalMapper;
import com.wcq.tang.mapper.ThreecupMapper;
import com.wcq.tang.model.*;
import com.wcq.tang.service.user.UserHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/3 16:16
 */
@Service
public class UserHomeServiceImpl implements UserHomeService {
    @Autowired
    private CorpusMapper corpusMapper;
    @Autowired
    private OriginalMapper originalMapper;
    @Autowired
    private ThreecupMapper threecupMapper;
    @Override
    public Page<Corpus> getUserCorpus(User user, Integer page, Integer limit) {
        Page<Corpus> corpusPage = PageHelper.startPage(page,limit);
        CorpusExample example = new CorpusExample();
        CorpusExample.Criteria criteria = example.createCriteria();
        criteria.andUploaderEqualTo(user.getUserId());
        criteria.andParticipledEqualTo(true);
        corpusMapper.selectByExample(example);
        return corpusPage;
    }

    @Override
    public Page<Original> getUserOriginals(User user,Integer page,Integer limit) {
        Page<Original> originalPage = PageHelper.startPage(page,limit);
        OriginalExample example = new OriginalExample();
        OriginalExample.Criteria criteria = example.createCriteria();
        criteria.andUploaderEqualTo(user.getUserId());
        originalMapper.selectByExample(example);
        return originalPage;
    }
    @Override
    public Page<Threecup> getUserThreecups(User user,Integer page,Integer limit) {
        Page<Threecup> threecupPage = PageHelper.startPage(page, limit);
        ThreecupExample example = new ThreecupExample();
        ThreecupExample.Criteria criteria = example.createCriteria();
        criteria.andUploaderEqualTo(user.getUserId());
        threecupMapper.selectByExample(example);
        return threecupPage;
    }
}
