package com.wcq.tang.service;

import com.github.pagehelper.Page;
import com.wcq.tang.dto.CleanDto;
import com.wcq.tang.dto.TableCleanDto;
import com.wcq.tang.model.Original;
import com.wcq.tang.model.Todayop;
import com.wcq.tang.model.User;

import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/15 14:10
 */
public interface CleanService {
    Page<Original> getWillCleanDate(User user,Integer limit,Integer page);
    List<TableCleanDto> originalToTableCleanDto(List<Original> originalList);
    CleanDto yuLan(Long originalId);

    CleanDto doClean(CleanDto cleanDto);

    CleanDto saveCleanResult(CleanDto cleanDto,User user);

    void deleteClean(Long originalId);

    Page<Todayop> getNowCleanData(User user, Integer opkind,Integer page,Integer limit);

    Todayop[] MoPao(List<Todayop> todayopList);
}
