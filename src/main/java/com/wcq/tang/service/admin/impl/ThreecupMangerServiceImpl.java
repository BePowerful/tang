package com.wcq.tang.service.admin.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wcq.tang.bean.Utils;
import com.wcq.tang.dto.ThreecupDto;
import com.wcq.tang.mapper.ThreecupMapper;
import com.wcq.tang.mapper.UserMapper;
import com.wcq.tang.model.Threecup;
import com.wcq.tang.model.ThreecupExample;
import com.wcq.tang.service.admin.ThreecupMangerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/12 12:41
 */
@Service
public class ThreecupMangerServiceImpl implements ThreecupMangerService {
    @Autowired
    private ThreecupMapper threecupMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Page<Threecup> defaultValue(Integer page,Integer limit) {
        Page<Threecup> threecupPage = PageHelper.startPage(page, limit);
        threecupMapper.selectByExample(new ThreecupExample());
        return threecupPage;
    }

    @Override
    public Page<Threecup> getThreecupBySubject(String subject,Integer page,Integer limit) {
        Page<Threecup> threecupPage = PageHelper.startPage(page, limit);
        ThreecupExample example = new ThreecupExample();
        ThreecupExample.Criteria criteria = example.createCriteria();
        criteria.andSubjectLike("%"+subject+"%");
        threecupMapper.selectByExample(example);
        return threecupPage;
    }

    @Override
    public Page<Threecup> getThreecupByPredicate(String predicate,Integer page,Integer limit) {
        Page<Threecup> threecupPage = PageHelper.startPage(page, limit);
        ThreecupExample example = new ThreecupExample();
        ThreecupExample.Criteria criteria = example.createCriteria();
        criteria.andPredicateLike("%"+predicate+"%");
        threecupMapper.selectByExample(example);
        return threecupPage;
    }

    @Override
    public Page<Threecup> getThreecupByObject(String object,Integer page,Integer limit) {
        Page<Threecup> threecupPage = PageHelper.startPage(page, limit);
        ThreecupExample example = new ThreecupExample();
        ThreecupExample.Criteria criteria = example.createCriteria();
        criteria.andObjectLike("%"+object+"%");
        threecupMapper.selectByExample(example);
        return threecupPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteThreecup(Threecup threecup) {
        threecupMapper.deleteByPrimaryKey(threecup.getThreeId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatethreecup(Threecup threecup) {
        return threecupMapper.updateByPrimaryKeySelective(threecup);
    }

    @Override
    public List<ThreecupDto> threecupListTothreecupDtoList(Page<Threecup> threecupPage) {
        List<ThreecupDto> dtoList = new LinkedList<>();
        List<Threecup> result = threecupPage.getResult();
        for (Threecup thr:result
             ) {
            ThreecupDto dto = new ThreecupDto();
            BeanUtils.copyProperties(thr,dto);
            dto.setUserName(userMapper.selectByPrimaryKey(dto.getUploader()).getUserName());
            dto.setStrDate(Utils.dateToString("yyyy-MM-dd",dto.getBuildTime()));
            dtoList.add(dto);
        }
        return dtoList;
    }

}
