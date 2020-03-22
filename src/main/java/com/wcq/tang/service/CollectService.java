package com.wcq.tang.service;

import com.github.pagehelper.Page;
import com.wcq.tang.bean.JsonResult;
import com.wcq.tang.dto.TodayCollectDto;
import com.wcq.tang.model.Original;
import com.wcq.tang.model.Todayop;
import com.wcq.tang.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/3 16:14
 */
public interface CollectService {
    Original upload(String title, String tags, String source, MultipartFile file, User user) throws IOException;
    Original collect(String title,String tags, String source, String content,User user);

    Page<Todayop> getTodayOp(User user,Integer page,Integer limit);

    TodayCollectDto[] getTodayCollectDto(Todayop[] todayopList);

    Todayop[] MaoPao(List<Todayop> todayopList);

    void deleteOriginal(Long originalId);

    void updateOriginal(TodayCollectDto dto,User user);
}
