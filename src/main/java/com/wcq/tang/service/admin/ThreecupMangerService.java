package com.wcq.tang.service.admin;

import com.github.pagehelper.Page;
import com.wcq.tang.dto.ThreecupDto;
import com.wcq.tang.model.Threecup;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/12 12:41
 */
public interface ThreecupMangerService {
    @Cacheable(cacheNames = "dsd",key = "#root.methodName")
    Page<Threecup> defaultValue(Integer page,Integer limit);

    @Cacheable(cacheNames = "thra",key = "#subject")
    Page<Threecup> getThreecupBySubject(String subject,Integer page,Integer limit);

    @Cacheable(cacheNames = "thra",key = "#predicate")
    Page<Threecup> getThreecupByPredicate(String predicate,Integer page,Integer limit);

    @Cacheable(cacheNames = "thra",key = "#object")
    Page<Threecup> getThreecupByObject(String object,Integer page,Integer limit);

    void deleteThreecup(Threecup threecup);

    int updatethreecup(Threecup threecup);
    List<ThreecupDto> threecupListTothreecupDtoList(Page<Threecup> threecupPage);
}
