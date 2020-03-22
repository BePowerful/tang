package com.wcq.tang.service;

import com.github.pagehelper.Page;
import com.wcq.tang.model.*;

import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/16 13:17
 */
public interface MyOpService {
    Page<Todayop> getTodayOp(User user, Integer opKind, Integer page, Integer limit);
    Todayop[]  MoPao(List<Todayop> list);
    int deleteTodayOp(Long id,Integer opKind);
    int updateTodayOp(Todayop todayop);
    int updateTodayNoOpId(Todayop todayop);
    Todayop insertTodayOp(Todayop todayop);
    List<Todayop> getTodayopByExample(TodayopExample example);
    Todayop getTodayopByOpId(Integer opId);
    //三元组的
    List<Todaythree> getTodaythree(User user);
    Todaythree insertTodaythree(Todaythree todaythree);
    int updateTodaythree(Todaythree todaythree);
    List<Todaythree> getTodaythreeByExample(Long threeId);
    Todaythree[]  ThreMoPao(List<Todaythree> list);

    void deleteThree(Long threeId);

    boolean hasTodayOp(Long id,Integer opKind);

    int deleteall();
}
