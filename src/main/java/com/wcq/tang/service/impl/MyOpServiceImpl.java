package com.wcq.tang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wcq.tang.mapper.TodayopMapper;
import com.wcq.tang.mapper.TodaythreeMapper;
import com.wcq.tang.model.*;
import com.wcq.tang.service.MyOpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/16 13:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MyOpServiceImpl implements MyOpService {
    @Autowired
    private TodayopMapper todayopMapper;
    @Autowired
    private TodaythreeMapper todaythreeMapper;
    @Override
    public Page<Todayop> getTodayOp(User user, Integer opKind,Integer page,Integer limit) {
        Page<Todayop> resultPage = PageHelper.startPage(page, limit);
        TodayopExample example = new TodayopExample();
        TodayopExample.Criteria criteria = example.createCriteria();
        criteria.andOpUserEqualTo(user.getUserId());
        criteria.andOpKindEqualTo(opKind);
        todayopMapper.selectByExample(example);
        return resultPage;
    }
    @Override
    public Todayop[]  MoPao(List<Todayop> list){
        Todayop[] array1 = new Todayop[list.size()];
        Todayop[] array = list.toArray(array1);
        int i,j;
        for(i=0;i<array.length-1;i++)
        {
            for(j=0;j<array.length-1-i;j++)
            {
                if(array[j].getOpId()<array[j+1].getOpId())
                {
                    Todayop temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
        return array;
    }

    @Override
    public synchronized int deleteTodayOp(Long id, Integer opKind) {
        TodayopExample example = new TodayopExample();
        TodayopExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andOpKindEqualTo(opKind);
        return todayopMapper.deleteByExample(example);
    }

    @Override
    public synchronized int updateTodayOp(Todayop todayop) {
        return todayopMapper.updateByPrimaryKeySelective(todayop);
    }

    @Override
    public synchronized int updateTodayNoOpId(Todayop todayop) {
        TodayopExample example = new TodayopExample();
        TodayopExample.Criteria criteria = example.createCriteria();
        criteria.andOpKindEqualTo(todayop.getOpKind());
        criteria.andIdEqualTo(todayop.getId());
        List<Todayop> todayops = todayopMapper.selectByExample(example);
        todayop.setOpId(todayops.get(0).getOpId());
        return todayopMapper.updateByPrimaryKeySelective(todayop);
    }

    @Override
    public synchronized Todayop insertTodayOp(Todayop todayop) {
        int i = todayopMapper.insertSelective(todayop);
        return todayop;
    }

    @Override
    public List<Todayop> getTodayopByExample(TodayopExample example) {
        return todayopMapper.selectByExample(example);
    }

    @Override
    public Todayop getTodayopByOpId(Integer opId) {
        return todayopMapper.selectByPrimaryKey(opId);
    }

    @Override
    public List<Todaythree> getTodaythree(User user) {
        TodaythreeExample example = new TodaythreeExample();
        TodaythreeExample.Criteria criteria = example.createCriteria();
        criteria.andOpUserEqualTo(user.getUserId());
        return todaythreeMapper.selectByExample(example);
    }

    @Override
    public synchronized Todaythree insertTodaythree(Todaythree todaythree) {
        int i = todaythreeMapper.insertSelective(todaythree);
        return todaythree;
    }

    @Override
    public synchronized int updateTodaythree(Todaythree todaythree) {
        return todaythreeMapper.updateByPrimaryKeySelective(todaythree);
    }

    @Override
    public List<Todaythree> getTodaythreeByExample(Long threeId) {
        TodaythreeExample example = new TodaythreeExample();
        TodaythreeExample.Criteria criteria = example.createCriteria();
        criteria.andThreeIdEqualTo(threeId);
        return todaythreeMapper.selectByExample(example);
    }

    @Override
    public Todaythree[] ThreMoPao(List<Todaythree> list) {
        Todaythree[] array1 = new Todaythree[list.size()];
        Todaythree[] array = list.toArray(array1);
        int i,j;
        for(i=0;i<array.length-1;i++)
        {
            for(j=0;j<array.length-1-i;j++)
            {
                if(array[j].getId()<array[j+1].getId())
                {
                    Todaythree temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
        return array;
    }

    @Override
    public synchronized void deleteThree(Long threeId) {
        TodaythreeExample example = new TodaythreeExample();
        TodaythreeExample.Criteria criteria = example.createCriteria();
        criteria.andThreeIdEqualTo(threeId);
        todaythreeMapper.deleteByExample(example);
    }

    @Override
    public boolean hasTodayOp(Long id,Integer opKind) {
        TodayopExample example = new TodayopExample();
        TodayopExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andOpKindEqualTo(opKind);
        long l = todayopMapper.countByExample(example);
        if(l==0L){
            return false;
        }
        return true;
    }

    @Override
    public int deleteall() {
        int i = todayopMapper.deleteByExample(new TodayopExample());
        int i1 = todaythreeMapper.deleteByExample(new TodaythreeExample());
        return i+i1;
    }
}
