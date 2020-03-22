package com.wcq.tang.service;

import com.github.pagehelper.Page;
import com.wcq.tang.dto.TableParDto;
import com.wcq.tang.model.Corpus;
import com.wcq.tang.model.Threecup;
import com.wcq.tang.model.Todaythree;
import com.wcq.tang.model.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/16 12:01
 */
public interface ParService {
    Page<Corpus> getWillParData(User user,Integer page,Integer limit);
    List<TableParDto> corpusToTableParDto(List<Corpus> willParData);
    TableParDto yuLan(Long corpusId);
    void deletePar(Long corpusId);
    List<Todaythree> getNowThreeData(User user);

    Todaythree[] threeMoPao(List<Todaythree> todaythreeList);
    void deleteThree(Long threeId);

    void updateThree(Threecup threecup);

    TableParDto doPar(TableParDto tableParDto);

    TableParDto saveResult(TableParDto tableParDto,User user);

    int addThreecup(Threecup threecup,User user);

    List<Threecup> tiquThreecup(String parResult);
}
